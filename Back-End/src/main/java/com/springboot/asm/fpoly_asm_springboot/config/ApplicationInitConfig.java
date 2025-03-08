package com.springboot.asm.fpoly_asm_springboot.config;

import com.springboot.asm.fpoly_asm_springboot.constant.Role;
import com.springboot.asm.fpoly_asm_springboot.entity.User;
import com.springboot.asm.fpoly_asm_springboot.repository.primary.UserRepository;
import com.springboot.asm.fpoly_asm_springboot.service.ProductService;
import com.springboot.asm.fpoly_asm_springboot.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, ProductService productService) {
        log.info("Initializing application.....");
        productService.addData();
        return args -> {
            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
                User user = User.builder().
                        email("admin@gmail.com").
                        password(passwordEncoder.encode("admin")).
                        role(Role.ADMIN).
                        build();
                userRepository.save(user);
                log.warn("Admin user added with default password: admin. Please change!");
            }
            log.info("Application initialization completed .....");
        };
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .codecs(conf -> conf.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();
    }

    @Bean
    PageUtil pageUtil() {
        return new PageUtil();
    }
}
