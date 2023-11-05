package com.example.projet1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projet1.Services.StudentService;
import com.example.projet1.beans.Student;

@RestController
@RequestMapping("/api/Student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping
	private List<Student> getAll() {
		return studentService.findAll();
	}

	@GetMapping("/{id}")
	private ResponseEntity<?> getById(@PathVariable int id) {

		Student s = studentService.findById(id);
		if (s == null) {

			return new ResponseEntity<Object>("student avec ID " + id + " n exite pas", HttpStatus.BAD_REQUEST);

		} else {

			return ResponseEntity.ok(s);

		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		Student student = studentService.findById(id);

		if (student == null) {
			return new ResponseEntity<Object>("student avec ID " + id + " n exite pas", HttpStatus.BAD_REQUEST);
		} else {
			studentService.delete(student);
			return ResponseEntity.ok(" supression avec succes ");

		}
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentService.create(student);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student) {

		if (studentService.findById(id) == null) {

			return new ResponseEntity<Object>("Aucun student avec l'id : " + id, HttpStatus.BAD_REQUEST);

		} else {
			student.setId(id);
			return ResponseEntity.ok(studentService.create(student));
		}
	}

}
