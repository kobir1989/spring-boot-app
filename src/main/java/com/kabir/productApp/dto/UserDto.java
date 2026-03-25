package com.kabir.productApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "User name is required")
    private String fullName;
    @NotBlank(message = "Email address is required")
    private String email;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String role;
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
