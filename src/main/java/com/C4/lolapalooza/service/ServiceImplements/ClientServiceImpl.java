package com.C4.lolapalooza.service.ServiceImplements;

import com.C4.lolapalooza.models.Client;
import com.C4.lolapalooza.repositories.ClientRepository;
import com.C4.lolapalooza.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public void saveC(Client user) {
        clientRepository.save(user);
    }

    @Override
    public void deleteClient(Client user) {
        clientRepository.delete(user);
    }
}
