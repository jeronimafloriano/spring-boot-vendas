package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}
