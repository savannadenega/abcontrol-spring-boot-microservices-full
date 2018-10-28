package com.abcontrol.controller;

import com.abcontrol.entity.Material;
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
    public List<Material> listaMateriais() {
        return materialRepository.findAll();

    }

    //Lista por ID
    @GetMapping("/{id}")
    public Material listaMaterialPorId(@PathVariable(value = "id") long id) {
        return materialRepository.findById(id);
    }


    // Cadastrar material
    @PostMapping
    public Material cadastraMaterial(@RequestBody Material material) {
        return materialRepository.save(material);
    }

    //Atualizar material
    @PutMapping
    public Material atualizaMaterial(@RequestBody Material material){
        return materialRepository.save(material);
    }

    //Excluir material
    @DeleteMapping
    public void excluiMaterial(@RequestBody Material material) {
        materialRepository.delete(material);
    }
}



