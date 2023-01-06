package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CostDTO;
import softuni.exam.models.dto.VendorDTO;
import softuni.exam.models.entity.Cost;
import softuni.exam.models.entity.User;
import softuni.exam.models.entity.Vendor;
import softuni.exam.repository.CostRepository;
import softuni.exam.service.CostService;
import softuni.exam.service.UserService;

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

    public CostServiceImpl(CostRepository costRepository, ModelMapper modelMapper, UserService userService) {
        this.costRepository = costRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
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
    }

    @Override
    public void removeCostById(Long id) {
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
