package com.example.PriceAlerter.modules.users;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.PriceAlerter.modules.users.dto.CreateUserDto;

@Service
@Transactional
public class UsersServices {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServices(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        final int maxPageSize = 50;
        Pageable effectivePageable = pageable;
        if (pageable.getPageSize() > maxPageSize) {
            effectivePageable = PageRequest.of(pageable.getPageNumber(), maxPageSize, pageable.getSort());
        }
        return usersRepository.findAll(effectivePageable);
    }

    public User getUserById(UUID id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPhoneNumber(createUserDto.getPhoneNumber());
        user.setPassword(createUserDto.getPassword());
        return usersRepository.save(user);
    }

    public User updateUser(UUID id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setPassword(updatedUser.getPassword());
        return usersRepository.save(existingUser);
    }

    public void deleteUser(UUID id) {
        User existingUser = getUserById(id);
        usersRepository.delete(existingUser);
    }
}
