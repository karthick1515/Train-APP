package com.securityservice.repository;

import com.securityservice.model.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserInfo,String> {
 //   Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByEmail(String email);
    Optional<UserInfo> findByUsername(String username);

}

