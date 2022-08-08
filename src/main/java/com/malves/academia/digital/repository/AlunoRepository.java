package com.malves.academia.digital.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.malves.academia.digital.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	/**
	 * @param dataDeNascimento: data de nascimento dos alunos
	 * @return lista com todos os alunos com a data de nacimento passada como parâmetro do método
	 */
	List<Aluno> findByDataDeNascimento(LocalDate dataDeNascimento);
}
