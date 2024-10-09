package com.martin.projects.onelinker.persistence.repository;

import com.martin.projects.onelinker.persistence.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
