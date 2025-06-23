package br.com.financialsys.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialsys.backend.dto.LoginDTO;
import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.service.TokenService;
import br.com.financialsys.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserService userService = new UserService();

    @Autowired
    private TokenService tokenService;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/admin/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO login) {
        boolean valid = userService.authenticateUser(login);
        if (valid) {
            try {
                User user = userService.findUserByUsername(login.getUsername());
                String token = tokenService.generateToken(user);
                return ResponseEntity.ok(token);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
