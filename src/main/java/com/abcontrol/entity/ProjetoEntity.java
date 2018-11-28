package com.abcontrol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author SavannaDenega
 */

@Entity
@Table(name="TB_PROJETO")
public class ProjetoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Não aceita nome do projeto em branco ou nulo.") //ja valida not null
    private String nomeProjeto;

    @NotBlank(message = "Não aceita tipo de projeto em branco ou nulo.") //ja valida not null
    private String tipoProjeto;

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private String statusProjeto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT-03:00")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita data inicial em branco ou nulo.")
    private Date dataInicial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "GMT-03:00")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita data de previsao de termino em branco ou nulo.")
    private Date previsaoTermino;

    // Relacionamentos

    //@NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idProposta;

    //@NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idOrcamento;

    //@NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    @OneToMany
    private Collection<ObraEntity> obras;

    //@NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idTarefa;

    //@NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idEquipe;

    //@NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idAnexo;


    public ProjetoEntity(){

    }

    public ProjetoEntity(Long id, @NotBlank(message = "Não aceita nome do projeto em branco ou nulo.") String nomeProjeto, @NotBlank(message = "Não aceita tipo de projeto em branco ou nulo.") String tipoProjeto, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") String statusProjeto, @NotNull(message = "Não aceita data inicial em branco ou nulo.") Date dataInicial, @NotNull(message = "Não aceita data de previsao de termino em branco ou nulo.") Date previsaoTermino, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") long idProposta, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") long idOrcamento, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") Collection<ObraEntity> obras, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") long idTarefa, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") long idEquipe, @NotNull(message = "Não aceita status do projeto em branco ou nulo.") long idAnexo) {
        this.id = id;
        this.nomeProjeto = nomeProjeto;
        this.tipoProjeto = tipoProjeto;
        this.statusProjeto = statusProjeto;
        this.dataInicial = dataInicial;
        this.previsaoTermino = previsaoTermino;
        this.idProposta = idProposta;
        this.idOrcamento = idOrcamento;
        this.obras = obras;
        this.idTarefa = idTarefa;
        this.idEquipe = idEquipe;
        this.idAnexo = idAnexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(String tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    public String getStatusProjeto() {
        return statusProjeto;
    }

    public void setStatusProjeto(String statusProjeto) {
        this.statusProjeto = statusProjeto;
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

    public long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(long idProposta) {
        this.idProposta = idProposta;
    }

    public long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Collection<ObraEntity> getObras() {
        return obras;
    }

    public void setObras(Collection<ObraEntity> obras) {
        this.obras = obras;
    }

    public long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public long getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(long idAnexo) {
        this.idAnexo = idAnexo;
    }
}

