package com.example.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.infrastructure.persistence.entity.PersonEntity;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByName(String name);
}
