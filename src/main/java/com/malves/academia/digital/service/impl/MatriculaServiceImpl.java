package com.malves.academia.digital.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malves.academia.digital.entity.Aluno;
import com.malves.academia.digital.entity.Matricula;
import com.malves.academia.digital.entity.form.MatriculaForm;
import com.malves.academia.digital.repository.AlunoRepository;
import com.malves.academia.digital.repository.MatriculaRepository;
import com.malves.academia.digital.service.IMatriculaService;

@Service
public class MatriculaServiceImpl implements IMatriculaService {
	
	@Autowired
	private MatriculaRepository repository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public Matricula get(Long id) {
		Optional<Matricula> matriculaOptional = repository.findById(id);
		if (matriculaOptional.isPresent()) {
			return matriculaOptional.get();
		}
		return null;
	}

	@Override
	public List<Matricula> getAll(String bairro) {
		if (bairro == null) {
			return repository.findAll();
		} else {
			return repository.findByAlunoBairro(bairro);
		}
	}

	@Override
	public Matricula create(MatriculaForm form) {
		Aluno aluno = null;
		Optional<Aluno> alunoOptional = alunoRepository.findById(form.getAlunoId());
		if(alunoOptional.isPresent()) {
			aluno = alunoOptional.get();
			Matricula matricula = new Matricula();
			matricula.setAluno(aluno);
			return repository.save(matricula);
		}
		return null;
	}

	@Override
	public Object delete(Long id) {
		Optional<Matricula> matriculaOptional = repository.findById(id);
		if (matriculaOptional.isPresent()) {
			repository.deleteById(matriculaOptional.get().getId());
			return "Matrícula excluída com sucesso!!";
		}
		return null;
	}

}
