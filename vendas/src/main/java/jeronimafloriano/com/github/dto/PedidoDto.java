package jeronimafloriano.com.github.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {

    @NotNull(message = "Informe o código do cliente.")
    private Integer cliente;

    @NotNull(message = "É obrigatório informar o valor total do pedido.")
    private BigDecimal total;

    private List<ItemPedidoDto> itens;
}
