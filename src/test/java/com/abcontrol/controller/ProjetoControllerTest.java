package com.abcontrol.controller;

import com.abcontrol.entity.ObraEntity;
import com.abcontrol.entity.ProjetoEntity;
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


import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author FrancieleNF
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoController projetoController;

    private static final String URI = "/projeto";

    private static final String SRC_JSON_PAYLOAD = "json/payload/projeto/";

    private static final String SRC_JSON_FAIL = "json/fail/projeto/";



    @Test
    public void deveriaListarTodosProjetosPositivo() throws Exception {

        List<ProjetoEntity> listaProjetoEntityResponse = new ArrayList<>();
        ResponseEntity<List<ProjetoEntity>> responseEntityListaProjetoEntity = new ResponseEntity<List<ProjetoEntity>>(listaProjetoEntityResponse, HttpStatus.OK);

        when(projetoController.listarProjeto())
                .thenReturn(responseEntityListaProjetoEntity);

        this.mockMvc
                .perform(get(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarProjetoValidResponse.json")));

    }

    @Test
    public void deveriaCadastrarUmProjetoPositivo() throws Exception {

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 1");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);


        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.OK);

        when(projetoController.cadastrarProjeto(any(ProjetoEntity.class))).thenReturn(responseEntityProjetoEntity);


        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarProjetoValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarProjetoValidResponse.json")));
    }

    @Test
    public void deveriaCadastrarUmProjetoNegativo() throws Exception {

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "cadastrarProjetoInvalidRequest.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveriaListarUmProjetoPositivo() throws Exception {

        deveriaCadastrarUmProjetoPositivo();

        String uriId = "/1";

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 1");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);

        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.OK);

        when(projetoController.listarProjetoPorId(any(Long.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarUmProjetoValidResponse.json")));

    }

    @Test
    public void deveriaListarUmProjetoNegativo() throws Exception {

        deveriaCadastrarUmProjetoPositivo();

        String uriId = "/1001";

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 1");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);

        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.NOT_FOUND);

        when(projetoController.listarProjetoPorId(any(Long.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAtualizarUmProjetoPositivo() throws Exception {

        deveriaCadastrarUmProjetoPositivo();

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 2");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);

        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);


        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.OK);


        when(projetoController.atualizarProjeto(any(ProjetoEntity.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmProjetoValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmProjetoValidResponse.json")));

    }

    @Test
    public void deveriaAtualizarUmProjetoNegativo() throws Exception {

        deveriaCadastrarUmProjetoPositivo();

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 2");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);


        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.OK);

        when(projetoController.atualizarProjeto(any(ProjetoEntity.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmProjetoInvalidRequest.json")))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deveriaAtualizarUmProjetoNegativoIdInexistente() throws Exception {

        deveriaCadastrarUmProjetoPositivo();

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 2");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);


        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.NOT_FOUND);

        when(projetoController.atualizarProjeto(any(ProjetoEntity.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmProjetoIdInexistenteInvalidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaExcluirUmProjetoPositivo() throws Exception {

        deveriaCadastrarUmProjetoPositivo();

        String uriId = "/1";

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 1");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);


        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.OK);

        when(projetoController.excluirProjeto(any(Long.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "deletarProjetoValidResponse.json")));
    }

    @Test
    public void deveriaExcluirUmProjetoNegativo() throws Exception {

        String uriId = "/1001";

        ProjetoEntity projetoEntityResponse = new ProjetoEntity();

        projetoEntityResponse.setId(1l);
        projetoEntityResponse.setNomeProjeto("Projeto 1");
        projetoEntityResponse.setTipoProjeto("Projeto para construcao");
        projetoEntityResponse.setStatusProjeto("Em andamento");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-03:00"));
        Date date = simpleDateFormat.parse("2018-01-01T14:00:00.000Z");
        Date date2 = simpleDateFormat.parse("2019-01-01T14:00:00.000Z");

        projetoEntityResponse.setDataInicial(date);
        projetoEntityResponse.setPrevisaoTermino(date2);

        projetoEntityResponse.setIdProposta(1);
        projetoEntityResponse.setIdOrcamento(22);


        projetoEntityResponse.setIdTarefa(2);
        projetoEntityResponse.setIdEquipe(3);
        projetoEntityResponse.setIdAnexo(11);


        Collection<ObraEntity> obras = new ArrayList<>();
        ((ArrayList<ObraEntity>) obras).add(new ObraEntity("Obra","construcao","andamento",date,date2));
        ((ArrayList<ObraEntity>) obras).get(0).setId(2l);
        projetoEntityResponse.setObras(obras);

        ResponseEntity<ProjetoEntity> responseEntityProjetoEntity = new ResponseEntity<ProjetoEntity>(projetoEntityResponse, HttpStatus.NOT_FOUND);

        when(projetoController.excluirProjeto(any(Long.class)))
                .thenReturn(responseEntityProjetoEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

}