package br.com.tokiomarine.seguradora.avaliacao.service;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.EditarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.helpers.ResourceNotFoundException;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.requests.EstudantesRequest;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteResult;

import javax.validation.Valid;
import java.util.List;


public interface EstudanteService {

	EstudanteListResult buscarEstudantes(EstudantesRequest request);

	List<EstudanteResult> buscarEstudantes();

	EstudanteResult buscarEstudante(Long estudanteId) throws ResourceNotFoundException;

	void cadastrarEstudante(@Valid AdicionarEstudanteCommand command) throws BusinessException;

	void apagarEstudante(Long estudanteId) throws ResourceNotFoundException;

	void atualizarEstudante(@Valid EditarEstudanteCommand command) throws ResourceNotFoundException, BusinessException;
}
