package rbekyarov.project.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prices")
public class Price extends BaseEntity{
  private BigDecimal priceStayS;
  private BigDecimal priceStayM;
  private BigDecimal priceStayL;
  private BigDecimal priceFood;
  private BigDecimal priceDeworming;
  private BigDecimal priceTraining;
  private BigDecimal priceBathing;
  private BigDecimal priceCombing;
  private BigDecimal pricePaws;
  private BigDecimal priceEars;
  private BigDecimal priceNails;
    private LocalDate dateCreate;


    public Price() {
    }

    public Price(BigDecimal priceStayS, BigDecimal priceStayM, BigDecimal priceStayL, BigDecimal priceFood, BigDecimal priceDeworming, BigDecimal priceTraining, BigDecimal priceBathing, BigDecimal priceCombing, BigDecimal pricePaws, BigDecimal priceEars, BigDecimal priceNails, LocalDate dateCreate) {
        this.priceStayS = priceStayS;
        this.priceStayM = priceStayM;
        this.priceStayL = priceStayL;
        this.priceFood = priceFood;
        this.priceDeworming = priceDeworming;
        this.priceTraining = priceTraining;
        this.priceBathing = priceBathing;
        this.priceCombing = priceCombing;
        this.pricePaws = pricePaws;
        this.priceEars = priceEars;
        this.priceNails = priceNails;
        this.dateCreate = dateCreate;
    }

    @Column
    public BigDecimal getPriceStayS() {
        return priceStayS;
    }

    public void setPriceStayS(BigDecimal priceStayS) {
        this.priceStayS = priceStayS;
    }
    @Column
    public BigDecimal getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(BigDecimal priceFood) {
        this.priceFood = priceFood;
    }
    @Column
    public BigDecimal getPriceTraining() {
        return priceTraining;
    }

    public void setPriceTraining(BigDecimal priceTraining) {
        this.priceTraining = priceTraining;
    }
    @Column
    public BigDecimal getPriceBathing() {
        return priceBathing;
    }

    public void setPriceBathing(BigDecimal priceBathing) {
        this.priceBathing = priceBathing;
    }
    @Column
    public BigDecimal getPriceCombing() {
        return priceCombing;
    }

    public void setPriceCombing(BigDecimal priceCombing) {
        this.priceCombing = priceCombing;
    }
    @Column
    public BigDecimal getPricePaws() {
        return pricePaws;
    }

    public void setPricePaws(BigDecimal pricePaws) {
        this.pricePaws = pricePaws;
    }
    @Column
    public BigDecimal getPriceEars() {
        return priceEars;
    }

    public void setPriceEars(BigDecimal priceEars) {
        this.priceEars = priceEars;
    }
    @Column
    public BigDecimal getPriceNails() {
        return priceNails;
    }

    public void setPriceNails(BigDecimal priceNails) {
        this.priceNails = priceNails;
    }

    public LocalDate getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }
    @Column
    public BigDecimal getPriceStayM() {
        return priceStayM;
    }

    public void setPriceStayM(BigDecimal priceStayM) {
        this.priceStayM = priceStayM;
    }
    @Column
    public BigDecimal getPriceStayL() {
        return priceStayL;
    }

    public void setPriceStayL(BigDecimal priceStayL) {
        this.priceStayL = priceStayL;
    }

    public BigDecimal getPriceDeworming() {
        return priceDeworming;
    }

    public void setPriceDeworming(BigDecimal priceDeworming) {
        this.priceDeworming = priceDeworming;
    }
}
