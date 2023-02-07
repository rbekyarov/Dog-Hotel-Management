package rbekyarov.project.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BehaviorEditDTO {
private Long id;
    private String name;

    public BehaviorEditDTO() {
    }
    @Size(min = 5, max = 50, message = "AAContent length must be between 5 and 50 characters!")
    @NotNull(message = "Field cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
