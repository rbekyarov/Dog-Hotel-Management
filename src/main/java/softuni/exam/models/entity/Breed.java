package softuni.exam.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "breeds")
public class Breed extends BaseEntity{
    private String breedName;
    private User author;
    public Breed() {
    }

    public Breed(String breedName, User author) {
        this.breedName = breedName;
        this.author = author;
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
}
