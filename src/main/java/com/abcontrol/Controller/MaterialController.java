package com.abcontrol.Controller;

import com.abcontrol.Entity.Material;
import com.abcontrol.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/api")
public class MaterialController {

    @Autowired//injeção de dependencias com
    private MaterialRepository materialRepository;

    //Listar todos os matériais
    @GetMapping("/material")
    public List<Material> listaMateriais() {
        return materialRepository.findAll();

    }

    //Lista por ID
    @GetMapping("/material/{id}")
    public Material listaMaterialPorId(@PathVariable(value = "id") long id) {
        return materialRepository.findById(id);
    }


    // Cadastrar material
    @PostMapping("/material")
    public Material cadastraMaterial(@RequestBody Material material) {
        return materialRepository.save(material);
    }

    //Atualizar material
    @PutMapping("material")
    public Material atualizaMaterial(@RequestBody Material material){
        return materialRepository.save(material);
    }

    //Excluir material
    @DeleteMapping("/material")
    public void excluiMaterial(@RequestBody Material material) {
        materialRepository.delete(material);
    }
}



