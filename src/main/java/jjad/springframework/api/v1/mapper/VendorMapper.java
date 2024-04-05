package jjad.springframework.api.v1.mapper;

import jjad.springframework.api.v1.controller.CustomerController;
import jjad.springframework.api.v1.controller.VendorController;
import jjad.springframework.api.v1.model.VendorDTO;
import jjad.springframework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendorURL", expression = "java(\""+ VendorController.BASE_URL +"\"+vendor.getId())")
    VendorDTO vendorToVendorDTO(Vendor vendor);
    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);

}
