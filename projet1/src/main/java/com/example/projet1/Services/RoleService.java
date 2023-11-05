package com.example.projet1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet1.IDao.Dao;
import com.example.projet1.beans.Role;
import com.example.projet1.repositories.RoleRepository;

@Service
public class RoleService implements Dao<Role> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role create(Role o) {

		return roleRepository.save(o);
	}

	@Override
	public boolean delete(Role o) {
		try {
			roleRepository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Role update(Role o) {
		return roleRepository.save(o);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findById(int id) {
		return roleRepository.findById(id).orElse(null);
	}

}
