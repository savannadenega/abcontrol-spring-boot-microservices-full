package com.abcontrol.controller;

import com.abcontrol.dto.EmailDTO;
import com.abcontrol.entity.CompraEntity;
import com.abcontrol.repository.CompraRepository;
import com.abcontrol.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author SavannaDenega
 */

/**
 * Validacao pela anotacao @Valid, ou seja, caso os parametros não estejam de
 * acordo com as anotacoes de validacao nos atributos do FormaPagamentoEntity,
 * retorna http status 400 Bad Request automaticamente, junto com a mensagem
 * de erro de qual parametro esta incorreto.
 */
@RestController
@RequestMapping(value="/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    EmailService emailService;

    @GetMapping
    public ResponseEntity<List<CompraEntity>> listarCompras() {

        return ResponseEntity.ok(compraRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraEntity> listarCompraPorId(@Valid @PathVariable(value = "id") long id) {

        if(!compraRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else if(compraRepository.findById(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(compraRepository.findById(id));

    }

    @PostMapping
    public ResponseEntity<CompraEntity> realizarCompra(@Valid @RequestBody CompraEntity compraEntity) {

        return ResponseEntity.ok(compraRepository.save(compraEntity));

    }

    @PutMapping
    public ResponseEntity<CompraEntity> atualizarCompra(@Valid @RequestBody CompraEntity compraEntity){

        if(!compraRepository.existsById(compraEntity.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(compraRepository.save(compraEntity));

    }

    @RequestMapping(value = "/enviarEmailCompra/", method = RequestMethod.POST)
    public ResponseEntity enviarEmailCompra(@Valid @RequestBody EmailDTO emailDTO){

        //emailService.enviarEmail();

        return ResponseEntity.ok().build();
    }


}
