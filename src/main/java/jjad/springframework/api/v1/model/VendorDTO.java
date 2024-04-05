package jjad.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    @Schema(hidden = true)
    private Long id;
    @Schema(title = "This is vendor's name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorURL;

}
