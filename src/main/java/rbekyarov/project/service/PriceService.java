package rbekyarov.project.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rbekyarov.project.models.dto.PriceDTO;
import rbekyarov.project.models.dto.restDto.PriceRestDTO;
import rbekyarov.project.models.entity.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PriceService {
    List<Price> findAllPriceById();

    void addPrice(PriceDTO priceDTO);

    void removePriceById(Long id);

    Optional<Price> findById(Long id);

    void editPrice(
            BigDecimal priceStayS,
            BigDecimal priceStayM,
            BigDecimal priceStayL,
                   BigDecimal priceFood,
            BigDecimal deworming,
                   BigDecimal priceTraining,
                   BigDecimal priceBathing,
                   BigDecimal priceCombing,
                   BigDecimal pricePaws,
                   BigDecimal priceEars,
                   BigDecimal priceNails,
            Double discountClientRegular,
            Double discountClientVip,
                   Long id);

    BigDecimal getFoodCurrentPrice();
    BigDecimal getDewormingCurrentPrice();

    BigDecimal getBathingCurrentPrice();

    BigDecimal getTrainingCurrentPrice();

    BigDecimal getCombingCurrentPrice();

    BigDecimal getEarsCurrentPrice();

    BigDecimal getPawsCurrentPrice();

    BigDecimal getNailsCurrentPrice();

    BigDecimal getCurrentPriceStayForCellS();
    BigDecimal getCurrentPriceStayForCellM();
    BigDecimal getCurrentPriceStayForCellL();
    Page<Price> findPaginated(Pageable pageable);

    List<PriceRestDTO> getAllPricesForRest();
}
