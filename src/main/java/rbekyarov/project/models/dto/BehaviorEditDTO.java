package rbekyarov.project.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BehaviorEditDTO {

    private String name;

    public BehaviorEditDTO() {
    }
    @Size(min = 2, max = 50, message = "Content length must be between 2 and 20 characters!")
    @NotNull(message = "Field cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
