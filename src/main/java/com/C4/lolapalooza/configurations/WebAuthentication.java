package com.C4.lolapalooza.configurations;

import com.C4.lolapalooza.models.Client;
import com.C4.lolapalooza.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        ClientService userService;

        @Bean
        public PasswordEncoder passwordEncoder() { return PasswordEncoderFactories.createDelegatingPasswordEncoder(); }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(inputName-> {

                Client client = userService.findClientByEmail(inputName);

                if (client != null && client.getStatus()) {
                    if(client.getEmail().equals("admin@lollapalooza.com")){
                        return new User(client.getEmail(), client.getPassword(),
                                AuthorityUtils.createAuthorityList("ADMIN"));
                    }else {
                        return new User(client.getEmail(), client.getPassword(),
                                AuthorityUtils.createAuthorityList("USER"));
                    }
                } else {
                    throw new UsernameNotFoundException("Unknown user: " + inputName);
                }

            });
        }
}
