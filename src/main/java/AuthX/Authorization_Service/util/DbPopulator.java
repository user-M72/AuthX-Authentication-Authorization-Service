package AuthX.Authorization_Service.util;

import AuthX.Authorization_Service.entity.Role;
import AuthX.Authorization_Service.entity.User;
import AuthX.Authorization_Service.repository.UserRepository;
import AuthX.Authorization_Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DbPopulator implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (userRepository.findByRole(Role.Admin).isEmpty()){

                User admin = new User();
                admin.setUsername("admin");
                admin.setRole(Role.Admin);
                admin.setEmail("admin@admin.com");
                admin.setPassword(passwordEncoder.encode("admin"));

                userRepository.save(admin);
            }

        if (userRepository.findByRole(Role.User).isEmpty()){

            User user = new User();
            user.setUsername("user");
            user.setEmail("user@user.com");
            user.setRole(Role.User);
            user.setPassword(passwordEncoder.encode("user"));

            userRepository.save(user);
        }

    }
}

