package rbekyarov.project.models.dto.restDto;

import javax.validation.constraints.Size;

public class BehaviorRestDTO {
    private Long id;
    private String name;

    public BehaviorRestDTO() {
    }

    @Size(min = 5, max = 50, message = "Content length must be between 5 and 50 characters!")
    public String getName() {
        return name;
    }

    public BehaviorRestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BehaviorRestDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
