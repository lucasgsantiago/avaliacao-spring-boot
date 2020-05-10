package br.com.tokiomarine.seguradora.avaliacao.controller;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/estudantes")
public class EstudanteRestController {

    @Autowired
    private EstudanteService service;

    @PostMapping
    public ResponseEntity adicionarEstudante(@Valid @RequestBody AdicionarEstudanteCommand command) throws BusinessException {
        service.cadastrarEstudante(command);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
