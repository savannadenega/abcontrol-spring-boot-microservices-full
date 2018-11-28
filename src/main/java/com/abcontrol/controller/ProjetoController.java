package com.abcontrol.controller;

import com.abcontrol.entity.ProjetoEntity;
import com.abcontrol.repository.ProjetoRepository;
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
@RequestMapping(value="/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public ResponseEntity<List<ProjetoEntity>> listarProjeto() {

        return ResponseEntity.ok(projetoRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoEntity> listarProjetoPorId(@Valid @PathVariable(value = "id") long id) {

        if(!projetoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else if(projetoRepository.findById(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projetoRepository.findById(id));

    }

    @PostMapping
    public ResponseEntity<ProjetoEntity> cadastrarProjeto(@Valid @RequestBody ProjetoEntity projetoEntity) {

        return ResponseEntity.ok(projetoRepository.save(projetoEntity));

    }

    @PutMapping
    public ResponseEntity<ProjetoEntity> atualizarProjeto(@Valid @RequestBody ProjetoEntity projetoEntity){

        if(!projetoRepository.existsById(projetoEntity.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projetoRepository.save(projetoEntity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProjetoEntity> excluirProjeto(@Valid @PathVariable(value = "id")long id) {

        ProjetoEntity projetoEntityResponse;

        if(!projetoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else {
            projetoEntityResponse = projetoRepository.findById(id);
            projetoRepository.deleteById(id);
        }

        return ResponseEntity.ok(projetoEntityResponse);

    }

}
