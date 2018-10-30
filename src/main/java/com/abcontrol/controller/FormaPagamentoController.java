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

    //Listar todos os pagamentos
    @GetMapping
    public List<FormaPagamento> listaFormaPagamentos() {
        return pagamentoRepository.findAll();

    }

    //Lista por ID
    @GetMapping("/{id}")
    public FormaPagamento listaFormaPagamentoPorId(@PathVariable(value = "id") long id) {
        return pagamentoRepository.findById(id);
    }


    // Cadastrar forma de pagamento
    @PostMapping
    public FormaPagamento cadastraFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        return pagamentoRepository.save(formaPagamento);
    }

    //Atualizar forma de pagamento
    @PutMapping
    public FormaPagamento atualizaFormaPagamento(@RequestBody FormaPagamento formaPagamento){
        return pagamentoRepository.save(formaPagamento);
    }

    //Excluir formapagamento por ID
    @DeleteMapping("/{id}")
    public void excluiFormaPagamento(@PathVariable(value = "id") long id) {
        pagamentoRepository.deleteById(id);
    }
}



