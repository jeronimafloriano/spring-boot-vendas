package jeronimafloriano.com.github.domain.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ProdutoTest {

    private final Integer id = (int) Math.random();
    private final String descricao = "Xbox";
    private final BigDecimal preco = BigDecimal.valueOf(4000);

    @Test
    public void deveCriarProduto(){
        var produto = new Produto(id, descricao, preco);

        assertAll("Teste produto",
                () -> assertThat(produto.getId()).isEqualTo(id),
                () ->  assertThat(produto.getDescricao()).isEqualTo(descricao),
                () -> assertEquals(BigDecimal.valueOf(4000), produto.getPreco())
        );
    }

    @Test
    public void doisProdutosComDescricaoEPrecoIguaisEIdDiferenteDevemSerDiferentes(){
        var produto1 = new Produto(id, descricao, preco);

        var outroId = (int) Math.random() + 1;
        var produto2 = new Produto(outroId, descricao, preco);

        assertThat(produto1).isNotEqualTo(produto2);
    }
}