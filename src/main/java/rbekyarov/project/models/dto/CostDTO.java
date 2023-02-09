package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.Vendor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class CostDTO {
    private Vendor vendor;
    private String description;
    private String invoiceNo;
    private BigDecimal amount;
    private String dateCost;


    public CostDTO() {
    }

    @NotNull
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, message = "Content length must be over 3 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Field cannot be empty")
    @Digits(integer = 10, fraction = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @NotBlank(message = "Field cannot be empty")
    public String getDateCost() {
        return dateCost;
    }

    public void setDateCost(String dateCost) {
        this.dateCost = dateCost;
    }
    @NotNull(message = "Field cannot be empty")
    @Size(min = 3, message = "Content length must be over 3 characters!")
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
