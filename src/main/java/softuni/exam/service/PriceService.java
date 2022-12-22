package softuni.exam.service;


import softuni.exam.models.dto.PriceDTO;
import softuni.exam.models.entity.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PriceService {
    List<Price> findAllPriceById();

    void addPrice(PriceDTO priceDTO);

    void removePriceById(Long id);

    Optional<Price> findById(Long id);

    void editPrice(BigDecimal priceOvernightStay,BigDecimal priceFood,BigDecimal priceTraining,BigDecimal priceBathing,BigDecimal priceCombing,BigDecimal pricePaws,BigDecimal priceEars,BigDecimal priceNails, Long id);
}
