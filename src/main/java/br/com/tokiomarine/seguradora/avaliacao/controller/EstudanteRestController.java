package br.com.tokiomarine.seguradora.avaliacao.controller;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.requests.EstudantesRequest;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/estudantes")
public class EstudanteRestController {

    @Autowired
    private EstudanteService service;

    @GetMapping
    public ResponseEntity<EstudanteListResult> listarEstudantes(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){
        EstudanteListResult result = service.buscarEstudantes(new EstudantesRequest(page,size));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity adicionarEstudante(@Valid @RequestBody AdicionarEstudanteCommand command) throws BusinessException {
        service.cadastrarEstudante(command);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
