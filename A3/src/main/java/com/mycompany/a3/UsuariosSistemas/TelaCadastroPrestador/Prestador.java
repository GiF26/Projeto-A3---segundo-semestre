package com.mycompany.a3.UsuariosSistemas.TelaCadastroPrestador;

import com.mycompany.a3.UsuariosSistemas.UsuarioGeral;
import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;

public class Prestador extends UsuarioGeral{
    private String bio;
    private double avaliacaoMedia;
    private int tipoServico;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(double avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public int getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(int tipoServico) {
        this.tipoServico = tipoServico;
    }

    @Override
    protected boolean salvar() {
        return insert();
    }

    @Override
    protected boolean insert() {
        String sql = "INSERT INTO PRESTADOR (ID, NOME, EMAIL, SENHA, CPF, TELEFONE," +
                    " RUA, BAIRRO, NUMERO, COMPLEMENTO, CIDADE, ESTADO, CEP," +
                    " BIO, TIPO_SERVICE, DATA_NASCIMENTO, SEXO, TIPOUSUARIO) " +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try (Connection con = configConexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)) {

            if(criticas()){
                return false;
            }

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

            //14. Bio
            ps.setString(14, getBio());

            //15. Tipo Servico
            ps.setString(15, String.valueOf(getTipoServico()));

            //16. Data de Nascimento - Conversão adequada
            LocalDate data = getDataNascimento()
                                  .toInstant()
                                  .atZone(ZoneId.systemDefault())
                                  .toLocalDate();
            ps.setDate(16, java.sql.Date.valueOf(data));

            //17. Sexo
            ps.setString(17, String.valueOf(getSexo()));
            
            //18. Tipo do Usuario
            ps.setInt(18, 1);
            
            // Executar a inserção
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
        String sql = "SELECT COALESCE(MAX(ID), 0) + 1 FROM PRESTADOR";
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

    public static boolean delete(int id) {
        String sql = "DELETE FROM PRESTADORES" + 
                    " WHERE ID = ?";
        
        try (Connection con = configConexao.getConexao(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 1. ID  
            ps.setInt(1, id);

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
}
 