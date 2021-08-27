package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.Merch;
import com.C4.lolapalooza.models.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
    Site findByLocation (String location);

}
