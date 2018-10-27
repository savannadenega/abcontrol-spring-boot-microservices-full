package com.abcontrol.Controller;


import com.abcontrol.Entity.FormaPagamento;
import com.abcontrol.Repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author FrancieleNF
 */

@RestController
@RequestMapping(value="/api")
public class FormaPagamentoController {

    @Autowired//injeção de dependencias com
    private FormaPagamentoRepository pagamentoRepository;

    //Listar todos os matériais
    @GetMapping("/formaPagamento")
    public List<FormaPagamento> listaFormaPagamentos() {
        return pagamentoRepository.findAll();

    }

    //Lista por ID
    @GetMapping("/formaPagamento/{id}")
    public FormaPagamento listaFormaPagamentoPorId(@PathVariable(value = "id") long id) {
        return pagamentoRepository.findById(id);
    }


    // Cadastrar material
    @PostMapping("/formaPagamento")
    public FormaPagamento cadastraFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        return pagamentoRepository.save(formaPagamento);
    }

    //Atualizar material
    @PutMapping("formaPagamento")
    public FormaPagamento atualizaFormaPagamento(@RequestBody FormaPagamento formaPagamento){
        return pagamentoRepository.save(formaPagamento);
    }

    //Excluir material
    @DeleteMapping("/formaPagamento")
    public void excluiFormaPagamento(@RequestBody FormaPagamento formaPagamento) {
        pagamentoRepository.delete(formaPagamento);
    }
}



