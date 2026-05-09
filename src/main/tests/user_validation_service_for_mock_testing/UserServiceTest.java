package user_validation_service_for_mock_testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private EmailService emailService;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    void shouldRegisterNewUser() {
        var email = "aleshaPopovich@gmail.com";
        when(userRepository.existsByEmail(email)).thenReturn(false);

        userService.registerUser(email);

        verify(userRepository, times(1)).save(userCaptor.capture());
        verify(emailService, times(1)).sendWelcomeEmail(email);

        var user = userCaptor.getValue();
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {

        var email = "aleshaPopovich@gmail.com";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> userService.registerUser(email))
                .withMessage("User already exists");

        verify(userRepository, never()).save(any(User.class));
        verify(emailService, never()).sendWelcomeEmail(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        var email = "";
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> userService.registerUser(email))
                .withMessage("Email is invalid");

        verify(userRepository, never()).existsByEmail(any());
        verify(userRepository, never()).save(any(User.class));
        verify(emailService, never()).sendWelcomeEmail(any());
    }

}