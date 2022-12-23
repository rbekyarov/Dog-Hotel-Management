package softuni.exam.models.dto;

import org.springframework.format.annotation.DateTimeFormat;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.Breed;
import softuni.exam.models.entity.Client;
import softuni.exam.models.entity.ImageData;
import softuni.exam.models.entity.enums.Microchip;
import softuni.exam.models.entity.enums.Passport;
import softuni.exam.models.entity.enums.Sex;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DogEditDTO {

    private String name;
    private LocalDate birthDate;
    private ImageData image;
    private Integer weight;
    private Breed breed;
    private Sex sex;
    private Passport passport;
    private Microchip microchip;
    private Client client;
    private Behavior behavior;

    public DogEditDTO() {
    }

    @NotNull(message = "Name cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ImageData getImage() {
        return image;
    }

    public void setImage(ImageData image) {
        this.image = image;
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
}
