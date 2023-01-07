package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.Cell;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.Dog;
import rbekyarov.project.models.entity.enums.*;


import javax.validation.constraints.NotNull;

public class ReservationEditDTO {
    private Client client;
    private Dog dog;
    private String startDate;
    private String endDate;
    private Cell cell;
    private Food food;
    private Training training;
    private Bathing bathing;
    private Combing combing;
    private Ears ears;
    private Paws paws;
    private Nails nails;
    private Double discount;


    public ReservationEditDTO() {
    }

    @NotNull
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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
