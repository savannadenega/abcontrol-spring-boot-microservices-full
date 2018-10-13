package com.abcontrol.Dao;

import com.abcontrol.Entity.Tarefa;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SavannaDenega
 */
@Repository
public class TarefaDao{

    private static Map<Integer, Tarefa> tarefas;

    static {

        tarefas = new HashMap<Integer, Tarefa>(){

            {
                put(0, new Tarefa(0, "Gerar relatório projetos", "Gerar relatório de todos os projetos em execução hoje", "Alta", "Em construção", "01/01/2018", "12/12/2018", "Savanna Denega", "Material 1", "Sem Anexo" ));
                put(1, new Tarefa(1, "Gerar relatório projetos", "Gerar relatório de todos os projetos em execução hoje", "Alta", "Em construção", "01/01/2018", "12/12/2018", "Savanna Denega", "Material 1", "Sem Anexo" ));
                put(2, new Tarefa(2, "Criar Projeto Casa Grande", "Criar Projeto Casa Grande", "Alta", "Em construção", "01/01/2018", "12/12/2018", "Savanna Denega", "Material 1", "Sem Anexo" ));
                put(3, new Tarefa(3, "Solicitar orçamento cimento", "Solicitar orçamento cimento", "Alta", "Em construção", "01/01/2018", "12/12/2018", "Savanna Denega", "Material 1", "Sem Anexo" ));
            }
        };
    }

    public Collection<Tarefa> getAllTarefas(){
        return this.tarefas.values();
    }

    public Tarefa getTarefaById(int id){
        return this.tarefas.get(id);
    }

    public void removeTarefaById(int id) {
        this.tarefas.remove(id);
    }

    public void updateTarefa(Tarefa tarefa) {
        this.tarefas.put(tarefa.getId(), tarefa);
    }

    public void insertTarefa(Tarefa tarefa) {
        this.tarefas.put(tarefa.getId(), tarefa);
    }
}
