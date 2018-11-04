package com.abcontrol.controller;


import com.abcontrol.entity.FormaPagamentoEntity;
import com.abcontrol.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Validacao pela anotacao @Valid, ou seja, caso os parametros não estejam de
 * acordo com as anotacoes de validacao nos atributos do FormaPagamentoEntity,
 * retorna http status 400 Bad Request automaticamente, junto com a mensagem
 * de erro de qual parametro esta incorreto.
 */
@RestController
@RequestMapping(value="/formaPagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository pagamentoRepository;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoEntity>> listarFormaPagamentos() {

        return ResponseEntity.ok(pagamentoRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoEntity> listarFormaPagamentoPorId(@Valid @PathVariable(value = "id") long id) {

        if(!pagamentoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else if(pagamentoRepository.findById(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pagamentoRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoEntity> cadastrarFormaPagamento(@Valid @RequestBody FormaPagamentoEntity formaPagamentoEntity) {

        return ResponseEntity.ok(pagamentoRepository.save(formaPagamentoEntity));

    }

    @PutMapping
    public ResponseEntity<FormaPagamentoEntity> atualizarFormaPagamento(@Valid @RequestBody FormaPagamentoEntity formaPagamentoEntity){

        if(!pagamentoRepository.existsById(formaPagamentoEntity.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pagamentoRepository.save(formaPagamentoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FormaPagamentoEntity> excluirFormaPagamento(@Valid @PathVariable(value = "id") long id) {

        FormaPagamentoEntity formaPagamentoEntityResponse;

        if(!pagamentoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else {
            formaPagamentoEntityResponse = pagamentoRepository.findById(id);
            pagamentoRepository.deleteById(id);
        }

        return ResponseEntity.ok(formaPagamentoEntityResponse);

    }

}



