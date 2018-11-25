package com.abcontrol.controller;

import com.abcontrol.entity.FormaPagamentoEntity;
import com.abcontrol.entity.MaterialEntity;
import com.abcontrol.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author SavannaDenega
 *
 * Validacao pela anotacao @Valid, ou seja, caso os parametros não estejam de
 * acordo com as anotacoes de validacao nos atributos do FormaPagamentoEntity,
 * retorna http status 400 Bad Request automaticamente, junto com a mensagem
 * de erro de qual parametro esta incorreto.
 */
@RestController
@RequestMapping(value="/material")
public class MaterialController {

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping
    public ResponseEntity<List<MaterialEntity>> listarMateriais() {

        return ResponseEntity.ok(materialRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialEntity> listarMaterialPorId(@Valid @PathVariable(value = "id") long id) {

        if(!materialRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else if(materialRepository.findById(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materialRepository.findById(id));

    }

    @PostMapping
    public ResponseEntity<MaterialEntity> cadastrarMaterial(@Valid @RequestBody MaterialEntity materialEntity) {

        return ResponseEntity.ok(materialRepository.save(materialEntity));

    }

    @PutMapping
    public ResponseEntity<MaterialEntity> atualizarMaterial(@Valid @RequestBody MaterialEntity materialEntity){

        if(!materialRepository.existsById(materialEntity.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(materialRepository.save(materialEntity));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MaterialEntity> excluirMaterial(@Valid @PathVariable(value = "id")long id) {

        MaterialEntity materialEntityResponse;

        if(!materialRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else {
            materialEntityResponse = materialRepository.findById(id);
            materialRepository.deleteById(id);
        }

        return ResponseEntity.ok(materialEntityResponse);

    }

}
