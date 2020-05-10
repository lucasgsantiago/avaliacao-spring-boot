package br.com.tokiomarine.seguradora.avaliacao.entidade;

import br.com.tokiomarine.seguradora.avaliacao.helpers.BusinessException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Estudante implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é obrigatório")
    @Column()
    private String nome;

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    private String telefone;

    public Estudante(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public Estudante(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public void alterar(String nome, String email, String telefone) throws BusinessException {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.validar();
    }

    public void validar() throws BusinessException {
        Set<ConstraintViolation<Estudante>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(this);
        if(constraintViolations != null && !constraintViolations.isEmpty()){
            for (ConstraintViolation error: constraintViolations) {
                throw new BusinessException(error.getMessage());
            }
        }

    }

}
