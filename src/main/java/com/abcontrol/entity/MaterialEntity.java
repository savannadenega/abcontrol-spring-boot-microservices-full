package com.abcontrol.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author SavannaDenega
 */
@Entity
@Table(name="TB_MATERIAL")
public class MaterialEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Não aceita tipo de material em branco ou nulo.") //ja valida not null
    private String tipoMaterial;

    @NotBlank(message = "Não aceita descrição de material em branco ou nulo.") //ja valida not null
    private String descricaoMaterial;

    @NotBlank(message = "Não aceita tipo de unidade em branco ou nulo.") //ja valida not null
    private String tipoUnidade;

    @NotNull(message = "Não aceita valor da unidade em branco ou nulo.") //ja valida not null
    private double valorUnidade;

    public MaterialEntity(){

    }

    public MaterialEntity(@NotBlank(message = "Não aceita tipo de material em branco ou nulo.") String tipoMaterial, @NotBlank(message = "Não aceita descrição de material em branco ou nulo.") String descricaoMaterial, @NotBlank(message = "Não aceita tipo de unidade em branco ou nulo.") String tipoUnidade, @NotNull(message = "Não aceita valor da unidade em branco ou nulo.") double valorUnidade) {
        this.tipoMaterial = tipoMaterial;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoUnidade = tipoUnidade;
        this.valorUnidade = valorUnidade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getDescricaoMaterial() {
        return descricaoMaterial;
    }

    public void setDescricaoMaterial(String descricaoMaterial) {
        this.descricaoMaterial = descricaoMaterial;
    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public double getValorUnidade() {
        return valorUnidade;
    }

    public void setValorUnidade(double valorUnidade) {
        this.valorUnidade = valorUnidade;
    }


}