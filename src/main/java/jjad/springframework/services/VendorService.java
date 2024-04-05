package jjad.springframework.services;

import jjad.springframework.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    VendorDTO getVendorById(Long id);

    List<VendorDTO> getAllVendors();

    VendorDTO saveVendor(VendorDTO vendorDTO);

    VendorDTO patchVendor(VendorDTO vendorDTO);

    void deleteVendorById(Long id);

}
