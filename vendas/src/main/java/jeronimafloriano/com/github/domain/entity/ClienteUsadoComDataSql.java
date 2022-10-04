package jeronimafloriano.com.github.domain.entity;

import javax.persistence.*;


public class ClienteUsadoComDataSql {


    private Integer id;
    private String nome;

    public ClienteUsadoComDataSql(){}

    public ClienteUsadoComDataSql(String nome){
        this.nome = nome;
    }

    public ClienteUsadoComDataSql(Integer id, String nome){
        this.id = id;
        this. nome = nome;
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

    @Override
    public String toString() {
        return "Cliente: " + this.id + " Nome: " + this.nome;
    }
}
