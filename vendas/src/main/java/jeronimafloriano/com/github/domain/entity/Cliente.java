package jeronimafloriano.com.github.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity //avisando a JPA que se trata de uma tabela do banco de dados
@Table(name = "cliente") //avisando a JPA qual a tabela do banco de dados
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id //define a primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Obrigatório informar o nome.")
    private String nome;

    @Column(length = 11)
    @NotEmpty(message = "Obrigatório informar o CPF.")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente") //OneToMany = Um cliente para muitos pedidos / mappedBy = nome do mapeamento(atributo) que está mapeando essa classe lá na classe Pedido
    private List<Pedido> pedidos;

    public Cliente(String nome){
        this. nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
