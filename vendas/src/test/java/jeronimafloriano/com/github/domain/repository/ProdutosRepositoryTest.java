package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.exception.RegraDeNegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ProdutosRepositoryTest {

    @Autowired
    ProdutosRepository repository;


    @Test
    void deveLancarExcecaoAoBuscarProdutoInexistente() {

        repository.findByIdOrElseThrow(589);
        //Executable buscar = () -> repository.findByIdOrElseThrow(589);
        //assertThrows(RegraDeNegocioException.class, buscar);
    }
}