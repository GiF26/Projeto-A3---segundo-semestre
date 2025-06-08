package com.mycompany.a3.Servicos.TelaCadastroServicos;

import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Servico {
    int id;
    String nomeServico;
    String descricao;
    int categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    public boolean salvar() throws SQLException{
        return insert();
    }
    
    public boolean insert() throws SQLException{
        
        String sql = "INSERT INTO SERVICE (ID, NOME_SERVICE, DESCRICAO, CATEGORIA)" +
                " VALUES (?,?,?,?)";
        
        try(Connection con = configConexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)){
            
            //1. ID - Verifique se geraId() retorna um valor válido
            int id = geraId();
            ps.setInt(1, id);
            
            //2. Nome do Servico
            ps.setString(2, getNomeServico());
            
            //3. Descricao do Servico
            ps.setString(3, getDescricao());
            
            //4. Categoria
            ps.setInt(4, getCategoria());
            
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,  "Erro no banco de dados: " + e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,  "Dados inválidos: " + e.getMessage(),
                "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
    }
    
    private int geraId(){
        String sql = "SELECT COALESCE(MAX(ID), 0) + 1 FROM SERVICE";
        try (Connection con = configConexao.getConexao()) {
            assert con != null;
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                 return rs.next() ? rs.getInt(1) : 1; 
            }
        } catch (SQLException e) {
            return -1; // Valor inválido que será capturado depois
        }
    }

}
