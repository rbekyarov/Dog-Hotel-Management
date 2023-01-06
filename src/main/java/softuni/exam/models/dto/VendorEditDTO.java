package softuni.exam.models.dto;


import softuni.exam.models.entity.City;

import javax.validation.constraints.NotNull;


public class VendorEditDTO {
    private String name;
    private String country;
    private City city;
    private String address;
    private String vatNumber;
    private String email;


    public VendorEditDTO() {
    }
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @NotNull
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    @NotNull
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @NotNull
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
