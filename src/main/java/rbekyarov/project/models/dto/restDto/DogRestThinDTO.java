package rbekyarov.project.models.dto.restDto;

import lombok.Data;
import rbekyarov.project.models.entity.enums.DogSize;
import rbekyarov.project.models.entity.enums.Microchip;
import rbekyarov.project.models.entity.enums.Passport;
import rbekyarov.project.models.entity.enums.Sex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DogRestThinDTO {
    private String name;

    public DogRestThinDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
