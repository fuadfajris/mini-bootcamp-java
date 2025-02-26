package com.example.flyway.controller;

import com.example.flyway.dto.response.UserBalanceDTO;
import com.example.flyway.model.MasterAccount;
import com.example.flyway.service.MasterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class MasterAccountController {
    @Autowired
    private MasterAccountService masterAccountService;

    @GetMapping()
    public List<MasterAccount> getAllAccount() {
        return masterAccountService.getAllAccount();
    }

    @GetMapping("/{id}")
    public Optional<MasterAccount> getAccountById(@PathVariable Long id) {
        return masterAccountService.getAccountById(id);
    }

    @GetMapping("/{id}/balances")
    public Object getUserBalances(@PathVariable Long id) {
        return masterAccountService.getAccountBalanceById(id);
    }
}
