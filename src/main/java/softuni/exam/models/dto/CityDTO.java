package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;

public class CityDTO {

    private String code;
    private String name;

    public CityDTO() {
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
