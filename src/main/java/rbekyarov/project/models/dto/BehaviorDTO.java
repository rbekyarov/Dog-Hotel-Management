package rbekyarov.project.models.dto;

import javax.validation.constraints.Size;

public class BehaviorDTO {
    private Long id;
    private String name;

    public BehaviorDTO() {
    }

    @Size(min = 5, max = 50, message = "Content length must be between 5 and 50 characters!")
    public String getName() {
        return name;
    }

    public BehaviorDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BehaviorDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
