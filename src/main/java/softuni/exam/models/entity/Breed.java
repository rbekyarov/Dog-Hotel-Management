package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "breeds")
public class Breed extends BaseEntity{
    private String breedName;

    public Breed() {
    }

    public Breed(String breedName) {
        this.breedName = breedName;
    }

    @Column(unique = true, nullable = false)
    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}
