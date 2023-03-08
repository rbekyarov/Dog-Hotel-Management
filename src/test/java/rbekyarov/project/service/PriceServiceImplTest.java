package rbekyarov.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import rbekyarov.project.models.dto.PriceDTO;
import rbekyarov.project.models.dto.restDto.PriceRestDTO;
import rbekyarov.project.models.entity.Price;
import rbekyarov.project.repository.PriceRepository;
import rbekyarov.project.service.impl.PriceServiceImpl;
import org.mockito.exceptions.misusing.UnnecessaryStubbingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ModelMapper modelMapper;

    private PriceServiceImpl priceService;
    private PriceDTO priceDTO;
    private Price price;
    private PriceRestDTO priceRestDTO;
    private List<Price> prices;
    @BeforeEach
    public void setUp() {
        priceService = new PriceServiceImpl(priceRepository, modelMapper);
        priceDTO = new PriceDTO();
        priceDTO.setPriceFood(new BigDecimal("15.00"));

        price = new Price();
        price.setId(1L);
        price.setPriceFood(new BigDecimal("20.00"));
        price.setPriceDeworming(new BigDecimal("50.00"));
        price.setDateCreate(LocalDate.now());

        priceRestDTO = new PriceRestDTO();
        priceRestDTO.setId(1L);
        priceRestDTO.setPriceFood(new BigDecimal("20.00"));

        prices = new ArrayList<>();
        prices.add(price);
    }

    @Test
    public  void testFindAllPriceById() {
        List<Price> expectedPrices = new ArrayList<>();
        expectedPrices.add(new Price());
        when(priceRepository.findAllPrices()).thenReturn(expectedPrices);

        List<Price> actualPrices = priceService.findAllPriceById();

        assertEquals(expectedPrices, actualPrices);
        verify(priceRepository, times(1)).findAllPrices();
    }

    @Test
    public void addPriceShouldSavePriceToRepository() {
        PriceDTO priceDTO = new PriceDTO();
        Price price = new Price();
        price.setDateCreate(LocalDate.now());
        when(modelMapper.map(priceDTO, Price.class)).thenReturn(price);

        priceService.addPrice(priceDTO);

        verify(priceRepository).save(price);
    }


    @Test
    public  void testRemovePriceById() {
        Long id = 1L;

        priceService.removePriceById(id);

        verify(priceRepository, times(1)).deleteById(id);
    }

    @Test
    public  void testFindById() {
        Long id = 1L;
        Price expectedPrice = new Price();
        when(priceRepository.findById(id)).thenReturn(Optional.of(expectedPrice));

        Optional<Price> actualPrice = priceService.findById(id);

        assertTrue(actualPrice.isPresent());
        assertEquals(expectedPrice, actualPrice.get());
        verify(priceRepository, times(1)).findById(id);
    }

    @Test
  public   void testEditPrice() {
        Long id = 1L;
        BigDecimal priceStayS = BigDecimal.ONE;
        BigDecimal priceStayM = BigDecimal.TEN;
        BigDecimal priceStayL = BigDecimal.ZERO;
        BigDecimal priceFood = BigDecimal.valueOf(5);
        BigDecimal priceDeworming = BigDecimal.valueOf(10);
        BigDecimal priceTraining = BigDecimal.valueOf(15);
        BigDecimal priceBathing = BigDecimal.valueOf(20);
        BigDecimal priceCombing = BigDecimal.valueOf(25);
        BigDecimal pricePaws = BigDecimal.valueOf(30);
        BigDecimal priceEars = BigDecimal.valueOf(35);
        BigDecimal priceNails = BigDecimal.valueOf(40);
        Double discountClientRegular = 0.05;
        Double discountClientVip = 0.10;
        LocalDate dateEdit = LocalDate.now();

        priceService.editPrice(priceStayS, priceStayM, priceStayL, priceFood, priceDeworming, priceTraining, priceBathing, priceCombing, pricePaws, priceEars, priceNails, discountClientRegular, discountClientVip, id);

        verify(priceRepository, times(1)).editPrice(priceStayS, priceStayM, priceStayL, priceFood, priceDeworming, priceTraining, priceBathing, priceCombing, pricePaws, priceEars, priceNails, discountClientRegular, discountClientVip, id, dateEdit);
    }

    @Test
    public void getFoodCurrentPrice_ShouldReturnPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getFoodCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getFoodCurrentPrice();

        // Assert
        verify(priceRepository).getFoodCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }

    @Test
    public void testGetDewormingCurrentPrice() {

        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getDewormingCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getDewormingCurrentPrice();

        // Assert
        verify(priceRepository).getDewormingCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);

    }
    @Test
    public void testGetBathingCurrentPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getBathingCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getBathingCurrentPrice();

        // Assert
        verify(priceRepository).getBathingCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetTrainingCurrentPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getTrainingCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getTrainingCurrentPrice();

        // Assert
        verify(priceRepository).getTrainingCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetCombingCurrentPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getCombingCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getCombingCurrentPrice();

        // Assert
        verify(priceRepository).getCombingCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetEarsCurrentPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getEarsCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getEarsCurrentPrice();

        // Assert
        verify(priceRepository).getEarsCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetPawsCurrentPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getPawsCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getPawsCurrentPrice();

        // Assert
        verify(priceRepository).getPawsCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetNailsCurrentPrice() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getNailsCurrentPrice(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getNailsCurrentPrice();

        // Assert
        verify(priceRepository).getNailsCurrentPrice(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetCurrentPriceStayForCellS() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getCurrentPriceStayForCellS(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getCurrentPriceStayForCellS();

        // Assert
        verify(priceRepository).getCurrentPriceStayForCellS(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetCurrentPriceStayForCellM() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getCurrentPriceStayForCellM(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getCurrentPriceStayForCellM();

        // Assert
        verify(priceRepository).getCurrentPriceStayForCellM(anyLong());
        assertEquals(expectedPrice, result);
    }
    @Test
    public void testGetCurrentPriceStayForCellL() {
        // Arrange
        BigDecimal expectedPrice = BigDecimal.valueOf(10);
        when(priceRepository.getCurrentPriceStayForCellL(anyLong())).thenReturn(expectedPrice);

        // Act
        BigDecimal result = priceService.getCurrentPriceStayForCellL();

        // Assert
        verify(priceRepository).getCurrentPriceStayForCellL(anyLong());
        assertEquals(expectedPrice, result);
    }

}


