package com.reche.aep;

import com.reche.aep.model.Usuario;
import com.reche.aep.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AepApplication {

    public static void main(String[] args) {
        SpringApplication.run(AepApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                usuarioRepository.save(new Usuario("admin", "admin123", "Administrador", "ADMIN"));
                usuarioRepository.save(new Usuario("atendente", "atendente123", "Paula Atendente", "ATENDENTE"));
                System.out.println("--- Usuários padrão criados: admin/admin123 e atendente/atendente123 ---");
            }
        };
    }
}
