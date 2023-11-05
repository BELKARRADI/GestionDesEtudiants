package com.example.projet1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet1.IDao.Dao;
import com.example.projet1.beans.Student;
import com.example.projet1.repositories.StudentRepository;

@Service
public class StudentService implements Dao<Student>{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student create(Student o) {
		return studentRepository.save(o);
	}

	@Override
	public boolean delete(Student o) {
		try {
			 studentRepository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Student update(Student o) {
		return studentRepository.save(o);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(int id) {
		return studentRepository.findById(id).orElse(null);
	}

}
