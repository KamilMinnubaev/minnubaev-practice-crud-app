package ru.arapov.minnubaevpracticecrud.dto;

import ru.arapov.minnubaevpracticecrud.model.User;

public record UserDTO(
        Long id,
        String username,
        String email,
        String password
) {

    public static UserDTO from(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
