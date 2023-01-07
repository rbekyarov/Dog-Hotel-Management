package rbekyarov.project.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "breeds")
public class Breed extends BaseEntity{
    private String breedName;
    private User author;
    private LocalDate dateCreate;
    public Breed() {
    }

    public Breed(String breedName, User author, LocalDate dateCreate) {
        this.breedName = breedName;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    @Column(unique = true, nullable = false)
    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
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
