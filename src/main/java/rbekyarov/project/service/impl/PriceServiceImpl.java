package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.PriceDTO;
import rbekyarov.project.models.entity.Price;
import rbekyarov.project.repository.PriceRepository;
import rbekyarov.project.service.PriceService;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        // set dateCreated
        price.setDateCreate(LocalDate.now());
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
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        priceRepository.editPrice(
                priceOvernightStay,
                priceFood,
                priceTraining,
                priceBathing,
                priceCombing,
                pricePaws,
                priceEars,
                priceNails,
                id,
                dateEdit);
    }

    @Override
    public Double getFoodCurrentPrice() {
        BigDecimal foodCurrentPrice = priceRepository.getFoodCurrentPrice(getLastPricesId());
        return foodCurrentPrice.doubleValue();
    }

    @Override
    public Double getBathingCurrentPrice() {
        BigDecimal foodCurrentPrice = priceRepository.getBathingCurrentPrice(getLastPricesId());
        return foodCurrentPrice.doubleValue();
    }

    @Override
    public Double getTrainingCurrentPrice() {
        BigDecimal trainingCurrentPrice = priceRepository.getTrainingCurrentPrice(getLastPricesId());
        return trainingCurrentPrice.doubleValue();
    }

    @Override
    public Double getCombingCurrentPrice() {
        BigDecimal combingCurrentPrice = priceRepository.getCombingCurrentPrice(getLastPricesId());
        return combingCurrentPrice.doubleValue();
    }

    @Override
    public Double getEarsCurrentPrice() {
        BigDecimal earsCurrentPrice = priceRepository.getEarsCurrentPrice(getLastPricesId());
        return earsCurrentPrice.doubleValue();
    }

    @Override
    public Double getPawsCurrentPrice() {
        BigDecimal pawsCurrentPrice = priceRepository.getPawsCurrentPrice(getLastPricesId());
        return pawsCurrentPrice.doubleValue();
    }

    @Override
    public Double getNailsCurrentPrice() {
        BigDecimal nailsCurrentPrice = priceRepository.getNailsCurrentPrice(getLastPricesId());
        return nailsCurrentPrice.doubleValue();
    }

    @Override
    public Double getOvernightStayCurrentPrice() {
        BigDecimal overnightStayCurrentPrice = priceRepository.getOvernightStayCurrentPrice(getLastPricesId());
        return overnightStayCurrentPrice.doubleValue();
    }

    public int getLastPricesId(){
        List<Price> allPrices = priceRepository.findAllPricesById();
        return allPrices.size();
    }
}
