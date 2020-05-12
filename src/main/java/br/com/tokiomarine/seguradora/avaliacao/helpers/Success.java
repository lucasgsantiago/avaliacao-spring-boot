package br.com.tokiomarine.seguradora.avaliacao.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Success {
    private Long correlationId;
    private String message = "Registro salvo com sucesso!";

    public Success(Long correlationId) {
        this.correlationId = correlationId;
    }

}
