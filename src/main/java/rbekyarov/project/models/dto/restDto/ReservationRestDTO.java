package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.enums.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ReservationRestDTO {
    private Long id;
    private ClientRestThinDTO client;
    private DogRestThinDTO dog;
    private String startDate;
    private String endDate;
    private CellRestThinDTO dogHouse;
    private Food food;
    private Deworming deworming;
    private Training training;
    private Bathing bathing;
    private Combing combing;
    private Ears ears;
    private Paws paws;
    private Nails nails;
    private BigDecimal price;

    private Double discount;
    private BigDecimal totalPrice;



    public ReservationRestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientRestThinDTO getClient() {
        return client;
    }

    public void setClient(ClientRestThinDTO client) {
        this.client = client;
    }

    public DogRestThinDTO getDog() {
        return dog;
    }

    public void setDog(DogRestThinDTO dog) {
        this.dog = dog;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CellRestThinDTO getDogHouse() {
        return dogHouse;
    }

    public void setDogHouse(CellRestThinDTO dogHouse) {
        this.dogHouse = dogHouse;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Deworming getDeworming() {
        return deworming;
    }

    public void setDeworming(Deworming deworming) {
        this.deworming = deworming;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
