package com.abcontrol.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author FrancieleNF
 */

@Entity
@Table(name="TB_FORMAPAGAMENTO")
public class FormaPagamentoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Não aceita forma de pagamento em branco ou nulo.") //ja valida not null
    private String formaPagamento;

    @NotBlank(message = "Não aceita descrição de pagamento em branco ou nulo.") //ja valida not null
    private String descricaoPagamento;

    @NotNull(message = "Não aceita valor de pagamento em branco ou nulo.") //ja valida not null
    private double valorPagamento;

    public FormaPagamentoEntity(){

    }

    public FormaPagamentoEntity(Long id, String formaPagamento,
                               String descricaoPagamento, double valorPagamento){

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

