package br.com.tokiomarine.seguradora.avaliacao.queries.estudantes.requests;

import br.com.tokiomarine.seguradora.avaliacao.helpers.PageRequest;

public class EstudantesRequest extends PageRequest {

    private final int pageDefault = 0;
    private final int sizeDefault = 10;

    public EstudantesRequest() {
        this.setPage(pageDefault);
        this.setSize(sizeDefault);
    }

    public EstudantesRequest(Integer page, Integer size) {
        this.setPage(page != null ? page : pageDefault);
        this.setSize(size != null ? size : sizeDefault);
    }

}
