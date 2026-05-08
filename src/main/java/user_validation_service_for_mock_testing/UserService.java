package user_validation_service_for_mock_testing;

public class UserService {

    private final EmailService emailService;
    private final UserRepository userRepository;

    public UserService(EmailService emailService, UserRepository userRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public void registerUser(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is invalid");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("User already exists");
        }

        User user = new User(email);
        userRepository.save(user);

        emailService.sendWelcomeEmail(email);
    }

}
