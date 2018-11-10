package com.abcontrol.controller;

import com.abcontrol.entity.ObraEntity;
import com.abcontrol.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author FrancieleNF
 */
/**
 * Validacao pela anotacao @Valid, ou seja, caso os parametros não estejam de
 * acordo com as anotacoes de validacao nos atributos do FormaPagamentoEntity,
 * retorna http status 400 Bad Request automaticamente, junto com a mensagem
 * de erro de qual parametro esta incorreto.
 */
@RestController
@RequestMapping(value="/obra")
public class ObraController {

    @Autowired
    private ObraRepository obraRepository;

    @GetMapping
    public ResponseEntity<List<ObraEntity>> listarObra() {

        return ResponseEntity.ok(obraRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraEntity> listarObraPorId(@Valid @PathVariable(value = "id") long id) {

        if(!obraRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else if(obraRepository.findById(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(obraRepository.findById(id));

    }

    @PostMapping
    public ResponseEntity<ObraEntity> cadastrarObra(@Valid @RequestBody ObraEntity obraEntity) {

        return ResponseEntity.ok(obraRepository.save(obraEntity));

    }

    @PutMapping
    public ResponseEntity<ObraEntity> atualizarObra(@Valid @RequestBody ObraEntity obraEntity){

        if(!obraRepository.existsById(obraEntity.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(obraRepository.save(obraEntity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObraEntity> excluirObra(@Valid @PathVariable(value = "id")long id) {

        ObraEntity obraEntityResponse;

        if(!obraRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else {
            obraEntityResponse = obraRepository.findById(id);
            obraRepository.deleteById(id);
        }

        return ResponseEntity.ok(obraEntityResponse);

    }

}
