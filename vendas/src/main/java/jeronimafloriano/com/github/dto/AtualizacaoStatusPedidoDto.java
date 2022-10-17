package jeronimafloriano.com.github.dto;

import jeronimafloriano.com.github.domain.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AtualizacaoStatusPedidoDto {

    @NotEmpty(message = "Informe o status a ser atualizado.")
    private String status;
}
