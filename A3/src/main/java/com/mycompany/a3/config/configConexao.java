package com.mycompany.a3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class configConexao {
    private static final String HOST = "localhost";
    private static final String PORTA = "3306"; // Porta padrão do MySQL
    private static final String DB = "A3";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";
    private static final String TIMEZONE_CONFIG = "?useSSL=false&useTimezone=true&serverTimezone=UTC";

    public static Connection getConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://" + HOST + ":" + PORTA + "/" + DB + TIMEZONE_CONFIG;

            Connection c = DriverManager.getConnection(url, USUARIO, SENHA);
            System.out.println("Conexão com MySQL estabelecida com sucesso!");
            return c;

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
            return null;
        }
    }
}
