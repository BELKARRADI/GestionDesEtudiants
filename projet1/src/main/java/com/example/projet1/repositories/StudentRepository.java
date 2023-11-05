package com.example.projet1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projet1.beans.Student;

public interface StudentRepository  extends JpaRepository<Student, Integer>{

}
