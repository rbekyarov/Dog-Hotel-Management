package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class Price extends BaseEntity{
  private BigDecimal priceOvernightStay;
  private BigDecimal priceFood;
  private BigDecimal priceTraining;
  private BigDecimal priceBathing;
  private BigDecimal priceCombing;
  private BigDecimal pricePaws;
  private BigDecimal priceEars;
  private BigDecimal priceNails;


    public Price() {
    }
    @Column
    public BigDecimal getPriceOvernightStay() {
        return priceOvernightStay;
    }

    public void setPriceOvernightStay(BigDecimal priceOvernightStay) {
        this.priceOvernightStay = priceOvernightStay;
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
}
