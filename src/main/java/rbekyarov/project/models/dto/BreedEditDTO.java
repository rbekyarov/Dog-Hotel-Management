package rbekyarov.project.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BreedEditDTO {
    private Long id;
    private String breedName;

    public BreedEditDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 50, message = "Content length must be between 3 and 50 characters!")
    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
