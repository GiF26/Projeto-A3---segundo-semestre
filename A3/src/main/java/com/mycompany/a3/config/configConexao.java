package com.mycompany.a3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class configConexao {
    private static String host = "localhost";
    private static String porta = "3306";
    private static String db = "PROJECT_A3";
    private static String usuario = "root";
    private static String senha = "root";
    
    public static Connection getConexao(){
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + porta + 
                    "/" + db + "?useTimezone=true&serverTimezpne = UTC", usuario, senha);
            return c;
        }catch(SQLException e){
            return null;
        }
    }
}
