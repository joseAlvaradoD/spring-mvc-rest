package jjad.springframework.services;

import jjad.springframework.api.v1.mapper.VendorMapper;
import jjad.springframework.api.v1.model.VendorDTO;
import jjad.springframework.domain.Vendor;
import jjad.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository
                .findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(Collectors.toList());
        return vendorDTOS;
    }

    @Override
    public VendorDTO saveVendor(VendorDTO vendorDTO) {
        return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendorMapper.vendorDTOtoVendor(vendorDTO)));
    }

    @Override
    public VendorDTO patchVendor(VendorDTO vendorDTO) {
        return vendorRepository.findById(vendorDTO.getId())
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }
                    return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

}
