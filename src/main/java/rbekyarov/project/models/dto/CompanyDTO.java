package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.BaseEntity;
import rbekyarov.project.models.entity.City;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class CompanyDTO extends BaseEntity {
    private String name;

    private String logoName;
    private String country;
    private City city;
    private String address;
    private String vatNumber;
    private String email;
    private String bankName;
    private String bankAccount;

    private BigDecimal balance;
    private String managerName;

    public CompanyDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
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
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 8, max = 12, message = "Content length must be between 8 and 12 characters!")
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
    @NotNull(message = "Field cannot be empty")
    @Size(min = 16, max = 24, message = "Content length must be between 16 and 24 characters!")
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 20, message = "Content length must be between 3 and 20 characters!")
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, max = 30, message = "Content length must be between 3 and 30 characters!")
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @NotNull(message = "Field cannot be empty")
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
