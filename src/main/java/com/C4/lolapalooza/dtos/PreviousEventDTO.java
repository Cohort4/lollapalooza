package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Comment;
import com.C4.lolapalooza.models.PreviousEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PreviousEventDTO {

    private Long id;
    private String name;
    private String image;
    private List<CommentDTO> comments = new ArrayList<>();

    public PreviousEventDTO() {
    }

    public PreviousEventDTO(PreviousEvent previousEvent) {
        this.id = previousEvent.getId();
        this.name = previousEvent.getName();
        this.image = previousEvent.getImage();
        this.comments = previousEvent.getComment().stream().map(CommentDTO::new).collect(Collectors.toList());

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
