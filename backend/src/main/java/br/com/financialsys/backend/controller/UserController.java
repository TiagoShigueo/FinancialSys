package br.com.financialsys.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialsys.backend.model.User;
import br.com.financialsys.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService = new UserService();

    @PostMapping("/createUser")
    public User createUser(@RequestParam String name, @RequestParam String password) {
        return userService.createUser(null, name, password);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/auth")
    public boolean authenticateUser(@RequestParam String name, @RequestParam String password) {
        return userService.authenticateUser(name, password);
    }
}
