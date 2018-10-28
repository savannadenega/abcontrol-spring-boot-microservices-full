package com.abcontrol.controller;


import com.abcontrol.entity.FormaPagamento;
import com.abcontrol.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/formapagamento")
public class FormaPagamentoController {

    @Autowired//injeção de dependencias com
    private FormaPagamentoRepository pagamentoRepository;

    //Listar todos os matériais
    @GetMapping
    public List<FormaPagamento> listaFormaPagamentos() {
        return pagamentoRepository.findAll();

    }

    //Lista por ID
    @GetMapping("{/id}")
    public FormaPagamento listaFormaPagamentoPorId(@PathVariable(value = "id") long id) {
        return pagamentoRepository.findById(id);
    }


    // Cadastrar material
    @PostMapping
    public FormaPagamento cadastraFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        return pagamentoRepository.save(formaPagamento);
    }

    //Atualizar material
    @PutMapping
    public FormaPagamento atualizaFormaPagamento(@RequestBody FormaPagamento formaPagamento){
        return pagamentoRepository.save(formaPagamento);
    }

    //Excluir material
    @DeleteMapping
    public void excluiFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        pagamentoRepository.delete(formaPagamento);
    }
}



