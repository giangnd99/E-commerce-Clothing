package com.springboot.asm.fpoly_asm_springboot.repository.httpclient;

import com.springboot.asm.fpoly_asm_springboot.dto.request.google.ExchangeTokenRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.response.google.ExchangeTokenResonse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "outbound-identity" ,url = "https://oauth2.googleapis.com" )
public interface OutboundIdentityClient {
    @PostMapping(value = "/token" ,produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ExchangeTokenResonse exchangeToken(@QueryMap ExchangeTokenRequest request);
}
