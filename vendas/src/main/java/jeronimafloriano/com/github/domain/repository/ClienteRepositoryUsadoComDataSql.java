package jeronimafloriano.com.github.domain.repository;

import jeronimafloriano.com.github.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepositoryUsadoComDataSql {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = (?) WHERE ID = (?)";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = (?)";
    @Autowired
    private JdbcTemplate jdbcTemplate; //conexão com o banco

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void deletar(Cliente cliente){
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
    }

    public List<Cliente> listarTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    public List<Cliente> listarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" WHERE NOME LIKE ?"),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() { //RowMapper mapeia o resultado para uma classe
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
