package com.kabir.productApp.controller;

import com.kabir.productApp.dto.UserDto;
import com.kabir.productApp.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return usersService.getUserById(id);
    }
}
