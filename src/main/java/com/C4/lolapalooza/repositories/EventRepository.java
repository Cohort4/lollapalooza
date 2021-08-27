package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.Comment;
import com.C4.lolapalooza.models.Event;
import com.C4.lolapalooza.models.Merch;
import com.C4.lolapalooza.models.TicketEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findBynameEvent (String nameEvent);
}
