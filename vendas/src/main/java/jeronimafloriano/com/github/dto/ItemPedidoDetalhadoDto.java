package jeronimafloriano.com.github.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoDetalhadoDto {
    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
