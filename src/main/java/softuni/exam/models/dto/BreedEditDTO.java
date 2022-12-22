package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BreedEditDTO {
    private String breedName;

    public BreedEditDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 2, max = 50, message = "Content length must be between 2 and 20 characters!")
    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }
}
