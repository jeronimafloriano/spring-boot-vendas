package jeronimafloriano.com.github.service;

import jeronimafloriano.com.github.domain.repository.PedidosRepository;
import jeronimafloriano.com.github.dto.ItemPedidoDto;
import jeronimafloriano.com.github.dto.PedidoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PedidoServiceImpTest {

    @Mock
    private PedidoServiceImp pedidoService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        List<ItemPedidoDto> itens = new ArrayList<>();
        PedidoDto pedidoDto = new PedidoDto(1, BigDecimal.valueOf(100), itens);
        var pedido = pedidoService.save(pedidoDto);
        when(pedidoService.save(pedidoDto)).thenReturn(pedido);
    }

    @Test
    void obterPedidoDetalhado() {
        PedidosRepository mock = Mockito.mock(PedidosRepository.class);
        var pedidos = mock.findByIdFetchItens(1);
        assertTrue(pedidos.isEmpty());
    }

    @Test
    void atualizaStatus() {
    }
}