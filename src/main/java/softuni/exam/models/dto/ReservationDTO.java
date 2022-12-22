package softuni.exam.models.dto;

import softuni.exam.models.entity.Cell;
import softuni.exam.models.entity.Client;
import softuni.exam.models.entity.Dog;
import softuni.exam.models.entity.enums.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class ReservationDTO {
    private Client client;
    private Set<Dog> dogs;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<Cell> cells;
    private Food food;
    private Training training;
    private Bathing bathing;
    private Combing combing;
    private Ears ears;
    private Paws paws;
    private Nails nails;
    private Double discount;


    public ReservationDTO() {
    }
    @NotNull
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    @NotNull
    public Set<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
    }
    @NotNull
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    @NotNull
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    @NotNull
    public Set<Cell> getCells() {
        return cells;
    }

    public void setCells(Set<Cell> cells) {
        this.cells = cells;
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


}
