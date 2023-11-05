package com.example.projet1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projet1.beans.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
