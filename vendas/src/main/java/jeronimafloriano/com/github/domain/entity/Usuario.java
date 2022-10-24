package jeronimafloriano.com.github.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.usuario.login.obrigatorio}")
    private String login;

    @Column
    @NotEmpty(message = "{campo.usuario.senha.obrigatorio}")
    private String senha;

    @Column
    private boolean isAdmin;
}
