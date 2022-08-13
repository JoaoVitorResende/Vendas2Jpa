package joao.vendas.repositorio;

import joao.vendas.objetos.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteReposi {

    private static String insert = "insert into cliente (nome) values (?)";
    private static String selectAll = "select * from cliente";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente SalvarCliente(Cliente cliente){
        jdbcTemplate.update(insert,new Object[]{cliente.getNome()});
        return  cliente;
    }

    public List<Cliente> ObjterTodosOsClientes(){
        return jdbcTemplate.query(selectAll, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getString("nome"));
            }
        });
    }

}
