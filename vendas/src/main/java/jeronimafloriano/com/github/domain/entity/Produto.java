package jeronimafloriano.com.github.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Obrigatório informar a descrição do produto.")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "Obrigatório informar o preço do produto.")
    private BigDecimal preco;

}
