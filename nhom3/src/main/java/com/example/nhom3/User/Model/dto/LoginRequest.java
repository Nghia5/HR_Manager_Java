package com.example.nhom3.User.Model.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Username không được để trống") String username,

        @NotBlank(message = "Mật khẩu không được để trống") String password) {
}