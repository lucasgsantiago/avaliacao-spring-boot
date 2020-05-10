package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.EditarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.helpers.ResourceNotFoundException;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.requests.EstudantesRequest;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteResult;

public interface EstudanteService {

	EstudanteListResult buscarEstudantes(EstudantesRequest request);

	List<EstudanteResult> buscarEstudantes();

	void cadastrarEstudante(@Valid AdicionarEstudanteCommand command) throws BusinessException;

	EstudanteResult buscarEstudante(Long estudanteId) throws ResourceNotFoundException;

	void atualizarEstudante(@Valid EditarEstudanteCommand command) throws ResourceNotFoundException, BusinessException;

	void apagarEstudante(Long estudanteId) throws ResourceNotFoundException;
}
