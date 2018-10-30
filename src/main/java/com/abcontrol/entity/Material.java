package com.abcontrol.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="TB_MATERIAL")
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //valor gerado automaticamente
    private Long id;

    @NotNull //não aceita valores em branco
    private String tipoMaterial;
    private String descricaoMaterial;

    @NotNull
    private String tipoUnidade;

    @NotNull
    private double valorUnidade;

    public Material(){

    }

    public Material(Long id,String tipoMaterial,String descricaoMaterial, String tipoUnidade, double valorUnidade){

        this.id = id;
        this.tipoUnidade = tipoUnidade;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoMaterial = tipoMaterial;
        this.valorUnidade = valorUnidade;

    }

    public Material (Long id, String tipoUnidade, String descricaoMaterial){
        this.id=id;
        this.tipoUnidade = tipoUnidade;
        this.descricaoMaterial=descricaoMaterial;
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
