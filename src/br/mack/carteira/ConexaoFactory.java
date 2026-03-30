package br.mack.carteira;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/aula";
    private static final String USER = "root";
    private static final String PASSWORD = "senha";

    public static Connection conectar() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do MariaDB não encontrado. Verifique a dependência no Maven.", e);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados.", e);
        }
    }
}