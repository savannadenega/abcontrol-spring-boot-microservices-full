package com.abcontrol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
    private double statusProjeto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-03:00")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita data inicial em branco ou nulo.")
    private Date dataInicial;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-03:00")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita data de previsao de termino em branco ou nulo.")
    private Date previsaoTermino;

    // Relacionamentos

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idProposta;

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idOrcamento;

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idObra;

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idTarefa;

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idEquipe;

    @NotNull(message = "Não aceita status do projeto em branco ou nulo.")
    private long idAnexo;


    public ProjetoEntity(){

    }



}

