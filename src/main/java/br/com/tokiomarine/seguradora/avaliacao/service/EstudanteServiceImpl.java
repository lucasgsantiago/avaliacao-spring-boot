package br.com.tokiomarine.seguradora.avaliacao.service;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.requests.EstudantesRequest;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteResult;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Service
public class EstudanteServiceImpl implements EstudanteService {

	@Autowired
	private EstudanteRepository repository;

	@Override
	public void cadastrarEstudante(AdicionarEstudanteCommand command) throws BusinessException {
		if(verificarSeExisteComEmail(command.getEmail())){
			throw new BusinessException("Já existe um Estudante com este E-mail: " + command.getEmail());
		}

		Estudante estudante = new Estudante(command.getNome(),command.getEmail(), command.getTelefone());
		estudante.validar();
		repository.save(estudante);
	}

	@Override
	public EstudanteListResult buscarEstudantes(EstudantesRequest request) {

		Pageable pageable = PageRequest.of(request.getPage(),request.getSize());
		Page<Estudante> page = repository.findAll(pageable);
		List<Estudante> estudantesModel = page.getContent();

		EstudanteListResult result = new EstudanteListResult((int)page.getTotalElements(),page.getSize(),page.getTotalPages());

		estudantesModel.forEach(estudante -> result.getEstudantes().add(
				new EstudanteResult(estudante.getId(),estudante.getNome(),estudante.getEmail(),estudante.getTelefone())
				)
		);

		return result;
	}

	@Override
	public List<EstudanteResult> buscarEstudantes() {
		List<Estudante> estudantesModel = repository.findAll();
		List<EstudanteResult> estudantesResult = new ArrayList<>();
		estudantesModel.forEach(estudante -> estudantesResult.add(
				new EstudanteResult(estudante.getId(),estudante.getNome(),estudante.getEmail(),estudante.getTelefone())
				)
		);
		return estudantesResult;
	}

	@Override
	public void atualizarEstudante(@Valid Estudante estudante) {

	}



	@Override
	public Estudante buscarEstudante(long id) {
		throw new IllegalArgumentException("Identificador inválido:" + id);
	}

	private boolean verificarSeExisteComEmail(String email) {
		return obterEstuantePorEmail(email) != null;
	}

	private Estudante obterEstuantePorEmail(String email){
		if(email == null ) throw new IllegalArgumentException("Identificador inválido:" + email);
		return repository.findByEmail(email);
	}
}
