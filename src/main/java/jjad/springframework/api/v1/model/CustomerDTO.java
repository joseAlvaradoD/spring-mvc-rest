package jjad.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerDTO {

    @Schema(hidden = true)
    private Long id;
    @Schema(title = "This is customer's first name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstname;
    @Schema(title = "This is customer's last name", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastname;
    private String customerURL;
}
