package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.enums.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ReservationRestDTO {
    private Long id;
    private ClientRestDTO clientRestDTO;
    private DogRestDTO dogRestDTO;
    private String startDate;
    private String endDate;
    private CellRestDTO cellRestDTO;
    private Food food;
    private Deworming deworming;
    private Training training;
    private Bathing bathing;
    private Combing combing;
    private Ears ears;
    private Paws paws;
    private Nails nails;
    private Double discount;


    public ReservationRestDTO() {
    }

    @NotNull(message = "cannot be empty")
    public ClientRestDTO getClientRestDTO() {
        return clientRestDTO;
    }

    public void setClientRestDTO(ClientRestDTO clientRestDTO) {
        this.clientRestDTO = clientRestDTO;
    }
    @NotNull(message = "cannot be empty")
    public DogRestDTO getDogRestDTO() {
        return dogRestDTO;
    }

    public void setDogRestDTO(DogRestDTO dogRestDTO) {
        this.dogRestDTO = dogRestDTO;
    }
    @NotNull(message = "cannot be empty")
    public CellRestDTO getCellRestDTO() {
        return cellRestDTO;
    }

    public void setCellRestDTO(CellRestDTO cellRestDTO) {
        this.cellRestDTO = cellRestDTO;
    }
    @NotBlank(message = "cannot be empty")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    @NotBlank(message = "cannot be empty")
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Bathing getBathing() {
        return bathing;
    }

    public void setBathing(Bathing bathing) {
        this.bathing = bathing;
    }

    public Combing getCombing() {
        return combing;
    }

    public void setCombing(Combing combing) {
        this.combing = combing;
    }

    public Ears getEars() {
        return ears;
    }

    public void setEars(Ears ears) {
        this.ears = ears;
    }

    public Paws getPaws() {
        return paws;
    }

    public void setPaws(Paws paws) {
        this.paws = paws;
    }

    public Nails getNails() {
        return nails;
    }

    public void setNails(Nails nails) {
        this.nails = nails;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Deworming getDeworming() {
        return deworming;
    }

    public void setDeworming(Deworming deworming) {
        this.deworming = deworming;
    }

    public Long getId() {
        return id;
    }

    public ReservationRestDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
