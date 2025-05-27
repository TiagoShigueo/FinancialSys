package br.com.financialsys.backend.datainit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.UserRepository;

@Component
public class UserDataInitializer implements ApplicationRunner {
    @Autowired
    private UserRepository userRepository;

    String username = "demo";
    String rawPassword = "demo";
    String role = "User";

    @Override
    public void run(ApplicationArguments args) {
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
            System.out.println("Usuário demo já existe!");
        }
    }
}
