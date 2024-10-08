package com.mouad.syntax.service.impl;

import com.mouad.syntax.model.Admin;
import com.mouad.syntax.repository.AdminRepository;
import com.mouad.syntax.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {


    private final AdminRepository adminRepository;

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdmin(Long id) {
        return adminRepository.findById(id);
    }
}
