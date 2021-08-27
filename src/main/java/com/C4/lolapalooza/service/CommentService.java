package com.C4.lolapalooza.service;

import com.C4.lolapalooza.dtos.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getComments();
    CommentDTO getComentarioById(Long id);
    ResponseEntity<?> deleteComment(String commentEmail,String commentUser);

}
