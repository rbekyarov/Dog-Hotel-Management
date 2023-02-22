package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.enums.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRestDTO {
    private Long id;
    private String username;


    public UserRestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
