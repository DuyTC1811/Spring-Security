package com.example.springsecurity.repositorys;

import com.example.springsecurity.models.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserInfoRepository extends MongoRepository<UserInfo, String> {
    Optional<UserInfo> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
