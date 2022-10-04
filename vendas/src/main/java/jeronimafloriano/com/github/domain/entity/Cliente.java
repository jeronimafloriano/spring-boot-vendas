package jeronimafloriano.com.github.domain.entity;

import javax.persistence.*;
import java.util.List;

@Entity //avisando a JPA que se trata de uma tabela do banco de dados
@Table(name = "cliente") //avisando a JPA qual a tabela do banco de dados
public class Cliente {

    @Id //define a primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @OneToMany(mappedBy = "cliente") //OneToMany = Um cliente para muitos pedidos / mappedBy = nome do mapeamento(atributo) que está mapeando essa classe lá na classe Pedido
    private List<Pedido> pedidos;

    public Cliente(){}
    public Cliente(String nome){
        this.nome = nome;
    }
    public Cliente(Integer id, String nome){
        this.id = id;
        this. nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.id + " Nome: " + this.nome;
    }
}
