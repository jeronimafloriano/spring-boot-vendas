package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class ClienteRepositoryComEntityManager {


    @Autowired
    private EntityManager entity; //executa as operações no banco de dados

    @Transactional //criando a transação do banco
    public Cliente salvar(Cliente cliente){
        entity.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entity.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente){
        if(!entity.contains(cliente)){
            cliente = entity.merge(cliente);
        }
        entity.remove(cliente);
    }

    @Transactional(readOnly = true) //dizendo á JPA que essa operação é somente de leitura
    public List<Cliente> listarPorNome(String nome){
        String jpql = " select c from Cliente c where c.nome like :nome ";
        TypedQuery<Cliente> query = entity.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%" + nome + "%" );
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> listarTodos(){
        return entity.createQuery(" from Cliente ", Cliente.class)
                .getResultList();
    }

}
