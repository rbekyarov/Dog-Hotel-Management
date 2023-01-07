package rbekyarov.project.models.entity;


import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "vendors")
public class Vendor extends BaseEntity {
    private String name;
    private String country;
    private City city;
    private String address;
    private String vatNumber;
    private String email;
    private User author;
    private LocalDate dateCreate;

    public Vendor() {
    }

    public Vendor(String name, String country, City city, String address, String vatNumber, String email, User author, LocalDate dateCreate) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.vatNumber = vatNumber;
        this.email = email;
        this.author = author;
        this.dateCreate = dateCreate;
    }
    @Column
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @ManyToOne
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    @Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }
    @Column
    public void setAuthor(User author) {
        this.author = author;
    }
    @Column
    public LocalDate getDateCreate() {
        return dateCreate;
    }
    @Column
    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
}
