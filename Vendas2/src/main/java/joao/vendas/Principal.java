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
    public CommandLineRunner init(@Autowired ClienteReposi clientereposi){
        return args -> {
            System.out.println("Salvando clientes");
            clientereposi.save(new Cliente("Joao"));
            clientereposi.save(new Cliente("Outro Cliente"));

            boolean exists = clientereposi.existsByNome("Joao");
            System.out.println(exists);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

}
