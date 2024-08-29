package com.example.lobbying.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByTagsIdIn(List<Long> tagIds);

}
