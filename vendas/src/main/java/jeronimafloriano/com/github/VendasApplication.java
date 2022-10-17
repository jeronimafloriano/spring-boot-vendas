package jeronimafloriano.com.github;

import jeronimafloriano.com.github.domain.entity.Cliente;
import jeronimafloriano.com.github.domain.entity.Pedido;
import jeronimafloriano.com.github.domain.repository.ClienteRepository;
import jeronimafloriano.com.github.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository, PedidosRepository pedidosRepository){
        return args -> {
            Cliente cliente1 = new Cliente("Jeronima");
            Cliente cliente2 = new Cliente("Maria");
            cliente1.setCpf("21996548000");
            cliente2.setCpf("47654007000");
            clienteRepository.save(cliente1);
            clienteRepository.save(cliente2);

            /*clienteRepository.findAll().forEach(System.out::println);
            System.out.println("Procurando por nome com consulta da JPA: " + clienteRepository.findByNomeLike("Maria"));
            System.out.println(clienteRepository.existsByNome("Jeronima"));

            System.out.println("Procurando por nome com consulta Hql: " + clienteRepository.findByNomeComHql("Maria"));
            System.out.println("Procurando por nome com consulta Sql: " + clienteRepository.findByNomeComSqlPuro("Maria"));

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente1);
            pedido.setDataPedido(LocalDate.now());
            pedido.setTotal(BigDecimal.valueOf(100));
            //pedido.setTotal(new BigDecimal(100));
            pedidosRepository.save(pedido);

            Cliente cliente = clienteRepository.findClienteFetchPedidos(cliente1.getId());

            System.out.println("Buscando clientes com fetch: " + cliente);
            System.out.println(cliente.getPedidos());

            pedidosRepository.findByClienteId(cliente1.getId()).forEach(System.out::println);*/
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
