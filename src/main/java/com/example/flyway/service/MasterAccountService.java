package com.example.flyway.service;

import com.example.flyway.dto.response.MessageDTO;
import com.example.flyway.dto.response.UserBalanceDTO;
import com.example.flyway.model.MasterAccount;
import com.example.flyway.model.MasterUser;
import com.example.flyway.repository.MasterAccountRepository;
import com.example.flyway.repository.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MasterAccountService {
    @Autowired
    private MasterAccountRepository masterAccountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MasterUserRepository masterUserRepository;

    public List<MasterAccount> getAllAccount() {
        return masterAccountRepository.findAll();
    }

    public Optional<MasterAccount> getAccountById(Long id) {
        return masterAccountRepository.findById(id);
    }

    public Object getAccountBalanceById(Long id) {
        Optional<MasterUser> optionalUser = masterUserRepository.findById(id);
        if(optionalUser.isEmpty()) {
            MessageDTO obj = new MessageDTO();
            obj.setMessage("User Not Found");
            return obj;
        }

        String sql = "SELECT COALESCE(SUM(balance),0) " +
                "FROM BACKOFFICE.MASTER_ACCOUNT_FUAD " +
                "WHERE user_id = ? ";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, id);

        UserBalanceDTO userBalanceDTO = new UserBalanceDTO();
        MasterUser user = optionalUser.get();
        userBalanceDTO.setName(user.getFullName());
        userBalanceDTO.setBalance(balance);
        return userBalanceDTO;
    }
}
