package com.mycompany.a3.TelaPrestador;

import com.mycompany.a3.config.configConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Prestador {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String endereco;
    private int idContrato;
    private Date dataNascimento;

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

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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
        
        
    }
    
    public int geraId(){
        
        String sql = "SELECT MAX(ID) AS MAXID FROM PRESTADOR";
        
        try(Connection con = configConexao.getConexao(); PreparedStatement ps = con.prepareStatement(sql)){
//                ps.setString(1, sql);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    return rs.getInt("MAX_ID") + 1;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return 1;
    }
    
    
}




