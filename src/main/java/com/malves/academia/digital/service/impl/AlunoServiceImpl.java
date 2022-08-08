package com.malves.academia.digital.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malves.academia.digital.entity.Aluno;
import com.malves.academia.digital.entity.AvaliacaoFisica;
import com.malves.academia.digital.entity.form.AlunoForm;
import com.malves.academia.digital.entity.form.AlunoUpdateForm;
import com.malves.academia.digital.infra.utils.JavaTimeUtils;
import com.malves.academia.digital.repository.AlunoRepository;
import com.malves.academia.digital.service.IAlunoService;

@Service
public class AlunoServiceImpl implements IAlunoService {
	
	@Autowired
	private AlunoRepository repository;
	
	@Override
	public Aluno get(Long id) {
		Optional<Aluno> alunoOptional = repository.findById(id);
		if (alunoOptional.isPresent()) {
			return alunoOptional.get();
		}
		return null;
	}

	@Override
	public List<Aluno> getAll(String dataDeNascimento) {
		if (dataDeNascimento == null) {
			return repository.findAll();
		} else {
			LocalDate localDate = LocalDate.parse(dataDeNascimento, JavaTimeUtils.LOCAL_DATE_FORMATTER);
			return repository.findByDataDeNascimento(localDate);
		}
	}
	
	@Override
	public List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id) {
		Aluno aluno = repository.getById(id);
		return aluno.getAvaliacoes();
	}

	@Transactional
	@Override
	public Aluno create(AlunoForm form) {
		Aluno aluno = new Aluno();
		aluno.setNome(form.getNome());
		aluno.setCpf(form.getCpf());
		aluno.setBairro(form.getBairro());
		aluno.setDataDeNascimento(form.getDataDeNascimento());
		
		return repository.save(aluno);
	}

	@Transactional
	@Override
	public Aluno update(Long id, AlunoUpdateForm formUpdate) {
		Optional<Aluno> alunoOptional = repository.findById(id);
		if (alunoOptional.isPresent()) {
			Aluno aluno = alunoOptional.get();
			aluno.setNome(formUpdate.getNome());
			aluno.setBairro(formUpdate.getBairro());
			aluno.setDataDeNascimento(formUpdate.getDataDeNascimento());
			
			repository.save(aluno);
			return aluno;
		}
		return null;
	}

	@Transactional
	@Override
	public Object delete(Long id) {
		Optional<Aluno> alunoOptional = repository.findById(id);
		if (alunoOptional.isPresent()) {
			repository.deleteById(id);
			return "Aluno excluído com sucesso!";
		}
		return null;
	}

}
