package com.abcontrol.controller;

import com.abcontrol.entity.CompraEntity;
import com.abcontrol.entity.FormaPagamentoEntity;
import com.abcontrol.entity.OrdemCompraEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import test.util.ResourceUtils;

import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Savanna Denega
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //para zerar cada teste de cada vez
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompraController compraController;

    private static final String URI = "/compra";

    private static final String SRC_JSON_PAYLOAD = "json/payload/compra/";

    private static final String SRC_JSON_FAIL = "json/fail/compra/";


    @Test
    public void deveriaListarTodasComprasPositivo() throws Exception {

        List<CompraEntity> listaCompraEntityResponse = new ArrayList<>();
        ResponseEntity<List<CompraEntity>> responseEntityCompraEntity = new ResponseEntity<List<CompraEntity>>(listaCompraEntityResponse, HttpStatus.OK);

        when(compraController.listarCompras())
                .thenReturn(responseEntityCompraEntity);

        this.mockMvc
                .perform(get(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarComprasValidResponse.json")));

    }

    @Test
    public void deveriaRealizarUmaCompraPositivo() throws Exception {

        CompraEntity compraEntityResponse = new CompraEntity();

        compraEntityResponse.setId(1l);
        compraEntityResponse.setNomeCompra("Compra semanal");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("18/11/2018");
        compraEntityResponse.setDataCompra(date);

        compraEntityResponse.setValorTotal(2222.00);
        compraEntityResponse.setEstadoCompra("Finalizada");

        Collection<OrdemCompraEntity> ordemMaterial = new ArrayList<>();
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).add(new OrdemCompraEntity(1, 37));
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).get(0).setId(2l);
        compraEntityResponse.setOrdemMaterial(ordemMaterial);

        compraEntityResponse.setRazaoSocialFornecedor("Empresa da Savanna");
        compraEntityResponse.setEmailFornecedor("savanna.denega@hotmail.com");

        ResponseEntity<CompraEntity> responseEntityCompraEntity = new ResponseEntity<CompraEntity>(compraEntityResponse, HttpStatus.OK);

        when(compraController.realizarCompra(any(CompraEntity.class))).thenReturn(responseEntityCompraEntity);

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarCompraValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarCompraValidResponse.json")));
    }

    @Test
    public void deveriaRealizarUmaCompraNegativo() throws Exception {

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "cadastrarCompraInvalidRequest.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveriaListarUmaCompraPositivo() throws Exception {

        deveriaRealizarUmaCompraPositivo();

        String uriId = "/1";

        CompraEntity compraEntityResponse = new CompraEntity();

        compraEntityResponse.setId(1l);
        compraEntityResponse.setNomeCompra("Compra semanal");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("18/11/2018");
        compraEntityResponse.setDataCompra(date);

        compraEntityResponse.setValorTotal(2222.00);
        compraEntityResponse.setEstadoCompra("Finalizada");

        Collection<OrdemCompraEntity> ordemMaterial = new ArrayList<>();
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).add(new OrdemCompraEntity(1, 37));
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).get(0).setId(2l);
        compraEntityResponse.setOrdemMaterial(ordemMaterial);

        compraEntityResponse.setRazaoSocialFornecedor("Empresa da Savanna");
        compraEntityResponse.setEmailFornecedor("savanna.denega@hotmail.com");

        ResponseEntity<CompraEntity> responseEntityCompraEntity = new ResponseEntity<CompraEntity>(compraEntityResponse, HttpStatus.OK);

        when(compraController.listarCompraPorId(any(Long.class)))
                .thenReturn(responseEntityCompraEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarUmaCompraValidResponse.json")));

    }

    @Test
    public void deveriaListarUmaCompraNegativo() throws Exception {

        deveriaRealizarUmaCompraPositivo();

        String uriId = "/1";

        CompraEntity compraEntityResponse = new CompraEntity();

        compraEntityResponse.setId(1l);
        compraEntityResponse.setNomeCompra("Compra semanal");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("18/11/2018");
        compraEntityResponse.setDataCompra(date);

        compraEntityResponse.setValorTotal(2222.00);
        compraEntityResponse.setEstadoCompra("Finalizada");

        Collection<OrdemCompraEntity> ordemMaterial = new ArrayList<>();
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).add(new OrdemCompraEntity(1, 37));
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).get(0).setId(2l);
        compraEntityResponse.setOrdemMaterial(ordemMaterial);

        compraEntityResponse.setRazaoSocialFornecedor("Empresa da Savanna");
        compraEntityResponse.setEmailFornecedor("savanna.denega@hotmail.com");

        ResponseEntity<CompraEntity> responseEntityCompraEntity = new ResponseEntity<CompraEntity>(compraEntityResponse, HttpStatus.NOT_FOUND);

        when(compraController.listarCompraPorId(any(Long.class)))
                .thenReturn(responseEntityCompraEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAtualizarUmaCompraPositivo() throws Exception {

        deveriaRealizarUmaCompraPositivo();

        CompraEntity compraEntityResponse = new CompraEntity();

        compraEntityResponse.setId(1l);
        compraEntityResponse.setNomeCompra("Compra mensal");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("18/11/2018");
        compraEntityResponse.setDataCompra(date);

        compraEntityResponse.setValorTotal(2222.00);
        compraEntityResponse.setEstadoCompra("Finalizada");

        Collection<OrdemCompraEntity> ordemMaterial = new ArrayList<>();
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).add(new OrdemCompraEntity(1, 37));
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).get(0).setId(2l);
        compraEntityResponse.setOrdemMaterial(ordemMaterial);

        compraEntityResponse.setRazaoSocialFornecedor("Empresa da Savanna");
        compraEntityResponse.setEmailFornecedor("savanna.denega@hotmail.com");

        ResponseEntity<CompraEntity> responseEntityCompraEntity = new ResponseEntity<CompraEntity>(compraEntityResponse, HttpStatus.OK);

        when(compraController.atualizarCompra(any(CompraEntity.class)))
                .thenReturn(responseEntityCompraEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarCompraValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarCompraValidResponse.json")));

    }

    @Test
    public void deveriaAtualizarUmaCompraNegativo() throws Exception {

        deveriaRealizarUmaCompraPositivo();

        CompraEntity compraEntityResponse = new CompraEntity();

        compraEntityResponse.setId(1l);
        compraEntityResponse.setNomeCompra("Compra mensal");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("18/11/2018");
        compraEntityResponse.setDataCompra(date);

        compraEntityResponse.setValorTotal(2222.00);
        compraEntityResponse.setEstadoCompra("Finalizada");

        Collection<OrdemCompraEntity> ordemMaterial = new ArrayList<>();
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).add(new OrdemCompraEntity(1, 37));
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).get(0).setId(2l);
        compraEntityResponse.setOrdemMaterial(ordemMaterial);

        compraEntityResponse.setRazaoSocialFornecedor("Empresa da Savanna");
        compraEntityResponse.setEmailFornecedor("savanna.denega@hotmail.com");

        ResponseEntity<CompraEntity> responseEntityCompraEntity = new ResponseEntity<CompraEntity>(compraEntityResponse, HttpStatus.BAD_REQUEST);

        when(compraController.atualizarCompra(any(CompraEntity.class)))
                .thenReturn(responseEntityCompraEntity);
        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmaCompraInvalidRequest.json")))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deveriaAtualizarUmaFormaPagamentoNegativoIdInexistente() throws Exception {

        deveriaRealizarUmaCompraPositivo();

        CompraEntity compraEntityResponse = new CompraEntity();

        compraEntityResponse.setId(1l);
        compraEntityResponse.setNomeCompra("Compra mensal");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("18/11/2018");
        compraEntityResponse.setDataCompra(date);

        compraEntityResponse.setValorTotal(2222.00);
        compraEntityResponse.setEstadoCompra("Finalizada");

        Collection<OrdemCompraEntity> ordemMaterial = new ArrayList<>();
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).add(new OrdemCompraEntity(1, 37));
        ((ArrayList<OrdemCompraEntity>) ordemMaterial).get(0).setId(2l);
        compraEntityResponse.setOrdemMaterial(ordemMaterial);

        compraEntityResponse.setRazaoSocialFornecedor("Empresa da Savanna");
        compraEntityResponse.setEmailFornecedor("savanna.denega@hotmail.com");

        ResponseEntity<CompraEntity> responseEntityCompraEntity = new ResponseEntity<CompraEntity>(compraEntityResponse, HttpStatus.NOT_FOUND);

        when(compraController.atualizarCompra(any(CompraEntity.class)))
                .thenReturn(responseEntityCompraEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmaCompraIdInexistenteInvalidRequest.json")))
                .andExpect(status().isNotFound());

    }



}
