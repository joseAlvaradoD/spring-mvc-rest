package jjad.springframework.api.v1.controller;

import jjad.springframework.api.v1.model.VendorDTO;
import jjad.springframework.services.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static jjad.springframework.api.v1.controller.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerTest {

    @MockBean //provided by Spring Context
    VendorService vendorService;

    @Autowired
    MockMvc mockMvc; //provided by Spring Context

    VendorDTO vendorDTO_1;
    VendorDTO vendorDTO_2;

    @BeforeEach
    public void setUp() throws Exception {
        vendorDTO_1 = new VendorDTO(null, "Vendor 1", VendorController.BASE_URL + "/1");
        vendorDTO_2 = new VendorDTO(null, "Vendor 2", VendorController.BASE_URL + "/2");
    }

    @Test
    public void getVendorList() throws Exception {
        List<VendorDTO> vendorListDTO = Arrays.asList(vendorDTO_1, vendorDTO_2);

        given(vendorService.getAllVendors()).willReturn(vendorListDTO);

        mockMvc.perform(get(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getVendorById() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO_1);

        mockMvc.perform(get(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void createNewVendor() throws Exception {

        given(vendorService.saveVendor(vendorDTO_1)).willReturn(vendorDTO_1);

        mockMvc.perform(post(VendorController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void updateVendor() throws Exception {

        given(vendorService.saveVendor(any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(put(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void patchVendor() throws Exception {
        given(vendorService.patchVendor(any(VendorDTO.class))).willReturn(vendorDTO_1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO_1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO_1.getName())));
    }

    @Test
    public void deleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());

    }

}
