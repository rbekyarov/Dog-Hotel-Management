package rbekyarov.project.models.dto.restDto;

import rbekyarov.project.models.entity.Vendor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;


public class CostRestDTO {
    private Long id;
    private VendorRestThinDTO vendor;
    private String description;
    private String invoiceNo;
    private BigDecimal amount;
    private String dateCost;


    public CostRestDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VendorRestThinDTO getVendor() {
        return vendor;
    }

    public void setVendor(VendorRestThinDTO vendor) {
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
