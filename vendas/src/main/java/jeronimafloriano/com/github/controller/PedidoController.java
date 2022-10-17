package jeronimafloriano.com.github.controller;

import jeronimafloriano.com.github.domain.entity.ItemPedido;
import jeronimafloriano.com.github.domain.entity.Pedido;
import jeronimafloriano.com.github.domain.enums.StatusPedido;
import jeronimafloriano.com.github.domain.repository.PedidosRepository;
import jeronimafloriano.com.github.dto.AtualizacaoStatusPedidoDto;
import jeronimafloriano.com.github.dto.ItemPedidoDetalhadoDto;
import jeronimafloriano.com.github.dto.PedidoDetalhadoDto;
import jeronimafloriano.com.github.dto.PedidoDto;
import jeronimafloriano.com.github.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;
    @Autowired
    private PedidosRepository pedidosRepository;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save (@RequestBody @Valid PedidoDto pedidoDto){
        Pedido pedido = service.save(pedidoDto);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public PedidoDetalhadoDto findById(@PathVariable Integer id){
        return service.obterPedidoDetalhado(id)
                .map(p -> converterPedido(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private PedidoDetalhadoDto converterPedido(Pedido pedido){
        return PedidoDetalhadoDto.builder()
                .codigo(pedido.getId())
                .data(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpfCLiente(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItens(pedido.getItens()))
                .build();
    }

    private List<ItemPedidoDetalhadoDto> converterItens(List<ItemPedido> itens){
        if(itens.isEmpty()){
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> ItemPedidoDetalhadoDto.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()

        ).collect(Collectors.toList());
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarStatus(@PathVariable Integer id, @RequestBody @Valid AtualizacaoStatusPedidoDto dto){
        StatusPedido status = StatusPedido.valueOf(dto.getStatus());
        service.atualizaStatus(id, status);
    }

}
