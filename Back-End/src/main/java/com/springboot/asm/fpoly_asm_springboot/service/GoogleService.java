package com.springboot.asm.fpoly_asm_springboot.service;

import com.springboot.asm.fpoly_asm_springboot.dto.request.google.ExchangeTokenRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.response.UserGGResponse;
import com.springboot.asm.fpoly_asm_springboot.dto.response.UserResponse;
import com.springboot.asm.fpoly_asm_springboot.dto.response.google.ExchangeTokenResonse;

public interface GoogleService {

    UserGGResponse getUserResponse(String accessToken) throws Exception;
}
