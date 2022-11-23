package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Cliente;
import jeronimafloriano.com.github.domain.entity.Produto;
import jeronimafloriano.com.github.exception.RegraDeNegocioException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Integer> {

    default Produto findByIdOrElseThrow(Integer id){
        Produto produto = findById(id).orElseThrow(() ->
                new RegraDeNegocioException("Produto não encontrado ou código inválido: " + id));
        return produto;
    }
}
