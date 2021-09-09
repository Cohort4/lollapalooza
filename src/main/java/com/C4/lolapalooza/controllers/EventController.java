package com.C4.lolapalooza.controllers;


import com.C4.lolapalooza.dtos.EventDTO;
import com.C4.lolapalooza.dtos.SiteDTO;
import com.C4.lolapalooza.models.Comment;
import com.C4.lolapalooza.models.Event;
import com.C4.lolapalooza.models.Site;
import com.C4.lolapalooza.repositories.EventRepository;
import com.C4.lolapalooza.repositories.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    SiteRepository siteRepository;

    @RequestMapping("/events")
    private List<EventDTO> getEvents(){
        return this.eventRepository.findAll().stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
    }

    @RequestMapping("/events/{id}")
    private EventDTO getEventById(@PathVariable Long id){
        return this.eventRepository.findById(id).map(x -> new EventDTO(x)).orElse(null);
    }

    @PostMapping(path="/event/deleteEvent")
    public ResponseEntity<?> deleteEvent(@RequestParam String nameEvent){
        Event eventToDelete = eventRepository.findBynameEvent(nameEvent);
        if (eventToDelete==null)
        {
            return new ResponseEntity<>("event not found", HttpStatus.ACCEPTED);
        }
        if(eventToDelete.getStatus()==true)
            {
            eventToDelete.setStatus(false);
            eventRepository.save(eventToDelete);
            return new ResponseEntity<>("event updated", HttpStatus.ACCEPTED);
            }
        else{
            eventToDelete.setStatus(true);
            eventRepository.save(eventToDelete);
            return new ResponseEntity<>("event updated", HttpStatus.ACCEPTED);
        }

        }

    @PostMapping(path="/event/addEvent")
    public ResponseEntity<?> addEvent(@RequestParam String nameEvent,@RequestParam String bandsEvent,@RequestParam Double priceEvent,
                                      @RequestParam String dateEvent,@RequestParam String siteEvent){
        if(bandsEvent.isEmpty() || priceEvent<0 || dateEvent.isEmpty() || siteEvent.isEmpty() || nameEvent.isEmpty())
        {
            return new ResponseEntity<>("Incomplete dates", HttpStatus.FORBIDDEN);
        }

        if(eventRepository.findBynameEvent(nameEvent)!=null)
        {
            return new ResponseEntity<>("Name of event exist,choose another", HttpStatus.FORBIDDEN);
        }

        Site site = siteRepository.findByLocation(siteEvent);
        if(site.getId()==null) {
            return new ResponseEntity<>("Site not exist", HttpStatus.FORBIDDEN);
        }
        LocalDate localDateEvent = LocalDate.parse(dateEvent);
        eventRepository.save(new Event(nameEvent,List.of(bandsEvent), localDateEvent, priceEvent, site,true, "./img/day1.jpg"));
        return new ResponseEntity<>("Event updated", HttpStatus.ACCEPTED);
    }

    @PostMapping(path="/event/editEvent")
    public ResponseEntity<?> editEvent(@RequestParam String nameEvent, @RequestParam String dateEvent){
        if( dateEvent.isEmpty() )
        {
            return new ResponseEntity<>("Incomplete dates", HttpStatus.FORBIDDEN);
        }
        Event eventToModificate = eventRepository.findBynameEvent(nameEvent);
        if(eventToModificate==null)
        {
            return new ResponseEntity<>("Event not found", HttpStatus.FORBIDDEN);
        }
        LocalDate localDateEvent = LocalDate.parse(dateEvent);
        if(eventToModificate.getDateEvent().equals(localDateEvent)){
            return new ResponseEntity<>("Put a diferent date!", HttpStatus.FORBIDDEN);
        }
        if(eventToModificate.getDateEvent().isBefore(LocalDate.now())){
            return new ResponseEntity<>("Wrong,must be a later date!", HttpStatus.FORBIDDEN);
        }
        eventToModificate.setDateEvent(localDateEvent);
        eventRepository.save(eventToModificate);
        return new ResponseEntity<>("Event updated", HttpStatus.ACCEPTED);
    }


    }


