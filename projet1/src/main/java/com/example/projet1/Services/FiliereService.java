package com.example.projet1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet1.IDao.Dao;
import com.example.projet1.beans.Filiere;
import com.example.projet1.repositories.FiliereRepository;

@Service
public class FiliereService implements Dao<Filiere> {

	@Autowired
	private FiliereRepository filiereRepository;

	@Override
	public Filiere create(Filiere o) {
		return filiereRepository.save(o);
	}

	@Override
	public boolean delete(Filiere o) {
		try {

			filiereRepository.delete(o);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Filiere update(Filiere o) {
		return filiereRepository.save(o);
	}

	@Override
	public List<Filiere> findAll() {
		return filiereRepository.findAll();
	}

	@Override
	public Filiere findById(int id) {
		return filiereRepository.findById(id).orElse(null);
	}

}
