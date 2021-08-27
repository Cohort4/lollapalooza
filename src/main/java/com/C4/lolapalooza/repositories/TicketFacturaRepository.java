package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.TicketEntry;
import com.C4.lolapalooza.models.TicketFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketFacturaRepository extends JpaRepository<TicketFactura, Long> {
}
