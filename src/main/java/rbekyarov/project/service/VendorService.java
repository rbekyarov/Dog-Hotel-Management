package rbekyarov.project.service;


import rbekyarov.project.models.entity.Vendor;
import rbekyarov.project.models.dto.VendorDTO;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public interface VendorService {
    List<Vendor> findAllVendor();

    void addVendor(VendorDTO vendorDTO, HttpSession session);

    void removeVendorById(Long id);

    Optional<Vendor> findById(Long id);

    void editVendor(String name, String country, Long cityId, String address, String vatNumber, String email, HttpSession session, Long id);

}