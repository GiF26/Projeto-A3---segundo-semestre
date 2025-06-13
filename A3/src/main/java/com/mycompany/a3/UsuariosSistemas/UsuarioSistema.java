package com.mycompany.a3.UsuariosSistemas;

public class UsuarioSistema {
    private int id;
    private int tipoUsuario;
    private String email;
    private String cpf;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioSistema(int id, int tipoUsuario, String email, String cpf, String senha) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    public UsuarioSistema() {
    }
   
}
