package com.example.sabadoletivonativo.domain.entities;

public class Vertebrado extends Animal{
    private String tipoEsqueleto;
    private Boolean sangueQuente;
    private Integer numeroDeMembros;

    public Vertebrado(Integer id, String nome, Integer idade, Double peso, String tipoEsqueleto, Boolean sangueQuente, Integer numeroDeMembros) {
        super(id, nome, idade, peso);
        this.tipoEsqueleto = tipoEsqueleto;
        this.sangueQuente = sangueQuente;
        this.numeroDeMembros = numeroDeMembros;
    }

    public Vertebrado(String nome, Integer idade, Double peso, String tipoEsqueleto, Boolean sangueQuente, Integer numeroDeMembros) {
        super(nome, idade, peso);
        this.tipoEsqueleto = tipoEsqueleto;
        this.sangueQuente = sangueQuente;
        this.numeroDeMembros = numeroDeMembros;
    }

    public String getTipoEsqueleto() {
        return tipoEsqueleto;
    }

    public void setTipoEsqueleto(String tipoEsqueleto) {
        this.tipoEsqueleto = tipoEsqueleto;
    }

    public Boolean getSangueQuente() {
        return sangueQuente;
    }

    public void setSangueQuente(Boolean sangueQuente) {
        this.sangueQuente = sangueQuente;
    }

    public Integer getNumeroDeMembros() {
        return numeroDeMembros;
    }

    public void setNumeroDeMembros(Integer numeroDeMembros) {
        this.numeroDeMembros = numeroDeMembros;
    }
}
