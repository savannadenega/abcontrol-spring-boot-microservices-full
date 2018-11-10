package com.abcontrol.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author FrancieleNF
 */
@Entity
@Table(name="TB_OBRA")
public class ObraEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Não aceita nome branco ou nulo.") //ja valida not null
    private String nome;

    @NotBlank(message = "Não aceita tipo de obra em branco ou nulo.") //ja valida not null
    private String tipoObra;

    @NotBlank(message = "Não aceita status da obra em branco ou nulo.") //ja valida not null
    private String statusObra;

    @NotNull(message = "Não aceita data inicial em branco ou nulo.") //ja valida not null
    private String dataInicial;

    @NotNull(message = "Não aceita previsao de término em branco ou nulo.") //ja valida not null
    private String previsaoTermino;

    public ObraEntity(){

    }

    public ObraEntity(Long id, String nome, String tipoObra, String statusObra, String dataInicial, String previsaoTermino) {
        this.id = id;
        this.nome = nome;
        this.tipoObra = tipoObra;
        this.statusObra = statusObra;
        this.dataInicial = dataInicial;
        this.previsaoTermino = previsaoTermino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
    }

    public String getStatusObra() {
        return statusObra;
    }

    public void setStatusObra(String statusObra) {
        this.statusObra = statusObra;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(String previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }
}

