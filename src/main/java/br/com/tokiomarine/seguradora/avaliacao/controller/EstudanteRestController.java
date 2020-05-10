package br.com.tokiomarine.seguradora.avaliacao.controller;

import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.AdicionarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.commands.estudantes.EditarEstudanteCommand;
import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import br.com.tokiomarine.seguradora.avaliacao.helpers.ResourceNotFoundException;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.requests.EstudantesRequest;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteListResult;
import br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results.EstudanteResult;
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

    @GetMapping("/{id}")
    public ResponseEntity<EstudanteResult> bucarEstudante(@PathVariable("id") Long id) throws ResourceNotFoundException{
        EstudanteResult result = service.buscarEstudante(id);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity adicionarEstudante(@Valid @RequestBody AdicionarEstudanteCommand command) throws BusinessException {
        service.cadastrarEstudante(command);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarEstudante(
            @PathVariable("id") Long id,
            @Valid @RequestBody EditarEstudanteCommand command) throws ResourceNotFoundException, BusinessException {

        command.setId(id);
        service.atualizarEstudante(command);

        return ResponseEntity.ok(HttpStatus.OK);
    }

}
