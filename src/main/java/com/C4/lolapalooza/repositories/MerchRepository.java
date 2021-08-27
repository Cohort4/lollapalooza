package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.Merch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MerchRepository extends JpaRepository<Merch, Long> {
    Merch findByName (String name);

}
