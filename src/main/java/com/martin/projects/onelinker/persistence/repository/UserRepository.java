package com.martin.projects.onelinker.persistence.repository;

import com.martin.projects.onelinker.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
