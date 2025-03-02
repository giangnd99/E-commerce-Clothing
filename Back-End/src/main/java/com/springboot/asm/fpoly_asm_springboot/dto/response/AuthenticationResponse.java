package com.springboot.asm.fpoly_asm_springboot.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults( level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    boolean authenticated;
    String token;
}
