package user_validation_service_for_mock_testing;

public interface UserRepository {

    boolean existsByEmail(String email);
    void save(User user);
}
