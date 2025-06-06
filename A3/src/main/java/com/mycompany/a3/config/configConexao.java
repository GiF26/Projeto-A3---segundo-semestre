package com.mycompany.a3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class configConexao {
//    private static String host = "localhost";
//    private static String porta = "3306";
//    private static String db = "PROJECT_A3";
//    private static String usuario = "root";
//    private static String senha = "root";
//    
//    public static Connection getConexao(){
//        try{
//            Connection c = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + porta + 
//                    "/" + db + "?useTimezone=true&serverTimezone = UTC", usuario, senha);
//            return c;
//        }catch(SQLException e){
//            return null;
//        }
//    }
        private static final String HOST = "localhost";
        private static final String PORTA = "3306"; // Porta padrão do MySQL
        private static final String DB = "A3";
        private static final String USUARIO = "root";
        private static final String SENHA = "root";
        private static final String TIMEZONE_CONFIG = "?useSSL=false&useTimezone=true&serverTimezone=UTC";

        public static Connection getConexao() {
            try {
                // 1. Registrar o driver (não é mais necessário a partir do JDBC 4.0, mas não faz mal)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 2. URL de conexão para MySQL
                String url = "jdbc:mysql://" + HOST + ":" + PORTA + "/" + DB + TIMEZONE_CONFIG;

                // 3. Obter conexão
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
