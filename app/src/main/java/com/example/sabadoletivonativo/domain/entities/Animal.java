package com.example.sabadoletivonativo.domain.entities;

abstract public class Animal {
    private Integer id;
    private String nome;
    private Integer idade;
    private Double peso;

    public Animal(Integer id, String nome, Integer idade, Double peso) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }
    public Animal(String nome, Integer idade, Double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
