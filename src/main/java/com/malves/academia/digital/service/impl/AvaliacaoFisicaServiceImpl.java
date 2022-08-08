package com.malves.academia.digital.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malves.academia.digital.entity.Aluno;
import com.malves.academia.digital.entity.AvaliacaoFisica;
import com.malves.academia.digital.entity.form.AvaliacaoFisicaForm;
import com.malves.academia.digital.entity.form.AvaliacaoFisicaUpdateForm;
import com.malves.academia.digital.infra.utils.JavaTimeUtils;
import com.malves.academia.digital.repository.AlunoRepository;
import com.malves.academia.digital.repository.AvaliacaoFisicaRepository;
import com.malves.academia.digital.service.IAvaliacaoFisicaService;

@Service
public class AvaliacaoFisicaServiceImpl implements IAvaliacaoFisicaService {

	@Autowired
	private AvaliacaoFisicaRepository repository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public AvaliacaoFisica get(Long id) {
		Optional<AvaliacaoFisica> avaliacaoOptional = repository.findById(id);
		if (avaliacaoOptional.isPresent()) {
			return avaliacaoOptional.get();
		}
		return null;
	}

	@Override
	public List<AvaliacaoFisica> getAll(String dataDaAvaliacao) {
		if (dataDaAvaliacao == null) {
			return repository.findAll();
		} else {
			LocalDate localDate = LocalDate.parse(dataDaAvaliacao, JavaTimeUtils.LOCAL_DATE_FORMATTER);
			return repository.findByDataDaAvaliacao(localDate);
		}
	}
	
	@Override
	public AvaliacaoFisica create(AvaliacaoFisicaForm form) {
		AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica();
		
		Optional<Aluno> alunoOptional = alunoRepository.findById(form.getAlunoId());
		
		if (alunoOptional.isPresent()) {
			Aluno aluno = alunoOptional.get();
			
			avaliacaoFisica.setAluno(aluno);
			avaliacaoFisica.setAltura(form.getAltura());
			avaliacaoFisica.setPeso(form.getPeso());
			return repository.save(avaliacaoFisica); 
		}
		
		return null;
	}

	@Override
	public AvaliacaoFisica update(Long id, AvaliacaoFisicaUpdateForm formUpdate) {
		Optional<AvaliacaoFisica> avaliacaoOptional = repository.findById(id);
		if (avaliacaoOptional.isPresent()) {
			AvaliacaoFisica avaliacao = avaliacaoOptional.get();
			avaliacao.setPeso(formUpdate.getPeso());
			avaliacao.setAltura(formUpdate.getAltura());
			return repository.save(avaliacao);
		}
		return null;
	}

	@Override
	public Object delete(Long id) {
		Optional<AvaliacaoFisica> avaliacaoOptional = repository.findById(id);
		if (avaliacaoOptional.isPresent()) {
			repository.deleteById(avaliacaoOptional.get().getId());
			return "Avaliação Física excluída com sucesso!!";
		}
		return null;
	}
	
}
