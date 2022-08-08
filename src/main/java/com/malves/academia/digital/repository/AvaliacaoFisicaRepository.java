package com.malves.academia.digital.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malves.academia.digital.entity.AvaliacaoFisica;

@Repository
public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long> {

	/**
	 * @param dataDaAvaliacao: data da avaliação dos alunos
	 * @return lista com todas as avaliações físicas com a data da avaliação passada como parâmetro do método
	 */
	List<AvaliacaoFisica> findByDataDaAvaliacao(LocalDate localDate);
}
