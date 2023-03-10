package rbekyarov.project.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rbekyarov.project.models.dto.VendorDTO;
import rbekyarov.project.models.dto.restDto.CityRestThinDTO;
import rbekyarov.project.models.dto.restDto.VendorRestDTO;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.repository.VendorRepository;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.VendorService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public VendorServiceImpl(VendorRepository vendorRepository, ModelMapper modelMapper, UserService userService) {
        this.vendorRepository = vendorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public List<Vendor> findAllVendor() {
        return vendorRepository.findAllVendor();
    }

    @Override
    public void addVendor(VendorDTO vendorDTO, HttpSession session) {
        Vendor vendor = modelMapper.map(vendorDTO, Vendor.class);

        //get and set Author
        vendor.setAuthor(userService.getAuthorFromSession(session));
        // set dateCreated
        vendor.setDateCreate(LocalDate.now());
        vendorRepository.save(vendor);
    }

    @Override
    public void removeVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public void editVendor(String name, String country, Long cityId, String address, String vatNumber, String email, HttpSession session, Long id) {
        User editAuthor = userService.getAuthorFromSession(session);
        Long editAuthorId = editAuthor.getId();
        //set dateEdit
        LocalDate dateEdit = LocalDate.now();

        vendorRepository.editVendor(
                name,
                country,
                cityId,
                address,
                vatNumber,
                email,
                editAuthorId,
                dateEdit,
                id);
    }

    @Override
    public List<Vendor> listVendorByVatNumber(String vendorVatNumber) {
        return vendorRepository.listVendorByVatNumber(vendorVatNumber);
    }

    @Override
    public List<Vendor> listVendorByName(String vendorName) {
        return vendorRepository.listVendorByName(vendorName);
    }

    @Override
    public Page<Vendor> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Vendor> list;
        List<Vendor> vendors = vendorRepository.findAllVendor();
        if (vendors.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, vendors.size());
            list = vendors.subList(startItem, toIndex);
        }

        Page<Vendor> vendorsPage = new PageImpl<Vendor>(list, PageRequest.of(currentPage, pageSize), vendors.size());

        return vendorsPage;
    }

    @Override
    public List<VendorRestDTO> getAllVendorForRest() {
        return vendorRepository.findAll().
                stream().
                map(this::map).
                toList();
    }
    private VendorRestDTO map(Vendor vendor) {
        VendorRestDTO vendorRestDTO = new VendorRestDTO();

        vendorRestDTO.setId(vendor.getId());
        vendorRestDTO.setAddress(vendor.getAddress());
        vendorRestDTO.setName(vendor.getName());
        vendorRestDTO.setCountry(vendor.getCountry());
        vendorRestDTO.setEmail(vendor.getEmail());
        vendorRestDTO.setVatNumber(vendor.getVatNumber());
        CityRestThinDTO cityRestThinDTO = new CityRestThinDTO();
        cityRestThinDTO.setName(vendor.getCity().getName());
        vendorRestDTO.setCity(cityRestThinDTO);
        return vendorRestDTO;
    }


}
