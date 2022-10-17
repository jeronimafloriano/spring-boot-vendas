package jeronimafloriano.com.github.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDetalhadoDto {
    private Integer codigo;
    private String data;
    private String cpfCLiente;
    private String nomeCliente;
    private BigDecimal total;
    private String status;
    private List<ItemPedidoDetalhadoDto> itens;

}
