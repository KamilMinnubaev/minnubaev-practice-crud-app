package ru.arapov.minnubaevpracticecrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arapov.minnubaevpracticecrud.dto.UserRequestDTO;
import ru.arapov.minnubaevpracticecrud.dto.UserDTO;
import ru.arapov.minnubaevpracticecrud.model.User;
import ru.arapov.minnubaevpracticecrud.repo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(UserRequestDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Пользователь с такой почтой уже существует");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Пользователь с таким логином уже существует");
        }

        User savedUser = User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(savedUser);

        return UserDTO.from(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::from)
                .toList();
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id {} не найден" + id));
        return UserDTO.from(user);
    }

    public UserDTO updateUserById(Long id, UserRequestDTO request) {
        User user = userRepository.findUserById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с id {} не найден" + id));

        user.setUsername(request.username());
        user.setEmail(request.email());

        if (request.password() != null && !request.password().isEmpty()) {
            user.setPassword(request.password());
        }

        User updatedUser = userRepository.save(user);
        return UserDTO.from(updatedUser);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Пользователь не найден");
        }

        userRepository.deleteById(id);
    }
}
