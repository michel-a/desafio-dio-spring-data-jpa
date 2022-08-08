package com.malves.academia.digital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malves.academia.digital.entity.Matricula;
import com.malves.academia.digital.entity.form.MatriculaForm;
import com.malves.academia.digital.service.impl.MatriculaServiceImpl;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
	
	@Autowired
	private MatriculaServiceImpl service;
	
	private final String ID_NAO_ENCONTRADO= "O id do aluno não foi encontrado.";
	
	@GetMapping
	public ResponseEntity<List<Matricula>> getAll(@RequestParam(value = "bairro", required = false) String bairro) {
		return ResponseEntity.ok().body( service.getAll(bairro) );
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> get(@PathVariable Long id) {
		Matricula matricula = service.get(id);
		if (matricula != null) {
			return ResponseEntity.ok().body(matricula);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody MatriculaForm form) {
		Matricula matricula = service.create(form);
		if (matricula != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Object resposta = service.delete(id);
		if (resposta != null) {
			return ResponseEntity.ok().body(resposta);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
}
