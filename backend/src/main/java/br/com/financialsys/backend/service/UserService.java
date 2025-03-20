package br.com.financialsys.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(Long idUser, String name, String password) {
        User user = new User(idUser, name, password);
        userRepository.save(user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean authenticateUser(String name, String password) {
        Optional<User> userOptional = userRepository.findByName(name);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
