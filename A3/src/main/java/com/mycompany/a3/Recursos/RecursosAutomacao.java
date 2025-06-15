package com.mycompany.a3.Recursos;

import com.mycompany.a3.Servicos.TelaCadastroServicos.Servico;
import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Giovana Ferreira
 */
public class RecursosAutomacao {
   
    public static String retDescServico(int tipoServico){
        String sql = "SELECT NOME_SERVICO " +
                    " FROM SERVICOS" + 
                    " WHERE ID = ?";
        
        try(Connection con = configConexao.getConexao();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, tipoServico);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getString("NOME_SERVICO");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Servico nao cadastrado";
    }
    
    public static Servico retCategoria(int tipoServico){
        Servico s = new Servico();
        
        String sql = "SELECT S.CATEGORIA, C.NOME_CATEGORIA" +
                    " FROM SERVICOS S" +
                    " LEFT JOIN CATEGORIAS C" +
                    " ON (S.CATEGORIA = C.ID)" +
                    " WHERE S.ID = ?";
        
        try(Connection con = configConexao.getConexao();
            PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, tipoServico);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {            
                    s.setCategoria(rs.getInt("CATEGORIA"));
                    s.setNomeCategoria(rs.getString("NOME_CATEGORIA"));
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }
}
