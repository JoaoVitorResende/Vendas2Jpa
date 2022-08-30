package joao.vendas.repositorio;

import joao.vendas.objetos.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteReposi {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente SalvarCliente(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente Atualizar(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void Deletar(Cliente cliente) {
        if(!entityManager.contains(cliente))
        {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void Deletar(Integer id) {
       Deletar(entityManager.find(Cliente.class,id));
    }

    @Transactional(readOnly = true)
    public List<Cliente> BuscaPorNome(String nome) {
       String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql,Cliente.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> ObjterTodosOsClientes() {
        return entityManager.createQuery("from Cliente",Cliente.class).getResultList();
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
