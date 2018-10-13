package com.abcontrol.Controller;

import com.abcontrol.Entity.Tarefa;
import com.abcontrol.Service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author SavannaDenega
 */
@RestController
@RequestMapping("/tarefas") // end point geral
public class TarefaController{

    @Autowired
    private TarefaService tarefasService;

    /** End point intermediário.
    * Não coloca o parametro value neste @RequestMapping porque o método não aceita parâmetro.
    */
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Tarefa> getAllTarefas(){
        return this.tarefasService.getAllTarefas();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Tarefa getTarefaById(@PathVariable("id") int id){
        return this.tarefasService.getTarefaById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTarefaById(@PathVariable("id") int id){
        this.tarefasService.removeTarefaById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTarefa(@RequestBody Tarefa tarefa){
        this.tarefasService.updateTarefa(tarefa);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertTarefa(@RequestBody Tarefa tarefa){
        this.tarefasService.insertTarefa(tarefa);
    }

}
