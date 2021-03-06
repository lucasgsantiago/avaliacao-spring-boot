package br.com.tokiomarine.seguradora.avaliacao.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends Exception{

    private static final long serialVersionUID = 1L;

    public BusinessException(String message){
        super(message);
    }
}