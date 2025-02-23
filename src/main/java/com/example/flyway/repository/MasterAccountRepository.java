package com.example.flyway.repository;

import com.example.flyway.dto.response.UserBalanceDTO;
import com.example.flyway.model.MasterAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterAccountRepository extends JpaRepository<MasterAccount, Long> {

    @Query(value = "SELECT user_id, SUM(balance) AS totalBalance " +
            "FROM BACKOFFICE_XE.MASTER_ACCOUNT " +
            "WHERE user_id = :id " +
            "GROUP BY user_id",
            nativeQuery = true)
    Optional<UserBalanceDTO> getUserBalanceSummaryByUserId(@Param("id") Long id);
}

