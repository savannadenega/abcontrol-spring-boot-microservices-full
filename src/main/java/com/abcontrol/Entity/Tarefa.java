package com.abcontrol.Entity;

/**
 * @author SavannaDenega
 * Esta é uma classe bean
 */
public class Tarefa{

    private int id;
    private String nome;
    private String descricao;
    private String prioridade;
    private String status;
    private String dataComecoRealizacao;
    private String dataEstimadaEntrega;

    private String usuario;
    private String material;
    private String anexo;


    public Tarefa(int id, String nome, String descricao, String prioridade, String status, String dataComecoRealizacao, String dataEstimadaEntrega, String usuario, String material, String anexo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.status = status;
        this.dataComecoRealizacao = dataComecoRealizacao;
        this.dataEstimadaEntrega = dataEstimadaEntrega;
        this.usuario = usuario;
        this.material = material;
        this.anexo = anexo;
    }

    public Tarefa(int id, String nome, String descricao){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Tarefa(){

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataComecoRealizacao() {
        return dataComecoRealizacao;
    }

    public void setDataComecoRealizacao(String dataComecoRealizacao) {
        this.dataComecoRealizacao = dataComecoRealizacao;
    }

    public String getDataEstimadaEntrega() {
        return dataEstimadaEntrega;
    }

    public void setDataEstimadaEntrega(String dataEstimadaEntrega) {
        this.dataEstimadaEntrega = dataEstimadaEntrega;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }
}
