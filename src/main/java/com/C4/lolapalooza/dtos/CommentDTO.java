package com.C4.lolapalooza.dtos;

import com.C4.lolapalooza.models.Comment;

import java.time.LocalDate;

public class CommentDTO {
    private Long id;
    private String Email;
    private String description;
    private LocalDate date;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.Email = comment.getClient().getEmail();
        this.description = comment.getDescription();
        this.date = comment.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
