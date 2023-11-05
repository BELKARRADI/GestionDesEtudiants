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

import com.example.projet1.Services.FiliereService;
import com.example.projet1.beans.Filiere;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {

	@Autowired
	private FiliereService filiereService;

	@PostMapping("")

	public Filiere save(@RequestBody Filiere f) {

		f.setId(0);
		return filiereService.create(f);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id) {

		Filiere f = filiereService.findById(id);
		if (f == null) {

			return new ResponseEntity<Object>("Aucune filiere avce l'id :" + id, HttpStatus.BAD_REQUEST);

		} else {

			return ResponseEntity.ok(f);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable int id, @RequestBody Filiere filiere) {

		if (filiereService.findById(id) == null) {

			return new ResponseEntity<Object>("Aucune filiere avce l'id :" + id, HttpStatus.BAD_REQUEST);

		} else {

			filiere.setId(id);
			return ResponseEntity.ok(filiereService.update(filiere));
		}

	}

	@GetMapping("")
	public List<Filiere> getAll() {
		return filiereService.findAll();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {

		Filiere f = filiereService.findById(id);

		if (f == null) {
			return new ResponseEntity<Object>("Aucune filiere avec l id " + id, HttpStatus.BAD_REQUEST);
		} else {

			filiereService.delete(f);
			return ResponseEntity.ok("Filiere " + id + " supprimé avec succes");

		}

	}

}
