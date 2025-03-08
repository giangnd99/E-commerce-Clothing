package com.springboot.asm.fpoly_asm_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FpolyAsmSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpolyAsmSpringbootApplication.class, args);
    }

}
