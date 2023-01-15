package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.Collections;
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
    public List<Cost> findAllCostByDesc() {
        return costRepository.findAllCostByDesc();
    }

    @Override
    public void addCost(CostDTO costDTO, HttpSession session) {
        Cost cost = modelMapper.map(costDTO, Cost.class);
        //set DateCost
        String date = costDTO.getDateCost();
        if(date.isEmpty()){
            cost.setDateCost(null);
        }else {
            cost.setDateCost(formatterLocalDate(date));
        }

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
    public void editCost(Long vendorId, String description, String invoiceNo, BigDecimal amount, String dateCost, HttpSession session, Long id) {
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
        LocalDate localDate =null;
        if(dateCost.isEmpty()){
            localDate = null;
        }else {
            localDate = formatterLocalDate(dateCost);
        }


        companyService.editBalance(currentNewBalance.subtract(amount));
        costRepository.editCost(
                vendorId,
                description,
                invoiceNo,
                amount,
                localDate,
                editAuthorId,
                dateEdit,
                id);


    }

    @Override
    public Page<Cost> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Cost> list;
        List<Cost> costs = costRepository.findAllCostByDesc();
        if (costs.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, costs.size());
            list = costs.subList(startItem, toIndex);
        }

        Page<Cost> costsPage = new PageImpl<Cost>(list, PageRequest.of(currentPage, pageSize), costs.size());

        return costsPage;
    }

    @Override
    public List<Cost> findCostByVendor(String name) {
        return costRepository.findCostByVendor(name);
    }

    //convert String to LocalDate
    LocalDate formatterLocalDate(String dateDto) {
        //1.01.23 г.  ->23-01-01
        //11.01.23 г. ->23-01-11
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        if(dateDto.contains(".")){
            if(dateDto.length()==10){
                sb.append("2");
                sb.append("0");
                sb.append(dateDto.charAt(5));
                sb.append(dateDto.charAt(6));
                sb.append("-");
                sb.append(dateDto.charAt(2));
                sb.append(dateDto.charAt(3));
                sb.append("-");
                sb.append("0");
                sb.append(dateDto.charAt(0));

            }else if(dateDto.length()==11){
                sb.append("2");
                sb.append("0");
                sb.append(dateDto.charAt(6));
                sb.append(dateDto.charAt(7));
                sb.append("-");
                sb.append(dateDto.charAt(3));
                sb.append(dateDto.charAt(4));
                sb.append("-");
                sb.append(dateDto.charAt(0));
                sb.append(dateDto.charAt(1));
            }
            String s = sb.toString();

            LocalDate localDate = LocalDate.parse(s, formatter);
            return localDate;
        }else {
            LocalDate localDate = LocalDate.parse(dateDto, formatter);
            return localDate;
        }
    }
}
