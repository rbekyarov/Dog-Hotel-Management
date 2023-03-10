package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.enums.CellSize;
import rbekyarov.project.models.entity.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CellDTO {
    private String code;
    private CellSize cellSize;
    private Status status;


    public CellDTO() {
    }

    @NotBlank(message = "Field cannot be empty")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @NotNull(message = "Cannot be empty, please select")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    @NotNull(message = "Cannot be empty, please select")
    public CellSize getCellSize() {
        return cellSize;
    }

    public void setCellSize(CellSize cellSize) {
        this.cellSize = cellSize;
    }
}
