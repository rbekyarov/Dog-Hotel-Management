package rbekyarov.project.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityEditDTO {
private Long id;
    private String code;
    private String name;

    public CityEditDTO() {
    }

    @NotBlank(message = "Field cannot be empty")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotBlank(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
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
