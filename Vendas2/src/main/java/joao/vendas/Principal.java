package joao.vendas;

import joao.vendas.objetos.Cliente;
import joao.vendas.repositorio.ClienteReposi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Principal {

    @Bean
    public CommandLineRunner init(@Autowired ClienteReposi clienterposi){
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("João");
            clienterposi.SalvarCliente(cliente);

            List<Cliente> todosClientes = clienterposi.ObjterTodosOsClientes();
            todosClientes.forEach(System.out::println);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

}
