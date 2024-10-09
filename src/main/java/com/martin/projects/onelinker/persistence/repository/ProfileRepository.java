package com.martin.projects.onelinker.persistence.repository;

import com.martin.projects.onelinker.persistence.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
