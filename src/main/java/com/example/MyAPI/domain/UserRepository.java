package com.example.MyAPI.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
   User findDistinctByLogin(String login);
   User findDistinctById(int id);
   Iterable<User> findAllByNameContainingIgnoreCase (String name);

}
