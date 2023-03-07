package rbekyarov.project.models.dto.restDto;


import rbekyarov.project.models.entity.City;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class VendorRestDTO {
    private Long id;
    private String name;
    private String country;
    private CityRestThinDTO city;
    private String address;
    private String vatNumber;
    private String email;


    public VendorRestDTO() {
    }

    public VendorRestDTO(Long id, String name, String country, CityRestThinDTO city, String address, String vatNumber, String email) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.vatNumber = vatNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CityRestThinDTO getCity() {
        return city;
    }

    public void setCity(CityRestThinDTO city) {
        this.city = city;
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
