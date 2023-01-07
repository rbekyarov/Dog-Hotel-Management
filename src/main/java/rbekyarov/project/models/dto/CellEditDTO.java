package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.enums.Status;

import javax.validation.constraints.NotNull;

public class CellEditDTO {
    private String code;
    private Status status;

    public CellEditDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull(message = "Field cannot be empty")

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
