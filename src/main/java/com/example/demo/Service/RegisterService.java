package com.example.demo.Service;

import org.springframework.stereotype.Service;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class RegisterService {

    private final JdbcTemplate jdbcTemplate;

    public RegisterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createNewTable() {
        // Gerar um nome dinâmico para a tabela
        String tableName = "table_" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        // Comando SQL para criar uma nova tabela
        String createTableSQL = "CREATE TABLE " + tableName + " (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        // Executar o comando de criação da tabela
        jdbcTemplate.execute(createTableSQL);

        System.out.println("Nova tabela criada: " + tableName);
    }
    
    public void createFriendList() {
    	
    }

}
