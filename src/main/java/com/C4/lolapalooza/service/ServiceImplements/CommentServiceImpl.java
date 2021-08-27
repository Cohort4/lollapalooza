package com.C4.lolapalooza.service.ServiceImplements;

import com.C4.lolapalooza.dtos.CommentDTO;
import com.C4.lolapalooza.models.Comment;
import com.C4.lolapalooza.models.Merch;
import com.C4.lolapalooza.repositories.ClientRepository;
import com.C4.lolapalooza.repositories.CommentRepository;
import com.C4.lolapalooza.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<CommentDTO> getComments(){
        return this.commentRepository.findAll().stream().map(x -> new CommentDTO(x)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getComentarioById(Long id){
        return this.commentRepository.findById(id).map(x -> new CommentDTO(x)).orElse(null);
    }

    @Override
    public ResponseEntity<?> deleteComment(String commentEmail, String commentUser){
        Comment comment = commentRepository.findByDescription(commentUser);
        if (comment.getClient().getEmail().equals(commentEmail)) {
            commentRepository.delete(comment);
            return new ResponseEntity<>("Comment Deleted", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Error", HttpStatus.ACCEPTED);
        }
    }

}
