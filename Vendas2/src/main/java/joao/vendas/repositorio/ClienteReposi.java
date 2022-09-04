package joao.vendas.repositorio;

import joao.vendas.objetos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClienteReposi extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeLike(String nome);

    List<Cliente> findByNomeOrId(String nome,Integer id);

    boolean existsByNome(String nome);
}
