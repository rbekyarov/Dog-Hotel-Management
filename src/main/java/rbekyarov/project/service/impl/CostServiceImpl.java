package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.service.CompanyService;
import rbekyarov.project.models.dto.CostDTO;
import rbekyarov.project.models.entity.Cost;
import rbekyarov.project.repository.CostRepository;
import rbekyarov.project.service.CostService;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CostServiceImpl implements CostService {
    private final CostRepository costRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CompanyService companyService;

    public CostServiceImpl(CostRepository costRepository, ModelMapper modelMapper, UserService userService, CompanyService companyService) {
        this.costRepository = costRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.companyService = companyService;
    }

    @Override
    public List<Cost> findAllCost() {
        return costRepository.findAllCost();
    }

    @Override
    public void addCost(CostDTO costDTO, HttpSession session) {
        Cost cost = modelMapper.map(costDTO, Cost.class);
        //set DateCost
        String date = costDTO.getDateCost();
        cost.setDateCost(convertStringToLocalDate(date));
        //get and set Author
        cost.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        cost.setDateCreate(LocalDate.now());
        costRepository.save(cost);
        //change Company Balance
        BigDecimal currentBalance = companyService.getCurrentBalance();
        BigDecimal amountCost = costDTO.getAmount();
        companyService.editBalance(currentBalance.subtract(amountCost));
    }

    @Override
    public void removeCostById(Long id) {
        //change Company Balance
        Optional<Cost> costOptional = costRepository.findById(id);
        Cost cost = costOptional.get();
        BigDecimal amount = cost.getAmount();
        BigDecimal currentBalance = companyService.getCurrentBalance();
        companyService.editBalance(currentBalance.add(amount));

        costRepository.deleteById(id);
    }

    @Override
    public Optional<Cost> findById(Long id) {
        return costRepository.findById(id);
    }

    @Override
    public void editCost(Long vendorId, String description, BigDecimal amount, String dateCost, HttpSession session, Long id) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        //Company Balance
        //change Company Balance - return amount, old value
        Optional<Cost> costOptional = costRepository.findById(id);
        Cost cost = costOptional.get();
        BigDecimal oldAmount = cost.getAmount();
        BigDecimal currentBalance = companyService.getCurrentBalance();
        companyService.editBalance(currentBalance.add(oldAmount));
        //change Company Balance -charge new amount
        BigDecimal currentNewBalance = companyService.getCurrentBalance();


        companyService.editBalance(currentNewBalance.subtract(amount));
        costRepository.editCost(
                vendorId,
                description,
                amount,
                convertStringToLocalDate(dateCost),
                editAuthorId,
                dateEdit,
                id);


    }

    //convert String to LocalDate
    LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);

        return localDate;
    }
}
