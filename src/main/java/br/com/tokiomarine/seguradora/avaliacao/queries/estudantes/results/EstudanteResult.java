package br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudanteResult {

    private Long id;

    private String nome;

    private String email;

    private String matricula;

    private String telefone;

    private String curso;

}
