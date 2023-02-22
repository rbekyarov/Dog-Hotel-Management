package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.enums.CellSize;
import rbekyarov.project.models.entity.enums.Status;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CellRestDTO {
    private Long id;
    private String code;
    private CellSize cellSize;
    private Status status;


    public CellRestDTO() {
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CellSize getCellSize() {
        return cellSize;
    }

    public void setCellSize(CellSize cellSize) {
        this.cellSize = cellSize;
    }

    public Long getId() {
        return id;
    }

    public CellRestDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
