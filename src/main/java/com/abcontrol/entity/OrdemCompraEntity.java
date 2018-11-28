package com.abcontrol.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author SavannaDenega
 */

@Entity
@Table(name="TB_ORDEMCOMPRA")
public class OrdemCompraEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Não aceita quantidadeMaterial em branco ou nulo.")
    private int quantidadeMaterial;

    // Relacao com Material

    @NotNull(message = "Não aceita id material em branco ou nulo.")
    @ManyToOne
    private MaterialEntity material;

    public OrdemCompraEntity(){
    }

    public OrdemCompraEntity(@NotNull(message = "Não aceita quantidadeMaterial em branco ou nulo.") int quantidadeMaterial, @NotNull(message = "Não aceita id material em branco ou nulo.") MaterialEntity material) {
        this.quantidadeMaterial = quantidadeMaterial;
        this.material = material;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidadeMaterial() {
        return quantidadeMaterial;
    }

    public void setQuantidadeMaterial(int quantidadeMaterial) {
        this.quantidadeMaterial = quantidadeMaterial;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }

}

