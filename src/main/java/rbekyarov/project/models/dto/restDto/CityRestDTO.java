package rbekyarov.project.models.dto.restDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CityRestDTO {
    private Long id;
    private String code;
    private String name;

    public CityRestDTO() {
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public CityRestDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
