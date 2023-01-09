package rbekyarov.project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.Cost;
import rbekyarov.project.models.dto.CostDTO;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CostService {
    List<Cost> findAllCost();

    void addCost(CostDTO costDTO, HttpSession session);

    void removeCostById(Long id);

    Optional<Cost> findById(Long id);

    void editCost(Long vendorId, String description, BigDecimal amount, String dateCost, HttpSession session, Long id);
    Page<Cost> findPaginated(Pageable pageable);
}
