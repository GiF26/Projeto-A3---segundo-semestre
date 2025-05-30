package com.mycompany.a3.TelaCadastroCliente;

import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String endereco;
    private String dataNascimento;
    private int  idContrato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }
    
    public void salvar(){
        System.out.println(getId());
        System.out.println(getNome());
        System.out.println(getEmail());
        System.out.println(getSenha());
        System.out.println(getCpf());
        System.out.println(getTelefone());
        System.out.println(getEndereco());
        System.out.println(getDataNascimento());
        System.out.println("Objeto Salvo");
        
//        String sql = "INSERT INTO USUARIO (ID, NOME, EMAIL, SENHA, CPF, TELEFONE, ENDERENCO, DATA_NASCIMENTO)" +
//                " VALUES ( " + geraId() + "," + getNome() + "," + getEmail() + "," + getSenha() + "," + getCpf() +
//                "," + getTelefone() + "," + getEndereco() + "," + getDataNascimento() + ")";


    inserirUsuario();
    }
    
    public boolean inserirUsuario() {
    String sql = "INSERT INTO USUARIO (ID, NOME, EMAIL, SENHA, CPF, TELEFONE) " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = configConexao.getConexao();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Verificação básica de conexão
        if (con == null) {
            throw new SQLException("Falha na conexão com o banco de dados");
        }

        // 1. ID - Verifique se geraId() retorna um valor válido
        int id = geraId();
        ps.setInt(1, id);

        // 2. Nome - Verifique se não é nulo
        String nome = getNome();
        ps.setString(2, nome);

        // 3. Email - Validação básica
        String email = "";
        ps.setString(3, email);

        // 4. Senha - Nunca armazene em texto puro
        String senha = "amdin123";
        ps.setString(4, senha);

        // 5. CPF - Remova formatação
        String cpf = getCpf();
        ps.setString(5, cpf);

        // 6. Telefone - Remova formatação
        String telefone = getTelefone();
        ps.setString(6, telefone);

        // 7. Endereço
//        String endereco = "     ";
//        ps.setString(7, endereco);

        // 8. Data de Nascimento - Conversão adequada


        // Executar a inserção
        int linhasAfetadas = ps.executeUpdate();
        return linhasAfetadas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, 
            "Erro no banco de dados: " + e.getMessage(),
            "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(null, 
            "Dados inválidos: " + e.getMessage(),
            "Erro de Validação", JOptionPane.WARNING_MESSAGE);
        return false;
    }
}
    
    private int geraId() {
        String sql = "SELECT COALESCE(MAX(ID), 0) + 1 FROM USUARIO";
        try (Connection con = configConexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Valor inválido que será capturado depois
        }
    }
}

// Tabela de Usuário/Cliente (com campos adicionais)
//CREATE TABLE Usuario (
//    id INT PRIMARY KEY IDENTITY(1,1),
//    nome VARCHAR(100) NOT NULL,
//    email VARCHAR(100) UNIQUE NOT NULL,
//    senha VARCHAR(255) NOT NULL,
//    cpf VARCHAR(14) UNIQUE,
//    telefone VARCHAR(20),
//    endereco TEXT,
//    desconsidera -descfoto_perfil VARCHAR(255),
//    data_nascimento DATE,
//    id_contrato INT,
//    desconsidera created_at DATETIME DEFAULT GETDATE()