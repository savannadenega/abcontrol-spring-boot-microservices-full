package com.abcontrol.controller;

import com.abcontrol.entity.FormaPagamentoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

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
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * São realizados testes unitários de contexto Controller Spring,
 * com MockMvc, e utilizando banco em memória H2.
 * Ou seja, nao e necessario fazer deploy da aplicacao para testar
 * os endpoints de acesso e suas logicas.
 *
 * @author Savanna Denega
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) //para zerar cada teste de cada vez
public class FormaPagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FormaPagamentoController formaPagamentoController;

    private static final String URI = "/formaPagamento";

    private static final String SRC_JSON_PAYLOAD = "json/payload/formaPagamento/";

    private static final String SRC_JSON_FAIL = "json/fail/formaPagamento/";

    @Test
    public void deveriaListarTodasFormasPagamentoPositivo() throws Exception {

        List<FormaPagamentoEntity> listaFormaPagamentoEntityResponse = new ArrayList<>();
        ResponseEntity<List<FormaPagamentoEntity>> responseEntityListaFormaPagamentoEntity = new ResponseEntity<List<FormaPagamentoEntity>>(listaFormaPagamentoEntityResponse, HttpStatus.OK);

        when(formaPagamentoController.listarFormaPagamentos())
                .thenReturn(responseEntityListaFormaPagamentoEntity);

        this.mockMvc
                .perform(get(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarFormaPagamentosValidResponse.json")));

    }

    @Test
    public void deveriaCadastrarUmaFormaPagamentoPositivo() throws Exception {

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("3x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.OK);

        when(formaPagamentoController.cadastrarFormaPagamento(any(FormaPagamentoEntity.class))).thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarFormaPagamentoValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarFormaPagamentoValidResponse.json")));
    }

    @Test
    public void deveriaCadastrarUmaFormaPagamentoNegativo() throws Exception {

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "cadastrarFormaPagamentoInvalidRequest.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveriaListarUmaFormaPagamentoPositivo() throws Exception {

        deveriaCadastrarUmaFormaPagamentoPositivo();

        String uriId = "/1";

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("3x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.OK);

        when(formaPagamentoController.listarFormaPagamentoPorId(any(Long.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarUmaFormaPagamentoValidResponse.json")));

    }

    @Test
    public void deveriaListarUmaFormaPagamentoNegativo() throws Exception {

        deveriaCadastrarUmaFormaPagamentoPositivo();

        String uriId = "/1001";

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1001l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("3x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.NOT_FOUND);

        when(formaPagamentoController.listarFormaPagamentoPorId(any(Long.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAtualizarUmaFormaPagamentoPositivo() throws Exception {

        deveriaCadastrarUmaFormaPagamentoPositivo();

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("2x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.OK);

        when(formaPagamentoController.atualizarFormaPagamento(any(FormaPagamentoEntity.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmaFormaPagamentoValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmaFormaPagamentoValidResponse.json")));

    }

    @Test
    public void deveriaAtualizarUmaFormaPagamentoNegativo() throws Exception {

        deveriaCadastrarUmaFormaPagamentoPositivo();

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1l);
        formaPagamentoEntityResponse.setFormaPagamento(null);
        formaPagamentoEntityResponse.setDescricaoPagamento("2x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.OK);

        when(formaPagamentoController.atualizarFormaPagamento(any(FormaPagamentoEntity.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmaFormaPagamentoInvalidRequest.json")))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deveriaAtualizarUmaFormaPagamentoNegativoIdInexistente() throws Exception {

        deveriaCadastrarUmaFormaPagamentoPositivo();

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1001l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("2x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.NOT_FOUND);

        when(formaPagamentoController.atualizarFormaPagamento(any(FormaPagamentoEntity.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmaFormaPagamentoIdInexistenteInvalidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaExcluirUmaFormaPagamentoPositivo() throws Exception {

        deveriaCadastrarUmaFormaPagamentoPositivo();

        String uriId = "/1";

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("3x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.OK);

        when(formaPagamentoController.excluirFormaPagamento(any(Long.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "deletarFormaPagamentoValidResponse.json")));
    }

    @Test
    public void deveriaExcluirUmaFormaPagamentoNegativo() throws Exception {

        String uriId = "/1001";

        FormaPagamentoEntity formaPagamentoEntityResponse = new FormaPagamentoEntity();
        formaPagamentoEntityResponse.setId(1l);
        formaPagamentoEntityResponse.setFormaPagamento("Cartão Crédito");
        formaPagamentoEntityResponse.setDescricaoPagamento("3x - MasterCard");
        formaPagamentoEntityResponse.setValorPagamento(2000.00);

        ResponseEntity<FormaPagamentoEntity> responseEntityFormaPagamentoEntity = new ResponseEntity<FormaPagamentoEntity>(formaPagamentoEntityResponse, HttpStatus.NOT_FOUND);

        when(formaPagamentoController.excluirFormaPagamento(any(Long.class)))
                .thenReturn(responseEntityFormaPagamentoEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

}
