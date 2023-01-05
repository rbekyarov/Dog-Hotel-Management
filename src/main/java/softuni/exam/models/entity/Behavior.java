package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "behaviors")
public class Behavior extends BaseEntity{

    private String name;

    private User author;

    private LocalDate dateCreate;
    public Behavior() {
    }

    public Behavior(String name, User author, LocalDate dateCreate) {
        this.name = name;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
