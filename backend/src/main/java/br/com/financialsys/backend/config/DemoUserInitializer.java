package br.com.financialsys.backend.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.UserRepository;

// @Profile("dev")
@Configuration
public class DemoUserInitializer {
    @Bean
    public ApplicationRunner initDemoUser(UserRepository userRepository) {
        return args -> {
            String username = "demo";
            String rawPassword = "123456";
            String role = "USER";

            if (userRepository.findByName(username).isEmpty()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encryptedPassword = encoder.encode(rawPassword);

                User demoUser = new User();
                demoUser.setName(username);
                demoUser.setPassword(encryptedPassword);
                demoUser.setRole(role);

                userRepository.save(demoUser);

                System.out.println("Usuário demo criado com sucesso!");
            } else {
                System.out.println("Usuário já existe!");
            }
        };
    }
}
