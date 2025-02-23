package com.example.flyway.controller;

import com.example.flyway.model.MasterUser;
import com.example.flyway.repository.MasterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class MasterUserController {

    @Autowired
    private MasterUserRepository masterUserRepository;

    @GetMapping("/")
    public List<MasterUser> getAllUser() {
        return masterUserRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<MasterUser> getUserById(@PathVariable Long id) {
        return masterUserRepository.findById(id);
    }
}
