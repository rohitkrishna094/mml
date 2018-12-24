package com.rohit.mml.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rohit.mml.model.Role;
import com.rohit.mml.model.RoleName;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(RoleName roleName);
}