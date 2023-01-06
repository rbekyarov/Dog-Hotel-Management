package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity{
    private Client client;
    private Dog dog;
    private LocalDate startDate;
    private LocalDate endDate;

    private Integer countOvernightStay;
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

    private StatusReservation statusReservation;
    private User author;
    private LocalDate dateCreate;
    private Company company;

    public Reservation() {
    }

    public Reservation(Client client, Dog dog, LocalDate startDate, LocalDate endDate, Integer countOvernightStay, Cell cell, Food food, Training training, Bathing bathing, Combing combing, Ears ears, Paws paws, Nails nails, BigDecimal price, Double discount, BigDecimal totalPrice, StatusReservation statusReservation, User author, LocalDate dateCreate) {
        this.client = client;
        this.dog = dog;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countOvernightStay = countOvernightStay;
        this.cell = cell;
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
        this.statusReservation = statusReservation;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    @Column(name = "start_date", nullable = false)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    @Column(name = "end_date", nullable = false)
    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getCountOvernightStay() {
        return countOvernightStay;
    }

    public void setCountOvernightStay(Integer countOvernightStay) {
        this.countOvernightStay = countOvernightStay;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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
    @ManyToOne
    @JoinColumn(name = "dog_id", referencedColumnName = "id")
    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
    @ManyToOne
    @JoinColumn(name = "cell_id", referencedColumnName = "id")
    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    @Enumerated(EnumType.STRING)
    public StatusReservation getStatusReservation() {
        return statusReservation;
    }

    public void setStatusReservation(StatusReservation statusReservation) {
        this.statusReservation = statusReservation;
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
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
