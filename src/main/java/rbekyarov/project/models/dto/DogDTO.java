package rbekyarov.project.models.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.Client;
import rbekyarov.project.models.entity.enums.Microchip;
import rbekyarov.project.models.entity.enums.Passport;
import rbekyarov.project.models.entity.enums.Sex;

import javax.validation.constraints.NotNull;
@Data
public class DogDTO {

    private String name;
    private String birthDate;
    private Integer weight;
    private Breed breed;
    private Sex sex;
    private Passport passport;
    private Microchip microchip;
    private Client client;
    private Behavior behavior;

    private String imageName;

    public DogDTO() {
    }

    @NotNull(message = "Name cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @NotNull(message = "Breed cannot be empty")
    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @NotNull(message = "Sex cannot be empty")
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @NotNull(message = "Passport cannot be empty")
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @NotNull(message = "Microchip cannot be empty")
    public Microchip getMicrochip() {
        return microchip;
    }

    public void setMicrochip(Microchip microchip) {
        this.microchip = microchip;
    }

    @NotNull(message = "Client cannot be empty")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
