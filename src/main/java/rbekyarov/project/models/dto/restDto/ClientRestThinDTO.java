package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.enums.ClientType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class ClientRestThinDTO {

    private String firstName;
    private String lastName;


    public ClientRestThinDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientRestThinDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientRestThinDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
