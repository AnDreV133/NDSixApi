package com.example.NDSixApi.repositories;

import com.example.NDSixApi.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByLogin(String login);
    Optional<User> findOneByLoginAndPassword(String login, String password);

    Optional<User> findOneById(Long id);
}
