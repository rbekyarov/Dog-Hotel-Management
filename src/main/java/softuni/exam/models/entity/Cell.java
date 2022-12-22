package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "cells")
public class Cell extends BaseEntity{
    private String code;
    private Status status;

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
}
