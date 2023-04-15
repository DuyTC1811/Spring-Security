package com.example.springsecurity.repositorys;



import com.example.springsecurity.models.ERole;
import com.example.springsecurity.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IRoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
