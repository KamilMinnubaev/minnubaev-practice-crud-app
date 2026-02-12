package ru.arapov.minnubaevpracticecrud.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.arapov.minnubaevpracticecrud.model.User;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getCreatedAt());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        User testUser = new User(
                1L,
                "minnubaev",
                "minnubaev@example.com",
                "password123",
                now
        );

        assertEquals(1L, testUser.getId());
        assertEquals("minnubaev", testUser.getUsername());
        assertEquals("minnubaev@example.com", testUser.getEmail());
        assertEquals("password123", testUser.getPassword());
        assertEquals(now, testUser.getCreatedAt());
    }

    @Test
    void testUserWithNullValues() {
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);
        user.setPassword(null);
        user.setCreatedAt(null);

        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getCreatedAt());
    }

    @Test
    void testEdgeCases() {
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");

        assertEquals("", user.getUsername());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPassword());

        String longString = "a".repeat(1000);
        user.setUsername(longString);
        user.setEmail(longString + "@test.com");
        user.setPassword(longString);

        assertEquals(1000, user.getUsername().length());
        assertEquals(1000 + 9, user.getEmail().length());
        assertEquals(1000, user.getPassword().length());

        user.setId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, user.getId());

        user.setId(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, user.getId());

        user.setId(0L);
        assertEquals(0L, user.getId());
    }

    @Test
    void testDateTimeHandling() {
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);

        assertEquals(now, user.getCreatedAt());

        LocalDateTime later = now.plusDays(1);
        user.setCreatedAt(later);

        assertEquals(later, user.getCreatedAt());
        assertNotEquals(now, user.getCreatedAt());
    }

    @Test
    void testChainedSetters() {
        user.setId(10L);
        user.setUsername("minnubaev");
        user.setEmail("minnubaev@test.com");
        user.setPassword("chain123");

        assertAll("Проверка цепочки установки значений",
                () -> assertEquals(10L, user.getId()),
                () -> assertEquals("minnubaev", user.getUsername()),
                () -> assertEquals("minnubaev@test.com", user.getEmail()),
                () -> assertEquals("chain123", user.getPassword())
        );
    }
}