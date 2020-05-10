package br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.results;

import br.com.tokiomarine.seguradora.avaliacao.helpers.PageModel;

import java.util.ArrayList;
import java.util.List;

public class EstudanteListResult extends PageModel {

    private List<EstudanteResult> estudantes;

    public EstudanteListResult() {
        this.estudantes  = new ArrayList<EstudanteResult>();
    }

    public EstudanteListResult(List<EstudanteResult> estudantes, int totalElements, int pageSize, int totalPages ) {
        this.estudantes = estudantes;
        this.setTotalElements(totalElements);
        this.setPageSize(pageSize);
        this.setTotalPages(totalPages);
    }

    public EstudanteListResult(int totalElements, int pageSize, int totalPages ) {
        this.estudantes = new ArrayList<EstudanteResult>();
        this.setTotalElements(totalElements);
        this.setPageSize(pageSize);
        this.setTotalPages(totalPages);
    }

    public List<EstudanteResult> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<EstudanteResult> estudantes) {
        this.estudantes = estudantes;
    }
}
