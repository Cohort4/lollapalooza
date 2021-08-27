package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.TicketEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface TicketEntryRepository extends JpaRepository<TicketEntry, Long> {

}
