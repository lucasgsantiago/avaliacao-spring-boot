package br.com.tokiomarine.seguradora.avaliacao.controller;

import javax.validation.Valid;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.EditarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.helpers.ResourceNotFoundException;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteResult;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.tokiomarine.seguradora.avaliacao.entidade.Estudante;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;

import java.util.List;

@Controller
@RequestMapping("/estudantes/")
public class EstudanteController {

	@Autowired
	private EstudanteService service;

	@GetMapping("criar")
	public String iniciarCastrado(AdicionarEstudanteCommand estudante) {
		return "cadastrar-estudante";
	}

	@GetMapping("listar")
	public String listarEstudantes(Model model) {
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@PostMapping("add")
	public String adicionarEstudante(@Valid AdicionarEstudanteCommand estudante, BindingResult result, Model model) throws BusinessException {
		if (result.hasErrors()) {
			return "cadastrar-estudante";
		}

		model.addAttribute("estudante", estudante);
		service.cadastrarEstudante(estudante);

		return "redirect:listar";
	}

	@GetMapping("editar/{id}")
	public String exibirEdicaoEstudante(@PathVariable("id") Long id, Model model) throws ResourceNotFoundException {
		EstudanteResult estudante = service.buscarEstudante(id);
		model.addAttribute("estudante", estudante);
		return "atualizar-estudante";
	}

	@PostMapping("atualizar/{id}")
	public String atualizarEstudante(@PathVariable("id") Long id, @Valid EditarEstudanteCommand command, BindingResult result, Model model) throws ResourceNotFoundException, BusinessException {
		if (result.hasErrors()) {
			command.setId(id);
			return "atualizar-estudante";
		}
		command.setId(id);
		service.atualizarEstudante(command);

		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}

	@GetMapping("apagar/{id}")
	public String apagarEstudante(@PathVariable("id") Long id, Model model) throws ResourceNotFoundException {
		service.apagarEstudante(id);
		model.addAttribute("estudantes", service.buscarEstudantes());
		return "index";
	}
}
