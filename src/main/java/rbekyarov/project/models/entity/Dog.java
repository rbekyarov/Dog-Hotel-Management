package rbekyarov.project.models.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;
import rbekyarov.project.models.entity.enums.DogSize;
import rbekyarov.project.models.entity.enums.Microchip;
import rbekyarov.project.models.entity.enums.Passport;
import rbekyarov.project.models.entity.enums.Sex;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "dogs")
public class Dog extends BaseEntity {
    private String name;
    private LocalDate birthDate;
    private String imageName;
    private Integer weight;
    private Breed breed;
    private Sex sex;

    private Passport passport;
    private Microchip microchip;

    private Client client;
    private Behavior behavior;
    private User author;
    private LocalDate dateCreate;
    private DogSize  dogSize;
    private String years;
    private LocalDate lastDewormingDate;
    private String microchipNumber;



    public Dog() {
    }


    public Dog(String name, LocalDate birthDate, String imageName, Integer weight, Breed breed, Sex sex, Passport passport, Microchip microchip, Client client, Behavior behavior, User author, LocalDate dateCreate, DogSize dogSize, LocalDate lastDewormingDate, String microchipNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.imageName = imageName;
        this.weight = weight;
        this.breed = breed;
        this.sex = sex;
        this.passport = passport;
        this.microchip = microchip;
        this.client = client;
        this.behavior = behavior;
        this.author = author;
        this.dateCreate = dateCreate;
        this.dogSize = dogSize;
        this.lastDewormingDate = lastDewormingDate;
        this.microchipNumber = microchipNumber;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    @Column
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @ManyToOne
    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @Enumerated(EnumType.STRING)
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Enumerated(EnumType.STRING)
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Enumerated(EnumType.STRING)
    public Microchip getMicrochip() {
        return microchip;
    }

    public void setMicrochip(Microchip microchip) {
        this.microchip = microchip;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    @Enumerated(EnumType.STRING)
    public DogSize getDogSize() {
        return dogSize;
    }

    public void setDogSize(DogSize dogSize) {
        this.dogSize = dogSize;
    }
    @Column
    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
    @Column
    public LocalDate getLastDewormingDate() {
        return lastDewormingDate;
    }

    public void setLastDewormingDate(LocalDate lastDewormingDate) {
        this.lastDewormingDate = lastDewormingDate;
    }
    @Column
    public String getMicrochipNumber() {
        return microchipNumber;
    }

    public void setMicrochipNumber(String microchipNumber) {
        this.microchipNumber = microchipNumber;
    }
}
