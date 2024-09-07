package com.example.lobbying.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByTagsIdIn(List<Long> tagIds);

    Optional<User> findByEmail(String email);
}
