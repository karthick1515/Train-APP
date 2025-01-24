package com.securityservice.repository;

import com.securityservice.model.AdminDetails;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminDetailsRepository  extends MongoRepository<AdminDetails,String> {

  //  Optional<AdminDetails> findByUsername(String username);

    Optional<AdminDetails> findByEmail(String email);
}

