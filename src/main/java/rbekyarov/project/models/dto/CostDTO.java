package rbekyarov.project.models.dto;

import rbekyarov.project.models.entity.Vendor;

import javax.validation.constraints.NotNull;
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

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @NotNull
    public String getDateCost() {
        return dateCost;
    }

    public void setDateCost(String dateCost) {
        this.dateCost = dateCost;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
