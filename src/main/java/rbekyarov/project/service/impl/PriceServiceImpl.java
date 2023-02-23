package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.PriceDTO;
import rbekyarov.project.models.dto.restDto.BreedRestDTO;
import rbekyarov.project.models.dto.restDto.PriceRestDTO;
import rbekyarov.project.models.entity.Breed;
import rbekyarov.project.models.entity.Price;
import rbekyarov.project.repository.PriceRepository;
import rbekyarov.project.service.PriceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
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
        return priceRepository.findAllPrices();
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
    public void editPrice(BigDecimal priceStayS,BigDecimal priceStayM,BigDecimal priceStayL, BigDecimal priceFood,BigDecimal priceDeworming, BigDecimal priceTraining, BigDecimal priceBathing, BigDecimal priceCombing, BigDecimal pricePaws, BigDecimal priceEars, BigDecimal priceNails,Double discountClientRegular,Double discountClientVip, Long id) {
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();
        priceRepository.editPrice(
                priceStayS,
                priceStayM,
                priceStayL,
                priceFood,
                priceDeworming,
                priceTraining,
                priceBathing,
                priceCombing,
                pricePaws,
                priceEars,
                priceNails,
                discountClientRegular,
                discountClientVip,
                id,
                dateEdit);
    }

    @Override
    public BigDecimal getFoodCurrentPrice() {
        return priceRepository.getFoodCurrentPrice(getLastPricesId());
    }
    @Override
    public BigDecimal getDewormingCurrentPrice() {
        return priceRepository.getDewormingCurrentPrice(getLastPricesId());
    }
    @Override
    public BigDecimal getBathingCurrentPrice() {
        return priceRepository.getBathingCurrentPrice(getLastPricesId());
    }

    @Override
    public BigDecimal getTrainingCurrentPrice() {
        return priceRepository.getTrainingCurrentPrice(getLastPricesId());
    }

    @Override
    public BigDecimal getCombingCurrentPrice() {
        return priceRepository.getCombingCurrentPrice(getLastPricesId());
    }

    @Override
    public BigDecimal getEarsCurrentPrice() {
        return priceRepository.getEarsCurrentPrice(getLastPricesId());
    }

    @Override
    public BigDecimal getPawsCurrentPrice() {
        return priceRepository.getPawsCurrentPrice(getLastPricesId());
    }

    @Override
    public BigDecimal getNailsCurrentPrice() {
        return priceRepository.getNailsCurrentPrice(getLastPricesId());
    }

    @Override
    public BigDecimal getCurrentPriceStayForCellS() {
        return priceRepository.getCurrentPriceStayForCellS(getLastPricesId());
    }

    @Override
    public BigDecimal getCurrentPriceStayForCellM() {
        return priceRepository.getCurrentPriceStayForCellM(getLastPricesId());
    }

    @Override
    public BigDecimal getCurrentPriceStayForCellL() {
        return priceRepository.getCurrentPriceStayForCellL(getLastPricesId());
    }

    @Override
    public Page<Price> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Price> list;
        List<Price> prices = priceRepository.findAllPrices();
        if (prices.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, prices.size());
            list = prices.subList(startItem, toIndex);
        }

        Page<Price> pricesPage = new PageImpl<Price>(list, PageRequest.of(currentPage, pageSize), prices.size());

        return pricesPage;
    }

    @Override
    public List<PriceRestDTO> getAllPricesForRest() {
        return priceRepository.findAll().
                stream().
                map(this::map).
                toList();
    }
    private PriceRestDTO map(Price price) {

         PriceRestDTO priceRestDTO = new PriceRestDTO();

         priceRestDTO.setId(price.getId());
         priceRestDTO.setPriceBathing(price.getPriceBathing());
         priceRestDTO.setPriceCombing(price.getPriceCombing());
         priceRestDTO.setPriceDeworming(price.getPriceDeworming());
         priceRestDTO.setPriceTraining(price.getPriceTraining());
         priceRestDTO.setPriceEars(price.getPriceEars());
         priceRestDTO.setPriceNails(price.getPriceNails());
         priceRestDTO.setPriceFood(price.getPriceFood());
         priceRestDTO.setPricePaws(price.getPricePaws());
         priceRestDTO.setPriceStayL(price.getPriceStayL());
         priceRestDTO.setPriceStayM(price.getPriceStayM());
         priceRestDTO.setPriceStayS(price.getPriceStayS());
         priceRestDTO.setDiscountClientRegular(price.getDiscountClientRegular());
         priceRestDTO.setDiscountClientVip(price.getDiscountClientVip());
         return priceRestDTO;
    }

    public Long getLastPricesId(){
        List<Price> allPrices = priceRepository.findAllPrices();
        return Long.valueOf(allPrices.size());
    }
}
