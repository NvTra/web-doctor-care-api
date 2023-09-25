package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Roles;
import com.tranv.webdoctorcareapi.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }
    //get Role By Id
    public Roles getRoleById(int theId) {
        Optional<Roles> result = rolesRepository.findById(theId);
        Roles roles;
        if (result.isPresent()) {
            roles = result.get();
        } else {
            throw new RuntimeException("không tìm thấy role");
        }
        return roles;
    }
}
