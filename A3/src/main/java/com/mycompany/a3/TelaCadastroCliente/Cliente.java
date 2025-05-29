package com.mycompany.a3.TelaCadastroCliente;

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