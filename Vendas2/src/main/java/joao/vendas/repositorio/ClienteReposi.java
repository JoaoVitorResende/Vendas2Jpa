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

    private static String update = "update cliente set nome = ? where id = ?";

    private static String delete = "delete from cliente where id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente SalvarCliente(Cliente cliente){
        jdbcTemplate.update(insert,new Object[]{cliente.getNome()});
        return  cliente;
    }

    public Cliente Atualizar(Cliente cliente){
        jdbcTemplate.update(update,new Object[]{
                cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public void Deletar(Cliente cliente){
        Deletar(cliente.getId());
    }

    public void Deletar(Integer id) {
        jdbcTemplate.update(delete,new Object[]{id});
    }

    public List<Cliente> BuscaPorNome(String nome){
        return jdbcTemplate.query(selectAll.concat("where nome like ?"),
                new Object[]{"%"+nome+"%"},
                ObterClientMapper());
    }
    public List<Cliente> ObjterTodosOsClientes(){
        return jdbcTemplate.query(selectAll, ObterClientMapper());
    }

    private RowMapper<Cliente> ObterClientMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getString("nome"));
            }
        };
    }

}
