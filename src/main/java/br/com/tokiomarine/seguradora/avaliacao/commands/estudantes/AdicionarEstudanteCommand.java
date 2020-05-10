package br.com.tokiomarine.seguradora.avaliacao.commands.estudantes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdicionarEstudanteCommand {

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String telefone;
}
