package com.abcontrol.controller;

import com.abcontrol.entity.MaterialEntity;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialController materialController;

    private static final String URI = "/material";

    private static final String SRC_JSON_PAYLOAD = "json/payload/material/";

    private static final String SRC_JSON_FAIL = "json/fail/material/";

    @Test
    public void deveriaListarTodosMateriaisPositivo() throws Exception {

        List<MaterialEntity> listaMaterialEntityResponse = new ArrayList<>();
        ResponseEntity<List<MaterialEntity>> responseEntityListaMaterialEntity = new ResponseEntity<List<MaterialEntity>>(listaMaterialEntityResponse, HttpStatus.OK);

        when(materialController.listarMateriais())
                .thenReturn(responseEntityListaMaterialEntity);

        this.mockMvc
                .perform(get(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarMateriaisValidResponse.json")));

    }

    @Test
    public void deveriaCadastrarUmMaterialPositivo() throws Exception {

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1l);
        materialEntityResponse.setTipoMaterial("Contrução");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.OK);

        when(materialController.cadastrarMaterial(any(MaterialEntity.class))).thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarMaterialValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarMaterialValidResponse.json")));
    }

    @Test
    public void deveriaCadastrarUmMaterialNegativo() throws Exception {

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "cadastrarMaterialInvalidRequest.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveriaListarUmMaterialPositivo() throws Exception {

        deveriaCadastrarUmMaterialPositivo();

        String uriId = "/1";

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1l);
        materialEntityResponse.setTipoMaterial("Contrução");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.OK);

        when(materialController.listarMaterialPorId(any(Long.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarUmMaterialValidResponse.json")));

    }

    @Test
    public void deveriaListarUmMaterialNegativo() throws Exception {

        deveriaCadastrarUmMaterialPositivo();

        String uriId = "/1001";

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1001l);
        materialEntityResponse.setTipoMaterial("Contrução");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.NOT_FOUND);

        when(materialController.listarMaterialPorId(any(Long.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAtualizarUmMaterialPositivo() throws Exception {

        deveriaCadastrarUmMaterialPositivo();

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1l);
        materialEntityResponse.setTipoMaterial("Contrução");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão Novo");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.OK);

        when(materialController.atualizarMaterial(any(MaterialEntity.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmMaterialValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmMaterialValidResponse.json")));

    }

    @Test
    public void deveriaAtualizarUmMaterialNegativo() throws Exception {

        deveriaCadastrarUmMaterialPositivo();

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1l);
        materialEntityResponse.setTipoMaterial(null);
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão Novo");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.OK);

        when(materialController.atualizarMaterial(any(MaterialEntity.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmMaterialInvalidRequest.json")))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deveriaAtualizarUmMaterialNegativoIdInexistente() throws Exception {

        deveriaCadastrarUmMaterialPositivo();

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1001l);
        materialEntityResponse.setTipoMaterial("Construção");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão Novo");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.NOT_FOUND);

        when(materialController.atualizarMaterial(any(MaterialEntity.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmMaterialIdInexistenteInvalidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaExcluirUmMaterialPositivo() throws Exception {

        deveriaCadastrarUmMaterialPositivo();

        String uriId = "/1";

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1l);
        materialEntityResponse.setTipoMaterial("Construção");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.OK);

        when(materialController.excluirMaterial(any(Long.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "deletarUmMaterialValidResponse.json")));
    }

    @Test
    public void deveriaExcluirUmMaterialNegativo() throws Exception {

        String uriId = "/1001";

        MaterialEntity materialEntityResponse = new MaterialEntity();
        materialEntityResponse.setId(1001l);
        materialEntityResponse.setTipoMaterial("Construção");
        materialEntityResponse.setDescricaoMaterial("Cimento Padrão");
        materialEntityResponse.setTipoUnidade("Kg");
        materialEntityResponse.setValorUnidade(55.00);

        ResponseEntity<MaterialEntity> responseEntityMaterialEntity = new ResponseEntity<MaterialEntity>(materialEntityResponse, HttpStatus.NOT_FOUND);

        when(materialController.excluirMaterial(any(Long.class)))
                .thenReturn(responseEntityMaterialEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

}
