package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "costs")
public class Cost extends BaseEntity {
    private Vendor vendor;
    private String description;
    private BigDecimal amount;
    private LocalDate dateCost;
    private User author;

    private LocalDate dateCreate;

    public Cost() {
    }

    public Cost(Vendor vendor, String description, BigDecimal amount, LocalDate dateCost, User author, LocalDate dateCreate) {
        this.vendor = vendor;
        this.description = description;
        this.amount = amount;
        this.dateCost = dateCost;
        this.author = author;
        this.dateCreate = dateCreate;
    }

    @ManyToOne
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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
    public LocalDate getDateCost() {
        return dateCost;
    }

    public void setDateCost(LocalDate dateCost) {
        this.dateCost = dateCost;
    }

}
