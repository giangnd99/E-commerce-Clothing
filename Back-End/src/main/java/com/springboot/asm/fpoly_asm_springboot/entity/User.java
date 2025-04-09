package com.springboot.asm.fpoly_asm_springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Email not null")
    @Email(message = "Email invalid")
    private String email;

    @NotBlank(message = "Password not null")
    private String password;

    private String fullName;

    private String avatar;

    private LocalDate birthday;

    private String phone;

    private Boolean gender;

    @NotNull(message = "Role can't null")
    private Boolean role;

}