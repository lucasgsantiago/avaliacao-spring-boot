package br.com.tokiomarine.seguradora.avaliacao.service;

import java.util.List;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void atualizarEstudante(@Valid Estudante estudante) {

	}

	@Override
	public List<Estudante> buscarEstudantes() {
		return null;
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
