package com.abcontrol.controller;

import com.abcontrol.entity.MaterialEntity;
import com.abcontrol.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/material")
public class MaterialController {

    @Autowired//injeção de dependencias com
    private MaterialRepository materialRepository;

    //Listar todos os matériais
    @GetMapping
    public List<MaterialEntity> listaMateriais() {
        return materialRepository.findAll();
    }

    //Listar por ID
    @GetMapping("/{id}")
    public MaterialEntity listaMaterialPorId(@PathVariable(value = "id") long id) {
        return materialRepository.findById(id);
    }

    //Cadastrar materialEntity
    @PostMapping
    public MaterialEntity cadastraMaterial(@RequestBody MaterialEntity materialEntity) {
        return materialRepository.save(materialEntity);
    }

    //Atualizar materialEntity
    @PutMapping
    public MaterialEntity atualizaMaterial(@RequestBody MaterialEntity materialEntity){
        return materialRepository.save(materialEntity);
    }

    //Excluir material por ID
    @DeleteMapping("/{id}")
    public void excluiMaterial(@PathVariable(value = "id")long id) {
        materialRepository.deleteById(id);
    }

}
