package jeronimafloriano.com.github.service;

import jeronimafloriano.com.github.domain.entity.Cliente;
import jeronimafloriano.com.github.domain.entity.ItemPedido;
import jeronimafloriano.com.github.domain.entity.Pedido;
import jeronimafloriano.com.github.domain.entity.Produto;
import jeronimafloriano.com.github.domain.enums.StatusPedido;
import jeronimafloriano.com.github.domain.repository.ClienteRepository;
import jeronimafloriano.com.github.domain.repository.ItensPedidoRepository;
import jeronimafloriano.com.github.domain.repository.PedidosRepository;
import jeronimafloriano.com.github.domain.repository.ProdutosRepository;
import jeronimafloriano.com.github.dto.ItemPedidoDto;
import jeronimafloriano.com.github.dto.PedidoDto;
import jeronimafloriano.com.github.exception.PedidoNaoEncontradoException;
import jeronimafloriano.com.github.exception.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImp implements PedidoService{

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;


    @Override
    @Transactional
    public Pedido save(PedidoDto dto) {
        Cliente cliente =  clienteRepository.findByIdOrElseThrow(dto.getCliente());

        Pedido pedido = Pedido.builder()
                .cliente(cliente)
                .dataPedido(LocalDate.now())
                .total(dto.getTotal())
                .status(StatusPedido.REALIZADO)
                .build();

        List<ItemPedido> itens =  converterItem(pedido, dto.getItens());

        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itens);
        pedido.setItens(itens);

        return pedido;
    }



    private List<ItemPedido> converterItem(Pedido pedido, List<ItemPedidoDto> itens){
        if (itens.isEmpty()){
            throw new RegraDeNegocioException("Não é possível realizar um pedido sem itens!");
        }

        return itens.stream()
                .map(dto -> {
                    Produto produto = produtosRepository.findByIdOrElseThrow(dto.getProduto());
                    ItemPedido itemPedido = ItemPedido.builder()
                            .pedido(pedido)
                            .produto(produto)
                            .quantidade(dto.getQuantidade())
                            .build();
                            return itemPedido;
                        }
                ).collect(Collectors.toList());

    }

    @Override
    public Optional<Pedido> obterPedidoDetalhado(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido status){
        Pedido pedido = pedidosRepository
                .findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException());

        pedido.setStatus(status);
        pedidosRepository.save(pedido);

        /* OU:
            pedidosRepository.findByIdOrElseThrow(id)
                    .map(pedido -> {
                    pedido.setStatus(status);
                    return repository.save(pedido);
                    });
         */
    }




}
