package rbekyarov.project.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BehaviorDTO {
    private String name;

    public BehaviorDTO() {
    }

    @Size(min = 5, max = 50, message = "Content length must be between 5 and 50 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
