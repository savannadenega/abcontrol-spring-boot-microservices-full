package com.abcontrol.Entity;

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
    private String nomeMaterial;

    private String descricaoMaterial;
    private String tipoMaterial;
    private double valorUnidade;

    public Material(){

    }

    public Material(Long id, String nomeMaterial, String descricaoMaterial, String tipoMaterial, double valorUnidade){

        this.id = id;
        this.nomeMaterial = nomeMaterial;
        this.descricaoMaterial = descricaoMaterial;
        this.tipoMaterial = tipoMaterial;
        this.valorUnidade = valorUnidade;

    }

    public Material (Long id, String nomeMaterial, String descricaoMaterial){
        this.id=id;
        this.nomeMaterial=nomeMaterial;
        this.descricaoMaterial=descricaoMaterial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
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
