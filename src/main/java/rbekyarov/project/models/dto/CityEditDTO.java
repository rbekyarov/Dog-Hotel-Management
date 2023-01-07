package rbekyarov.project.models.dto;

import javax.validation.constraints.NotNull;

public class CityEditDTO {

    private String code;
    private String name;

    public CityEditDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull(message = "Field cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
