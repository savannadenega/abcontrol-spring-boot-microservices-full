
package com.abcontrol.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author FrancieleNF
 */

@Entity
@Table(name="TB_FORMAPAGAMENTO")
public class FormaPagamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //valor gerado automaticamente
    private Long id;

    @NotNull //não aceita valores em branco
    private String formaPagamento;
    private String descricaoPagamento;
    private double valorPagamento;

    public FormaPagamento(){
    }

    public FormaPagamento(Long id, String formaPagamento, String descricaoPagamento, double valorPagamento){

        this.id = id;
        this.formaPagamento = formaPagamento;
        this.descricaoPagamento = descricaoPagamento;
        this.valorPagamento = valorPagamento;
            }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getDescricaoPagamento() {
        return descricaoPagamento;
    }

    public void setDescricaoPagamento(String descricaoPagamento) {
        this.descricaoPagamento = descricaoPagamento;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }
}

