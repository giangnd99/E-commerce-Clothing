package com.springboot.asm.fpoly_asm_springboot.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.asm.fpoly_asm_springboot.dto.response.UserGGResponse;
import com.springboot.asm.fpoly_asm_springboot.entity.User;
import com.springboot.asm.fpoly_asm_springboot.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationService authenticationService;

    @Value("${jwt.signerKey}")
    private String signerKey;

    private final String[] PUBLIC_URLS = {"/users"
            , "/auth/token", "/auth/introspect"
            , "/auth/logout", "/auth/refresh"
            , "/reset-password"
            , "/reset-password/*", "/auth/callback"};
    private final String[] PUBLIC_PRODUCT_URLS = {"/products", "/products/*"
            , "/products/search/category/*"
            , "/categories", "/categories/*"
            , "/swagger-ui", "/swagger-ui/**"
            , "/payment/vn-pay-callback"
            , "/payment/vn-pay-callback/*"
            , "/reset-password", "/reset-password/*"
            , "/reviews", "/reviews/*"
    };
    public static final String[] PRODUCT_URLS = {
            "/products/search/category/{categoryId}",
            "/products/search/price",
            "/products/search/category/{categoryId}/price",
            "/products/search/category/{categoryId}/size",
            "/products/search/category/{categoryId}/filter",
            "/products/sort/name/asc/{categoryId}",
            "/products/sort/name/desc/{categoryId}",
            "/products/search/category/{categoryId}/sort/price/asc",
            "/products/search/category/{categoryId}/sort/price/desc",
            "/products/{productId}",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.POST, PUBLIC_URLS).permitAll().
                        requestMatchers(HttpMethod.GET, PUBLIC_PRODUCT_URLS).permitAll().
                        requestMatchers(HttpMethod.GET, PRODUCT_URLS).permitAll().
                        anyRequest().authenticated()
        );

        httpSecurity.oauth2Login(oauth2 -> oauth2
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("http://localhost:5173/login-success");
                })
        );

        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())));
//Để mở cors
        httpSecurity.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return converter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HmacSHA512");

        return NimbusJwtDecoder.withSecretKey(secretKeySpec).
                macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");

        corsConfiguration.addAllowedHeader("*");

        corsConfiguration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
