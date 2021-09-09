package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.PreviousEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PreviousEventRepository extends JpaRepository<PreviousEvent, Long> {

    PreviousEvent getById(Long id);
}
