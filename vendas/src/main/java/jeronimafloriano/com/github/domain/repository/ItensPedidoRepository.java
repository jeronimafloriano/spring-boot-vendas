package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Integer>{
}
