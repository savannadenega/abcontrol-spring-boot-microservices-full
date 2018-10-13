package com.abcontrol.Service;

import com.abcontrol.Dao.TarefaDao;
import com.abcontrol.Entity.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author SavannaDenega
 */
@Service
public class TarefaService{

    @Autowired
    private TarefaDao tarefasDao;

    public Collection<Tarefa> getAllTarefas(){
        return this.tarefasDao.getAllTarefas();
    }

    public Tarefa getTarefaById(int id){
        return this.tarefasDao.getTarefaById(id);
    }

    public void removeTarefaById(int id) {
        this.tarefasDao.removeTarefaById(id);
    }

    public void updateTarefa(Tarefa tarefa) {
        this.tarefasDao.updateTarefa(tarefa);
    }

    public void insertTarefa(Tarefa tarefa) {
        this.tarefasDao.insertTarefa(tarefa);
    }
}
