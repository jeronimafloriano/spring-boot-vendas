package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Pedido;
import jeronimafloriano.com.github.domain.entity.Produto;
import jeronimafloriano.com.github.exception.RegraDeNegocioException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteId(Integer id);

    default Pedido findByIdOrElseThrow(Integer id){
        Pedido pedido = findById(id).orElseThrow(() ->
                new RegraDeNegocioException("Pedido não encontrado ou código inválido"));
        return pedido;
    }

    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
