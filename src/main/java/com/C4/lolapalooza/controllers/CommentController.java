package com.C4.lolapalooza.controllers;

import com.C4.lolapalooza.dtos.CommentDTO;
import com.C4.lolapalooza.models.*;
import com.C4.lolapalooza.repositories.ClientRepository;
import com.C4.lolapalooza.repositories.CommentRepository;
import com.C4.lolapalooza.repositories.PreviousEventRepository;
import com.C4.lolapalooza.service.ClientService;
import com.C4.lolapalooza.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    //A agregar funciones que controlen el vocabulario del mismo.  (Barto)
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PreviousEventRepository previousEventRepository;

    @RequestMapping("/comments")
    public List<CommentDTO> getComments() {
        return commentService.getComments();
    }

    @RequestMapping("/comments/{id}")
    public CommentDTO getComentarioById(@PathVariable Long id) {
        return commentService.getComentarioById(id);
    }

    @PostMapping(path = "/comment/deleteComment")
    public ResponseEntity<?> deleteComment(@RequestParam String commentEmail,
                                           @RequestParam String commentUser
    ) {
        return commentService.deleteComment(commentEmail,commentUser);
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(Authentication authentication,
                                        @RequestParam Long id,
                                       @RequestParam String description){

        Client client = clientRepository.findByEmail(authentication.getName());

        PreviousEvent previousEvent = previousEventRepository.getById(id);

        if(client == null){
            return new ResponseEntity<>("Client not recognized", HttpStatus.FORBIDDEN);
        }
        if(authentication == null){
            return new ResponseEntity<>("Client not authenticated", HttpStatus.FORBIDDEN);
        }
        if(description.isEmpty() || id == null ){
            return new ResponseEntity<>("Please complete all fields", HttpStatus.FORBIDDEN);
        }
        Comment comment1 = commentRepository.save(new Comment(description, LocalDate.now(),previousEvent,client));

        return new ResponseEntity<>("Comentario Creado", HttpStatus.ACCEPTED);
    }

    @PostMapping("/editComment")
    public ResponseEntity<?> editComment(Authentication authentication,
                                         @RequestParam String description,
                                         @RequestParam Long idComment) {

        Client client = clientRepository.findByEmail(authentication.getName());

        Comment comment = commentRepository.findById(idComment).orElse(null);

        if (client == null) {
            return new ResponseEntity<>("Client not authenticated", HttpStatus.UNAUTHORIZED);
        }
        if(description.isEmpty() || idComment == null) {
            return new ResponseEntity<>("Please, complete all fields", HttpStatus.FORBIDDEN);
        }
        if(comment.getClient().getEmail() != client.getEmail()) {
            return new ResponseEntity<>("This comment isn't you", HttpStatus.UNAUTHORIZED);
        }
            comment.setDescription(description);
            commentRepository.save(comment);
            return new ResponseEntity<>("This comment has been edited", HttpStatus.ACCEPTED);
        }

    @PostMapping("/deleteComment")
    public ResponseEntity<?> commentDelete(Authentication authentication,
                                         @RequestParam Long idComment) {

        Client client = clientRepository.findByEmail(authentication.getName());

        Comment comment = commentRepository.findById(idComment).orElse(null);

        if (client == null) {
            return new ResponseEntity<>("Client not authenticated", HttpStatus.UNAUTHORIZED);
        }
        if(idComment == null) {
            return new ResponseEntity<>("Please, complete all fields", HttpStatus.FORBIDDEN);
        }
        if(comment.getClient().getEmail() != client.getEmail()) {
            return new ResponseEntity<>("This comment isn't you", HttpStatus.UNAUTHORIZED);
        }
        commentRepository.delete(comment);
        return new ResponseEntity<>("This comment has been deleted", HttpStatus.ACCEPTED);
    }
}
