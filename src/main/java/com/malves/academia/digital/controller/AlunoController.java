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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malves.academia.digital.entity.Aluno;
import com.malves.academia.digital.entity.form.AlunoForm;
import com.malves.academia.digital.entity.form.AlunoUpdateForm;
import com.malves.academia.digital.service.impl.AlunoServiceImpl;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoServiceImpl service;
	
	private final String ID_NAO_ENCONTRADO= "O id do aluno não foi encontrado.";
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> get(@PathVariable Long id) {
		Aluno aluno = service.get(id);
		if (aluno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(aluno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> getAll(@RequestParam(value = "dataDeNascimento", required = false) String dataDeNascimento) {
		return ResponseEntity.ok().body( service.getAll(dataDeNascimento) );
	}
	
	@GetMapping("/avaliacoes/{id}")
	public ResponseEntity<Object> getAllAvaliacaoFisicaId(@PathVariable Long id) {
		Aluno aluno = service.get(id);
		if (aluno != null) {
			return ResponseEntity.ok().body( service.getAllAvaliacaoFisicaId(id) );
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
	
	@PostMapping
	public ResponseEntity<Aluno> create(@RequestBody @Valid AlunoForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body( service.create(form) );
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid AlunoUpdateForm form) {
		Aluno aluno = service.update(id, form);
		if (aluno != null) {
			return ResponseEntity.status(HttpStatus.OK).body(aluno);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Object resposta = service.delete(id);
		if (resposta != null) {
			return ResponseEntity.ok().body(resposta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
	}
}
