package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoces")
public class Invoice extends BaseEntity {
    private Client client;
    private Dog dog;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer countStay;
    private Cell cell;
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
    private Company company;
    private User author;
    private LocalDate dateCreate;

    private Long reservationId;

    public Invoice() {
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne
    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Column
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column
    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Column
    public Integer getCountStay() {
        return countStay;
    }

    public void setCountStay(Integer countStay) {
        this.countStay = countStay;
    }

    @ManyToOne
    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
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

    @ManyToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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
}
