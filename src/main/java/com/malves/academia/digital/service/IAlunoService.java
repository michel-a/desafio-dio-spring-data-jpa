package com.malves.academia.digital.service;

import java.util.List;

import com.malves.academia.digital.entity.Aluno;
import com.malves.academia.digital.entity.AvaliacaoFisica;
import com.malves.academia.digital.entity.form.AlunoForm;
import com.malves.academia.digital.entity.form.AlunoUpdateForm;

public interface IAlunoService {

	/**
	 * Retorna um Aluno que está no banco de dados de acordo com seu Id.
	 * 
	 * @param id - id do Aluno que será exibido.
	 * @return - Aluno de acordo com o Id fornecido.
	 */
	Aluno get(Long id);

	/**
	 * Retorna todos os Alunos que estão no banco de dados.
	 * 
	 * @return - Uma lista os Alunos que estão salvas no DB.
	 */
	List<Aluno> getAll(String dataDeNascimento);

	/**
	 * 
	 * @return lista das avaliações física do aluno
	 */
	List<AvaliacaoFisica> getAllAvaliacaoFisicaId(Long id);

	/**
	 * Cria um Aluno e salva no banco de dados.
	 * 
	 * @param form - formulário referente aos dados para criação de um Aluno no
	 *             banco de dados.
	 * @return - Aluno recém-criado.
	 */
	Aluno create(AlunoForm form);

	/**
	 * Atualiza o Aluno.
	 * 
	 * @param id         - id do Aluno que será atualizado.
	 * @param formUpdate - formulário referente aos dados necessários para
	 *                   atualização do Aluno no banco de dados.
	 * @return - Aluno recém-atualizado.
	 */
	Aluno update(Long id, AlunoUpdateForm formUpdate);

	/**
	 * Deleta um Aluno específico.
	 * 
	 * @param id - id do Aluno que será removido.
	 */
	Object delete(Long id);

}
