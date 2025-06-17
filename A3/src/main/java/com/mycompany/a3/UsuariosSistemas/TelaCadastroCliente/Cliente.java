package com.mycompany.a3.UsuariosSistemas.TelaCadastroCliente;

import com.mycompany.a3.UsuariosSistemas.UsuarioGeral;
import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;

public class Cliente extends UsuarioGeral{

    @Override
    protected boolean salvar() {
        criticas();
        return insert();
    }

    @Override
    protected boolean insert() {
        
        String sql = "INSERT INTO CLIENTES (ID, NOME, EMAIL, SENHA, CPF, TELEFONE," +
                    " RUA, BAIRRO, NUMERO, COMPLEMENTO, CIDADE, ESTADO, CEP," +
                    " DATA_NASCIMENTO, SEXO, TIPOUSUARIO)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = configConexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            // 1. ID - Verifique se geraId() retorna um valor válido
            int id = geraId();
            ps.setInt(1, id);

            // 2. Nome - Verifique se não é nulo
            String nome = getNome();
            ps.setString(2, nome);

            // 3. Email - Validação básica
            String email = getEmail();
            ps.setString(3, email);

            // 4. Senha - Nunca armazene em texto puro
            String senha = getSenha();
            ps.setString(4, senha);

            // 5. CPF - Remova formatação
            String cpf = getCpf();
            ps.setString(5, cpf);

            // 6. Telefone - Remova formatação
            String telefone = getTelefone();
            ps.setString(6, telefone);

            // 7. Rua
            ps.setString(7, getRua());
            
            // 8. Bairro
            ps.setString(8, getBairro());
            
            //9. Numero
            ps.setString(9, getNumero());
            
            //10. Complemento
            ps.setString(10, getComplemento());
            
            //11. Cidade
            ps.setString(11, getCidade());
            
            //12. Estado
            ps.setString(12, getEstado());
            
            //13. CEP
            ps.setString(13, getCep());
            
            //14. Data de Nascimento - Conversão adequada
            LocalDate data = getDataNascimento()
                                  .toInstant()
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();
            ps.setDate(14, java.sql.Date.valueOf(data));
            
            //15. Sexo
            ps.setString(15, String.valueOf(getSexo()));
            
            //16. Tipo do Usuario
            ps.setInt(16, 2);
            
            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null,  "Erro no banco de dados: " + e.getMessage(), 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,  "Dados inválidos: " + e.getMessage(),
                "Erro de Validação", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
    }
    
    @Override
    protected int geraId() {
        String sql = "SELECT COALESCE(MAX(ID), 0) + 1 FROM USUARIO";
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

    @Override
    protected boolean criticas() {
        if(geraId() == 0){
            return true;
        }
        if(getNome().isEmpty() || getNome().equals(" ")){
            return true;
        }
        return super.criticas();
    }
    
    public Cliente retornaUsuarioSistema(int id){
        Cliente c = new Cliente();
        
        String sql = "SELECT NOME, EMAIL, SENHA, CPF, TELEFONE," +
        " RUA, BAIRRO, NUMERO, COMPLEMENTO, CIDADE, ESTADO, CEP," +
        " DATA_NASCIMENTO, SEXO, TIPOUSUARIO" +
        " FROM USUARIO" +
        " WHERE ID = " + id;

         try (Connection con = configConexao.getConexao();
                 PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
             
             c.setNome(rs.getString("NOME"));
             c.setEmail(rs.getString("EMAIL"));
             c.setSenha(rs.getString("SENHA"));
             c.setCpf(rs.getString("CPF"));
             c.setTelefone(rs.getString("TELEFONE"));
             c.setRua(rs.getString("RUA"));
             c.setBairro(rs.getString("BAIRRO"));
             c.setNumero(rs.getString("NUMERO"));
             c.setComplemento(rs.getString("COMPLEMENTO"));
             c.setCidade(rs.getString("CIDADE"));
             c.setEstado(rs.getString("ESTADO"));
             c.setCep(rs.getString("CEP"));
             c.setDataNascimento(rs.getDate("DATA_NASCIMENTO"));
             c.setSexo(rs.getInt("SEXO"));
             c.setTipoUsuario(rs.getInt("TIPOUSUARIO"));

         }catch(Exception e){
             e.printStackTrace();
         }
        
         return c;
    }
}
