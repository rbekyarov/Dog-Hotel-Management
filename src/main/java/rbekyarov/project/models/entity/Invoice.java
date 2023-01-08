package rbekyarov.project.models.entity;

import rbekyarov.project.models.entity.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoces")
public class Invoice extends BaseEntity {
    private String companyName;
    private String companyCityName;
    private String companyAddress;
    private String companyVatNumber;
    private String companyEmail;
    private String companyBankName;
    private String companyBankAccount;
    private String companyManagerName;
    private String dogName;
    private Integer countStay;
    private String cellCode;
    private Food food;
    private Training training;
    private Bathing bathing;
    private Combing combing;
    private Ears ears;
    private Paws paws;
    private Nails nails;

    private BigDecimal price;
    private Double discount;

    private BigDecimal totalPrice;
    private String authorName;
    private LocalDate dateCreate;

    private Long reservationId;

    private BigDecimal countStayPrice;
    private BigDecimal foodPrice;
    private BigDecimal trainingPrice;
    private BigDecimal bathingPrice;
    private BigDecimal combingPrice;
    private BigDecimal earsPrice;
    private BigDecimal pawsPrice;
    private BigDecimal nailsPrice;

    private String clientName;
    private String clientEmail;
    private String clientAddress;
    private String clientCityName;
    private String clientPhone;

    public Invoice() {
    }

    public Invoice(String companyName, String companyCityName, String companyAddress, String companyVatNumber, String companyEmail, String companyBankName, String companyBankAccount, String companyManagerName, String dogName, Integer countStay, String cellCode, Food food, Training training, Bathing bathing, Combing combing, Ears ears, Paws paws, Nails nails, BigDecimal price, Double discount, BigDecimal totalPrice, String authorName, LocalDate dateCreate, Long reservationId, BigDecimal countStayPrice, BigDecimal foodPrice, BigDecimal trainingPrice, BigDecimal bathingPrice, BigDecimal combingPrice, BigDecimal earsPrice, BigDecimal pawsPrice, BigDecimal nailsPrice, String clientName, String clientEmail, String clientAddress, String clientCityName, String clientPhone) {
        this.companyName = companyName;
        this.companyCityName = companyCityName;
        this.companyAddress = companyAddress;
        this.companyVatNumber = companyVatNumber;
        this.companyEmail = companyEmail;
        this.companyBankName = companyBankName;
        this.companyBankAccount = companyBankAccount;
        this.companyManagerName = companyManagerName;
        this.dogName = dogName;
        this.countStay = countStay;
        this.cellCode = cellCode;
        this.food = food;
        this.training = training;
        this.bathing = bathing;
        this.combing = combing;
        this.ears = ears;
        this.paws = paws;
        this.nails = nails;
        this.price = price;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.authorName = authorName;
        this.dateCreate = dateCreate;
        this.reservationId = reservationId;
        this.countStayPrice = countStayPrice;
        this.foodPrice = foodPrice;
        this.trainingPrice = trainingPrice;
        this.bathingPrice = bathingPrice;
        this.combingPrice = combingPrice;
        this.earsPrice = earsPrice;
        this.pawsPrice = pawsPrice;
        this.nailsPrice = nailsPrice;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
        this.clientCityName = clientCityName;
        this.clientPhone = clientPhone;
    }

    @Column
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @Column
    public String getCompanyCityName() {
        return companyCityName;
    }

    public void setCompanyCityName(String companyCityName) {
        this.companyCityName = companyCityName;
    }
    @Column
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    @Column
    public String getCompanyVatNumber() {
        return companyVatNumber;
    }

    public void setCompanyVatNumber(String companyVatNumber) {
        this.companyVatNumber = companyVatNumber;
    }
    @Column
    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
    @Column
    public String getCompanyBankName() {
        return companyBankName;
    }

    public void setCompanyBankName(String companyBankName) {
        this.companyBankName = companyBankName;
    }
    @Column
    public String getCompanyBankAccount() {
        return companyBankAccount;
    }

    public void setCompanyBankAccount(String companyBankAccount) {
        this.companyBankAccount = companyBankAccount;
    }
    @Column
    public String getCompanyManagerName() {
        return companyManagerName;
    }

    public void setCompanyManagerName(String companyManagerName) {
        this.companyManagerName = companyManagerName;
    }
    @Column
    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }
    @Column
    public Integer getCountStay() {
        return countStay;
    }

    public void setCountStay(Integer countStay) {
        this.countStay = countStay;
    }
    @Column
    public String getCellCode() {
        return cellCode;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }
    @Enumerated(EnumType.STRING)
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
    @Enumerated(EnumType.STRING)
    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
    @Enumerated(EnumType.STRING)
    public Bathing getBathing() {
        return bathing;
    }

    public void setBathing(Bathing bathing) {
        this.bathing = bathing;
    }
    @Enumerated(EnumType.STRING)
    public Combing getCombing() {
        return combing;
    }

    public void setCombing(Combing combing) {
        this.combing = combing;
    }
    @Enumerated(EnumType.STRING)
    public Ears getEars() {
        return ears;
    }

    public void setEars(Ears ears) {
        this.ears = ears;
    }
    @Enumerated(EnumType.STRING)
    public Paws getPaws() {
        return paws;
    }

    public void setPaws(Paws paws) {
        this.paws = paws;
    }
    @Enumerated(EnumType.STRING)
    public Nails getNails() {
        return nails;
    }

    public void setNails(Nails nails) {
        this.nails = nails;
    }
    @Column
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    @Column
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Column
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    @Column
    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    @Column
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
    @Column
    public BigDecimal getCountStayPrice() {
        return countStayPrice;
    }

    public void setCountStayPrice(BigDecimal countStayPrice) {
        this.countStayPrice = countStayPrice;
    }
    @Column
    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }
    @Column
    public BigDecimal getTrainingPrice() {
        return trainingPrice;
    }

    public void setTrainingPrice(BigDecimal trainingPrice) {
        this.trainingPrice = trainingPrice;
    }
    @Column
    public BigDecimal getBathingPrice() {
        return bathingPrice;
    }

    public void setBathingPrice(BigDecimal bathingPrice) {
        this.bathingPrice = bathingPrice;
    }
    @Column
    public BigDecimal getCombingPrice() {
        return combingPrice;
    }

    public void setCombingPrice(BigDecimal combingPrice) {
        this.combingPrice = combingPrice;
    }
    @Column
    public BigDecimal getEarsPrice() {
        return earsPrice;
    }

    public void setEarsPrice(BigDecimal earsPrice) {
        this.earsPrice = earsPrice;
    }
    @Column
    public BigDecimal getPawsPrice() {
        return pawsPrice;
    }

    public void setPawsPrice(BigDecimal pawsPrice) {
        this.pawsPrice = pawsPrice;
    }
    @Column
    public BigDecimal getNailsPrice() {
        return nailsPrice;
    }

    public void setNailsPrice(BigDecimal nailsPrice) {
        this.nailsPrice = nailsPrice;
    }
    @Column
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    @Column
    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    @Column
    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    @Column
    public String getClientCityName() {
        return clientCityName;
    }

    public void setClientCityName(String clientCityName) {
        this.clientCityName = clientCityName;
    }
    @Column
    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }
}
