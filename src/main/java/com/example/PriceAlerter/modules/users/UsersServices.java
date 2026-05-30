package com.example.PriceAlerter.modules.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.http.HttpStatus;

@Service
@Transactional
public class UsersServices {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User createUser(User user) {
        user.setId(null);
        return usersRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setPassword(updatedUser.getPassword());
        return usersRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        usersRepository.delete(existingUser);
    }
}
