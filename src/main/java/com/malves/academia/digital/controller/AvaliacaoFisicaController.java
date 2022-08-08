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

import com.malves.academia.digital.entity.AvaliacaoFisica;
import com.malves.academia.digital.entity.form.AvaliacaoFisicaForm;
import com.malves.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import com.malves.academia.digital.service.impl.AvaliacaoFisicaServiceImpl;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

	@Autowired
	private AvaliacaoFisicaServiceImpl service;
	
	private final String ID_NAO_ENCONTRADO= "O id do aluno não foi encontrado.";
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> get(@PathVariable Long id) {
		AvaliacaoFisica avaliacao = service.get(id);
		if (avaliacao != null) {
			return ResponseEntity.status(HttpStatus.OK).body(avaliacao);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
	}
	
	@GetMapping
	public ResponseEntity<List<AvaliacaoFisica>> getAll(@RequestParam(value = "dataDaAvaliacao", required = false) String dataDaAvaliacao) {
		return ResponseEntity.ok().body(service.getAll(dataDaAvaliacao));
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody AvaliacaoFisicaForm form) {
		AvaliacaoFisica avaliacao = service.create(form);
		if (avaliacao != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid AvaliacaoFisicaUpdateForm formUpdate) {
		AvaliacaoFisica avaliacao = service.update(id, formUpdate);
		if (avaliacao != null) {
			return ResponseEntity.status(HttpStatus.OK).body(avaliacao);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NAO_ENCONTRADO);
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
