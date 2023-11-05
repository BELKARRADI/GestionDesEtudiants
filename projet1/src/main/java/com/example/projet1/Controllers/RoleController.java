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

import com.example.projet1.Services.RoleService;
import com.example.projet1.beans.Role;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping
	public Role createRole(@RequestBody Role role) {
		return roleService.create(role);

	}

	@GetMapping
	public List<Role> getAll() {
		return roleService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable int id) {

		Role r = roleService.findById(id);
		if (r == null) {

			return new ResponseEntity<Object>("Aucun role ayant l'id: " + id, HttpStatus.BAD_REQUEST);

		} else {
			return ResponseEntity.ok(r);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> getAll(@PathVariable int id) {

		Role r = roleService.findById(id);
		if (r == null) {

			return new ResponseEntity<Object>("Aucun role ayant l'id: " + id, HttpStatus.BAD_REQUEST);

		} else {
			roleService.delete(r);
			return ResponseEntity.ok("Role supprimé avec succes");
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRole(@PathVariable int id, @RequestBody Role role) {

		if (roleService.findById(id) == null) {

			return new ResponseEntity<Object>("Aucun role ayant l'id: " + id, HttpStatus.BAD_REQUEST);

		} else {
			role.setId(id);
			return ResponseEntity.ok(roleService.create(role));
		}

	}

}
