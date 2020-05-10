package br.com.tokiomarine.seguradora.avaliacao.helpers;

public class PageRequest {
    private Integer page;
    private Integer size;

    public PageRequest() {}

    public PageRequest(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
