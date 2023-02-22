package rbekyarov.project.models.dto.restDto;

import lombok.Data;

import rbekyarov.project.models.entity.enums.DogSize;
import rbekyarov.project.models.entity.enums.Microchip;
import rbekyarov.project.models.entity.enums.Passport;
import rbekyarov.project.models.entity.enums.Sex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DogRestDTO {
    private Long id;
    private String name;
    private String birthDate;
    private Integer weight;
    private BreedRestThinDTO breed;
    private Sex sex;
    private Passport passport;
    private Microchip microchip;
    private ClientRestThinDTO client;
    private BehaviorRestThinDTO behavior;

    private String imageName;
    private DogSize dogSize;
    private String years;
    private String lastDewormingDate;
    private String microchipNumber;
    public DogRestDTO() {
    }

    @NotBlank(message = "cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotBlank(message = "cannot be empty")
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @NotNull(message = "cannot be empty")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public BreedRestThinDTO getBreed() {
        return breed;
    }

    public void setBreed(BreedRestThinDTO breed) {
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


    public ClientRestThinDTO getClient() {
        return client;
    }

    public void setClient(ClientRestThinDTO client) {
        this.client = client;
    }

    public BehaviorRestThinDTO getBehavior() {
        return behavior;
    }

    public void setBehavior(BehaviorRestThinDTO behavior) {
        this.behavior = behavior;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public DogSize getDogSize() {
        return dogSize;
    }

    public void setDogSize(DogSize dogSize) {
        this.dogSize = dogSize;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
    @NotBlank(message = "cannot be empty")
    public String getLastDewormingDate() {
        return lastDewormingDate;
    }

    public void setLastDewormingDate(String lastDewormingDate) {
        this.lastDewormingDate = lastDewormingDate;
    }

    public String getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(String microchipNumber) {
        this.microchipNumber = microchipNumber;
    }

    public Long getId() {
        return id;
    }

    public DogRestDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
