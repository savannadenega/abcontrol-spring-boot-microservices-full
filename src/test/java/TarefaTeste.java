
import com.abcontrol.Dao.TarefaDao;
import com.abcontrol.Entity.Tarefa;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TarefaTeste{

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testarGetTarefaById(){
        TarefaDao tarefaDaoMock = mock(TarefaDao.class);
        when(tarefaDaoMock.getTarefaById(0)).thenReturn(new Tarefa(0, "Gerar relatório projetos", "Gerar relatório de todos os projetos em execução hoje", "Alta", "Em construção", "01/01/2018", "12/12/2018", "Savanna Denega", "Material 1", "Sem Anexo" ));

        Tarefa tarefa = tarefaDaoMock.getTarefaById(0);
        Assert.assertEquals(0, tarefa.getId());
        Assert.assertEquals("Gerar relatório projetos", tarefa.getNome());
        Assert.assertEquals("Gerar relatório de todos os projetos em execução hoje", tarefa.getDescricao());
        Assert.assertEquals("Alta", tarefa.getPrioridade());
        Assert.assertEquals("Em construção", tarefa.getStatus());
        Assert.assertEquals("01/01/2018", tarefa.getDataComecoRealizacao());
        Assert.assertEquals("12/12/2018", tarefa.getDataEstimadaEntrega());
        Assert.assertEquals("Savanna Denega", tarefa.getUsuario());
        Assert.assertEquals("Material 1", tarefa.getMaterial());
        Assert.assertEquals("Sem Anexo", tarefa.getAnexo());

        verify(tarefaDaoMock, Mockito.atLeastOnce()).getTarefaById(0);
    }

    @Test
    public void testaRemoveTarefaById(){
        TarefaDao tarefaDaoMock = mock(TarefaDao.class);
        doNothing().when(tarefaDaoMock).removeTarefaById(isA(Integer.class));

        tarefaDaoMock.removeTarefaById(0);

        verify(tarefaDaoMock, Mockito.atLeastOnce()).removeTarefaById(0);
    }

}