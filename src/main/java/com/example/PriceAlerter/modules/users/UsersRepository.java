package com.example.PriceAlerter.modules.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
    boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email); 
}
