package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{
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

    public Company() {
    }

    public Company(String name, String logoName, String country, City city, String address, String vatNumber, String email, String bankName, String bankAccount, BigDecimal balance, String managerName) {
        this.name = name;
        this.logoName = logoName;
        this.country = country;
        this.city = city;
        this.address = address;
        this.vatNumber = vatNumber;
        this.email = email;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.balance = balance;
        this.managerName = managerName;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    @Column()
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column()
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
    @Column()
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column()
    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    @Column()
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column()
    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    @Column()
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
    @Column()
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    @Column()
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
