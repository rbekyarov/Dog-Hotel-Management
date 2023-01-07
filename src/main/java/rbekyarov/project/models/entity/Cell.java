package rbekyarov.project.models.entity;

import rbekyarov.project.models.entity.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cells")
public class Cell extends BaseEntity{
    private String code;
    private Status status;

    private User author;
    private LocalDate dateCreate;

    public Cell(String code, Status status, User author, LocalDate dateCreate) {
        this.code = code;
        this.status = status;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    public Cell() {
    }
    @Column(nullable = false, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
}
