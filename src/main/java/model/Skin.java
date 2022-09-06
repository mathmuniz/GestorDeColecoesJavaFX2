/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author mmuni
 */
public class Skin {
    
    private int id;
    private String nome;
    private String colecao;
    private String preco;
    private String status;
    private String foto;

    public Skin() {
        
        
    }

    public Skin(String nome, String colecao, String preco, String status, String foto){
        this.nome = nome;
        this.colecao = colecao;
        this.preco = preco;
        this.status = status;
        this.foto = foto;
        
    }

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

    public String getColecao() {
        return colecao;
    }

    public void setColecao(String colecao) {
        this.colecao = colecao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    
    @Override
    public String toString() {
        return id + " | " + nome + " | " + colecao + " | " + preco + " | " + status + " | " + foto;   
    }  

    
}
