package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
