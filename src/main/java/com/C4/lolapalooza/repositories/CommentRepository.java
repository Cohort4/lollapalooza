package com.C4.lolapalooza.repositories;

import com.C4.lolapalooza.models.Client;
import com.C4.lolapalooza.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByDescription(String description);
}
