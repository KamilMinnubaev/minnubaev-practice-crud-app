package ru.arapov.minnubaevpracticecrud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Укажите логин")
        @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов")
        String username,

        @NotBlank(message = "Укажите почту")
        @Email(message = "Неверный формат")
        String email,

        @NotBlank(message = "Укажите пароль")
        @Size(min = 6, message = "Пароль минимум 6 символов")
        String password
) {
}
