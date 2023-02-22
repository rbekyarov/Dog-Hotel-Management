package rbekyarov.project.models.dto.restDto;

import javax.validation.constraints.Size;

public class BehaviorRestThinDTO {

    private String name;

    public BehaviorRestThinDTO() {
    }

    public String getName() {
        return name;
    }

    public BehaviorRestThinDTO setName(String name) {
        this.name = name;
        return this;
    }

}
