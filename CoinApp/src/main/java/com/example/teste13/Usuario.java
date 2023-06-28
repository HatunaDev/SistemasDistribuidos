package com.example.teste13;

public class Usuario {

    private String email;

    private String senha;

    public Usuario(){

    }

    public void setEmail( String newEmail){

        this.email = newEmail;

    }

    public void setSenha( String newSenha){

        this.senha = newSenha;

    }

    public String getEmail(){

        return this.email;

    }

    public String getSenha(){

        return this.senha;

    }

}
