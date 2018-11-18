package com.abcontrol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone ="GMT-03:00" )
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita data inicial em branco ou nulo.") //ja valida not null
    private Date dataInicial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone ="GMT-03:00" )
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita previsao de término em branco ou nulo.") //ja valida not null
    private Date previsaoTermino;




    public ObraEntity(){

    }

    public ObraEntity(@NotBlank(message = "Não aceita nome branco ou nulo.")String nome, @NotBlank(message = "Não aceita tipo de obra em branco ou nulo.") String tipoObra, @NotBlank(message = "Não aceita status da obra em branco ou nulo.")String statusObra, @NotNull(message = "Não aceita data inicial em branco ou nulo.")Date dataInicial, @NotNull(message = "Não aceita data inicial em branco ou nulo.")Date previsaoTermino) {

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

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getPrevisaoTermino() {
        return previsaoTermino;
    }

    public void setPrevisaoTermino(Date previsaoTermino) {
        this.previsaoTermino = previsaoTermino;
    }
}