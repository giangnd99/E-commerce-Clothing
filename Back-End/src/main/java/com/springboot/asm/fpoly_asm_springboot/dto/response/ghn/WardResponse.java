package com.springboot.asm.fpoly_asm_springboot.dto.response.ghn;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WardResponse {

    Integer district_id;
    String ward_name;
    String ward_code;
}
