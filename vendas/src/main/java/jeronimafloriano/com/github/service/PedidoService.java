package jeronimafloriano.com.github.service;

import jeronimafloriano.com.github.domain.entity.Pedido;
import jeronimafloriano.com.github.domain.enums.StatusPedido;
import jeronimafloriano.com.github.dto.PedidoDto;

import java.util.Optional;

public interface PedidoService {

    Pedido save(PedidoDto pedidoDto);

    Optional<Pedido> obterPedidoDetalhado(Integer id);

    void atualizaStatus(Integer id, StatusPedido status);
}
