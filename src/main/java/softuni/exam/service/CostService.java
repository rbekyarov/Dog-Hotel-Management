package softuni.exam.service;


import softuni.exam.models.dto.CostDTO;
import softuni.exam.models.dto.VendorDTO;
import softuni.exam.models.entity.Cost;
import softuni.exam.models.entity.Vendor;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CostService {
    List<Cost> findAllCost();

    void addCost(CostDTO costDTO, HttpSession session);

    void removeCostById(Long id);

    Optional<Cost> findById(Long id);

    void editCost(Long vendorId, String description, BigDecimal amount, String dateCost, HttpSession session, Long id);

}
