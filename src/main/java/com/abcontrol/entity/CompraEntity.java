package com.abcontrol.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name="TB_COMPRA")
public class CompraEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Não aceita nome compra em branco ou nulo.") //ja valida not null
    private String nomeCompra;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-03:00")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Não aceita data compra em branco ou nulo.")
    private Date dataCompra;

    @NotNull(message = "Não aceita valor total em branco ou nulo.")
    private double valorTotal;

    @NotBlank(message = "Não aceita estado de compra em branco ou nulo.") //ja valida not null
    private String estadoCompra;

    // Relacao com Ordem de Compra

    @NotNull(message = "Não aceita lista ordem compra em branco ou nulo.")
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<OrdemCompraEntity> ordemMaterial;

    // Solucao paliativa para entrega do uc008 -> atributos razaoSocialFornecedor e emailFornecedor
    // Era necessario utilizar o FornecedorEntity, mas nao realizamos a implementacao, por isso os atributos abaixo

    @NotBlank(message = "Não aceita razao social fornecedor em branco ou nulo.") //ja valida not null
    private String razaoSocialFornecedor;

    @NotBlank(message = "Não aceita email fornecedor em branco ou nulo.") //ja valida not null
    private String emailFornecedor;

    public CompraEntity(){

    }

    public CompraEntity(@NotBlank(message = "Não aceita nomeCompra em branco ou nulo.") String nomeCompra, @NotNull(message = "Não aceita data compra em branco ou nulo.") Date dataCompra, @NotNull(message = "Não aceita valor total em branco ou nulo.") double valorTotal, @NotBlank(message = "Não aceita estado de compra em branco ou nulo.") String estadoCompra, @NotNull(message = "Não aceita lista ordem compra em branco ou nulo.") Collection<OrdemCompraEntity> ordemMaterial, @NotBlank(message = "Não aceita razao social fornecedor em branco ou nulo.") String razaoSocialFornecedor, @NotBlank(message = "Não aceita email fornecedor em branco ou nulo.") String emailFornecedor) {
        this.nomeCompra = nomeCompra;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.estadoCompra = estadoCompra;
        this.ordemMaterial = ordemMaterial;
        this.razaoSocialFornecedor = razaoSocialFornecedor;
        this.emailFornecedor = emailFornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompra() {
        return nomeCompra;
    }

    public void setNomeCompra(String nomeCompra) {
        this.nomeCompra = nomeCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public Collection<OrdemCompraEntity> getOrdemMaterial() {
        return ordemMaterial;
    }

    public void setOrdemMaterial(Collection<OrdemCompraEntity> ordemMaterial) {
        this.ordemMaterial = ordemMaterial;
    }

    public String getRazaoSocialFornecedor() {
        return razaoSocialFornecedor;
    }

    public void setRazaoSocialFornecedor(String razaoSocialFornecedor) {
        this.razaoSocialFornecedor = razaoSocialFornecedor;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }
}