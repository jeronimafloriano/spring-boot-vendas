package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteId(Integer id);
}
