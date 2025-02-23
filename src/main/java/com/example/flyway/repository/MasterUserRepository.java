package com.example.flyway.repository;

import com.example.flyway.model.MasterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterUserRepository extends JpaRepository<MasterUser, Long> {
}
