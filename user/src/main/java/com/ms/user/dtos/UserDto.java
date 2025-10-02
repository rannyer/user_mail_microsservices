package com.ms.user.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String nome, @Email String email) {

}
