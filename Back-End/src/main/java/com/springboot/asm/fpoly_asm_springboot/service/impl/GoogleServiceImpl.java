package com.springboot.asm.fpoly_asm_springboot.service.impl;

import com.springboot.asm.fpoly_asm_springboot.constant.Google;
import com.springboot.asm.fpoly_asm_springboot.dto.request.google.ExchangeTokenRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.response.UserGGResponse;
import com.springboot.asm.fpoly_asm_springboot.dto.response.google.OutboundUserResponse;
import com.springboot.asm.fpoly_asm_springboot.repository.httpclient.OutboundIdentityClient;
import com.springboot.asm.fpoly_asm_springboot.repository.httpclient.OutboundUserClient;
import com.springboot.asm.fpoly_asm_springboot.service.AuthenticationService;
import com.springboot.asm.fpoly_asm_springboot.service.GoogleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleServiceImpl implements GoogleService {

    private final OutboundIdentityClient outboundIdentityClient;
    private final OutboundUserClient outboundUserClient;
    private final AuthenticationService authenticationService;

    public String getAccessToken(String code) {
        var response = outboundIdentityClient.exchangeToken(ExchangeTokenRequest.builder()
                .clientId(Google.CLIENT_ID)
                .clientSecret(Google.CLIENT_SECRET)
                .redirectUri(Google.REDIRECT_URI)
                .grantType(Google.GRANT_TYPE)
                .code(code)
                .build());

        log.info("Access token: {}", response.getAccessToken());
        return response.getAccessToken();
    }

    @Override
    public UserGGResponse getUserResponse(String code) throws Exception {
        var accessToken = getAccessToken(code);
        OutboundUserResponse userInfo = outboundUserClient.getUserInfo("json", accessToken);

        return authenticationService.getOrCreateUser(userInfo);
    }
}
