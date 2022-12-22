package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.PriceDTO;
import softuni.exam.models.entity.Price;
import softuni.exam.repository.PriceRepository;
import softuni.exam.service.PriceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final ModelMapper modelMapper;

    public PriceServiceImpl(PriceRepository priceRepository, ModelMapper modelMapper) {
        this.priceRepository = priceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Price> findAllPriceById() {
        return priceRepository.findAllPricesById();
    }

    @Override
    public void addPrice(PriceDTO priceDTO) {
        Price price = modelMapper.map(priceDTO, Price.class);

        priceRepository.save(price);
    }

    @Override
    public void removePriceById(Long id) {
        priceRepository.deleteById(id);
    }

    @Override
    public Optional<Price> findById(Long id) {
        return priceRepository.findById(id);
    }

    @Override
    public void editPrice(BigDecimal priceOvernightStay, BigDecimal priceFood, BigDecimal priceTraining, BigDecimal priceBathing, BigDecimal priceCombing, BigDecimal pricePaws, BigDecimal priceEars, BigDecimal priceNails, Long id) {
        priceRepository.editPrice(
                priceOvernightStay,
                priceFood,
                priceTraining,
                priceBathing,
                priceCombing,
                pricePaws,
                priceEars,
                priceNails,
                id);
    }
}
