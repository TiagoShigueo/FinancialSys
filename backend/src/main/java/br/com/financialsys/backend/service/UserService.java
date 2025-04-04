package br.com.financialsys.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.LoginDTO;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean authenticateUser(LoginDTO login) {
        Optional<User> userOptional = userRepository.findByName(login.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(login.getPassword(), user.getPassword());
        }
        return false;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
