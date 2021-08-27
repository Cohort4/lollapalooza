package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.MerchFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MerchFacturaRepository extends JpaRepository<MerchFactura, Long> {
}
