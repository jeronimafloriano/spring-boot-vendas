package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Cliente;
import jeronimafloriano.com.github.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);
    //É a mesma coisa que:
    //select c from Cliente c where c.nome like :nome;

    List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

    Cliente findOneByNome(String nome);

    boolean existsByNome(String nome);

    @Query(value = " select * from cliente c where c.nome like %:nome% ", nativeQuery = true)
    List<Cliente> findByNomeComSqlPuro(@Param("nome") String nome);

    @Query(value = " select c from Cliente c where c.nome like :nome ")
    List<Cliente> findByNomeComHql(@Param("nome") String nome);

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying //dizendo que é uma operação que altera algo, não apenas de leitura
    void deleteByNome(String nome);



    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
