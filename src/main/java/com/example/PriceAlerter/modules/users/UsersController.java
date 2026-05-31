package com.example.PriceAlerter.modules.users;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.PriceAlerter.modules.users.dto.CreateUserDto;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersServices usersServices;

    @Autowired
    public UsersController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping
    public Page<User> getUsers(Pageable pageable) {
        return usersServices.getAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        return usersServices.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody CreateUserDto createUserDto) {
        return usersServices.createUser(createUserDto);
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return usersServices.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        usersServices.deleteUser(id);
    }
}
