package com.mycompany.a3.Recursos;

import com.mycompany.a3.Servicos.TelaCadastroServicos.Servico;
import com.mycompany.a3.UsuariosSistemas.TelaCadastroCliente.Cliente;
import com.mycompany.a3.UsuariosSistemas.TelaCadastroPrestador.Prestador;
import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Giovana Ferreira
 */
public class RecursosAutomacao {
   
    public static Servico retServico(int tipoServico){
        Servico s = new Servico();
        
        String sql = "SELECT S.ID, S.NOME_SERVICO, S.DESCRICAO, S.CATEGORIA, C.NOME_CATEGORIA" +
                    " FROM SERVICOS S" +
                    " LEFT JOIN CATEGORIAS C" +
                    " ON (S.CATEGORIA = C.ID)" +
                    " WHERE S.ID = ?";
        
        try(Connection con = configConexao.getConexao();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, tipoServico);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {      
                    s.setId(tipoServico);
                    s.setNomeServico(rs.getString("NOME_SERVICO"));
                    s.setCategoria(rs.getInt("CATEGORIA"));
                    s.setNomeCategoria(rs.getString("NOME_CATEGORIA"));
                }
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }
    
    public static Prestador retPrestador(int id){
        Prestador p = new Prestador();
        
        String sql = "SELECT ID, NOME, EMAIL, SENHA, CPF, TELEFONE," +
                    " RUA, BAIRRO, NUMERO, COMPLEMENTO, CIDADE, ESTADO, CEP," +
                    " BIO, TIPO_SERVICO, DATA_NASCIMENTO, SEXO, TIPOUSUARIO " +
                    " FROM PRESTADORES" + 
                    " WHERE ID = ?" ;
        try (Connection con = configConexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);) {
               
                ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                 if(rs.next()){
                    p.setId(rs.getInt("ID"));
                    p.setNome(rs.getString("NOME"));
                    p.setEmail(rs.getString("EMAIL"));
                    p.setCpf(rs.getString("CPF"));
                    p.setTelefone(rs.getString("TELEFONE"));
                    p.setRua(rs.getString("RUA"));
                    p.setBairro(rs.getString("BAIRRO"));
                    p.setNumero(rs.getString("NUMERO"));
                    p.setComplemento(rs.getString("COMPLEMENTO"));
                    p.setCidade(rs.getString("CIDADE"));
                    p.setEstado(rs.getString("ESTADO"));
                    p.setCep(rs.getString("CEP"));                    
                    p.setBio(rs.getString("BIO"));
                    p.setTipoServico(rs.getInt("TIPO_SERVICO"));
                    p.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                    p.setSexo(rs.getInt("SEXO"));
                    p.setTipoUsuario(rs.getInt("TIPOUSUARIO"));
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    
    public static Cliente retCli(int id){
        Cliente c = new Cliente();
        String sql = "SELECT ID, NOME, EMAIL, SENHA, CPF, TELEFONE," +
                    " RUA, BAIRRO, NUMERO, COMPLEMENTO, CIDADE, ESTADO, CEP," +
                    " DATA_NASCIMENTO, SEXO, TIPOUSUARIO " +
                    " FROM CLIENTES" + 
                    " WHERE ID = ?" ;
        try (Connection con = configConexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);) {
               
                ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                 if(rs.next()){
                    c.setId(rs.getInt("ID"));
                    c.setNome(rs.getString("NOME"));
                    c.setEmail(rs.getString("EMAIL"));
                    c.setCpf(rs.getString("CPF"));
                    c.setTelefone(rs.getString("TELEFONE"));
                    c.setRua(rs.getString("RUA"));
                    c.setBairro(rs.getString("BAIRRO"));
                    c.setNumero(rs.getString("NUMERO"));
                    c.setNumero(rs.getString("COMPLEMENTO"));
                    c.setCidade(rs.getString("CIDADE"));
                    c.setEstado(rs.getString("ESTADO"));
                    c.setCep(rs.getString("CEP"));                    
                    c.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
                    c.setSexo(rs.getInt("SEXO"));
                    c.setTipoUsuario(rs.getInt("TIPOUSUARIO"));
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public static String formataData(Date dt){
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        return f.format(dt);
    }
    
    public static void configurarColuna(JTable tabela, int indiceColuna, int largura, int alinhamento) {
        TableColumn coluna = tabela.getColumnModel().getColumn(indiceColuna);

        coluna.setPreferredWidth(largura);
        coluna.setMinWidth(50); 
        coluna.setMaxWidth(500); 

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(alinhamento);
        coluna.setCellRenderer(renderer);

        coluna.setResizable(false); 
    }
}
