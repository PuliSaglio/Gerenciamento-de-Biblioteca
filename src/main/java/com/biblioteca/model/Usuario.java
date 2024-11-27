package com.biblioteca.model;

public class Usuario {
    private static int contadorID = 0;
    private int ID;
    private String nome;
    private String email;

    public Usuario() {

    }

    public Usuario(String nome, String email) {
        this.ID = contadorID++;
        this.nome = nome;
        this.email = email;
    }

    public int getID() {
        return ID;
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

    @Override
    public String toString() {
        return "Usuario{" + "ID=" + ID + ", nome=" + nome + ", email=" + email + '}';
    }
}
