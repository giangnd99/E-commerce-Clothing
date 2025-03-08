package com.springboot.asm.fpoly_asm_springboot.dto.response.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OutboundUserResponse {

    String id;
    String email;
    boolean verifiedEmail;
    String name;
    String givenName;
    String familyName;
    String picture;
    String locale;
}
