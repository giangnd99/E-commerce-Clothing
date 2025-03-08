package com.springboot.asm.fpoly_asm_springboot.controller;

import com.nimbusds.jose.JOSEException;
import com.springboot.asm.fpoly_asm_springboot.constant.Google;
import com.springboot.asm.fpoly_asm_springboot.constant.Role;
import com.springboot.asm.fpoly_asm_springboot.dto.request.ApiResponse;
import com.springboot.asm.fpoly_asm_springboot.dto.request.AuthenticationRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.request.IntrospectRequest;
import com.springboot.asm.fpoly_asm_springboot.dto.response.AuthenticationResponse;
import com.springboot.asm.fpoly_asm_springboot.dto.response.IntrospectResponse;

import com.springboot.asm.fpoly_asm_springboot.dto.response.UserGGResponse;
import com.springboot.asm.fpoly_asm_springboot.entity.User;
import com.springboot.asm.fpoly_asm_springboot.service.*;
import lombok.RequiredArgsConstructor;
import com.springboot.asm.fpoly_asm_springboot.dto.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final GoogleService googleService;
    private final UserService userService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticationResponseApiResponse(@RequestBody AuthenticationRequest request) {

        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder().
                result(result).
                build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {

        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<?> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {

        authenticationService.logout(request);

        return ApiResponse.<String>builder().
                result("Account is logged out successfully ").
                build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        return ApiResponse.<AuthenticationResponse>builder().
                result(authenticationService.refreshToken(request)).
                build();
    }

    @PostMapping("/callback")
    public ApiResponse<?> handleGoogleCallback(@RequestBody Map<String, String> requestData) {
        String code = requestData.get("code");

        if (code == null) {
            return ApiResponse.<String>builder()
                    .code(900)
                    .message("Lỗi: Không tìm thấy mã code!")
                    .build();
        }

        try {
            UserGGResponse user = googleService.getUserResponse(code);

            return ApiResponse.<String>builder()
                    .result(user.getToken())
                    .build();

        } catch (Exception e) {
            return ApiResponse.<String>builder()
                    .code(901)
                    .message("Lỗi: Không tìm được người dùng từ Google API!")
                    .build();
        }
    }
}
