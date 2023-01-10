package rbekyarov.project.models.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PriceDTO {

    private BigDecimal priceStayS;
    private BigDecimal priceFood;
    private BigDecimal priceTraining;
    private BigDecimal priceBathing;
    private BigDecimal priceCombing;
    private BigDecimal pricePaws;
    private BigDecimal priceEars;
    private BigDecimal priceNails;

    public PriceDTO() {
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceStayS() {
        return priceStayS;
    }

    public void setPriceStayS(BigDecimal priceStayS) {
        this.priceStayS = priceStayS;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceFood() {
        return priceFood;
    }

    public void setPriceFood(BigDecimal priceFood) {
        this.priceFood = priceFood;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceTraining() {
        return priceTraining;
    }

    public void setPriceTraining(BigDecimal priceTraining) {
        this.priceTraining = priceTraining;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceBathing() {
        return priceBathing;
    }

    public void setPriceBathing(BigDecimal priceBathing) {
        this.priceBathing = priceBathing;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceCombing() {
        return priceCombing;
    }

    public void setPriceCombing(BigDecimal priceCombing) {
        this.priceCombing = priceCombing;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPricePaws() {
        return pricePaws;
    }

    public void setPricePaws(BigDecimal pricePaws) {
        this.pricePaws = pricePaws;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceEars() {
        return priceEars;
    }

    public void setPriceEars(BigDecimal priceEars) {
        this.priceEars = priceEars;
    }

    @NotNull(message = "Field cannot be empty")
    @DecimalMin(value = "0", message = "Accepts only positive numbers")
    public BigDecimal getPriceNails() {
        return priceNails;
    }

    public void setPriceNails(BigDecimal priceNails) {
        this.priceNails = priceNails;
    }
}
