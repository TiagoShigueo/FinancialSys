package br.com.financialsys.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.dto.LoginDTO;
import br.com.financialsys.backend.dto.UserDTO;
import br.com.financialsys.backend.mapper.UserMapper;
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

    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> dto = users.stream().map(UserMapper::toDTO).toList();
        return dto;
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
