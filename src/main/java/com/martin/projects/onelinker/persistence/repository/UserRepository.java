package com.martin.projects.onelinker.persistence.repository;

import com.martin.projects.onelinker.persistence.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameIgnoreCase(String username);

  Optional<User> findByEmailIgnoreCase(String username);
}
