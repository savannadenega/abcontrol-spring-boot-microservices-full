package com.abcontrol.controller;

import com.abcontrol.entity.ObraEntity;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class ObraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObraController obraController;

    private static final String URI = "/obra";

    private static final String SRC_JSON_PAYLOAD = "json/payload/obra/";

    private static final String SRC_JSON_FAIL = "json/fail/obra/";

    Locale brasil = new Locale("pt","br");

    SimpleDateFormat s= new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void deveriaListarTodasObrasPositivo() throws Exception {

        List<ObraEntity> listaObraEntityResponse = new ArrayList<>();
        ResponseEntity<List<ObraEntity>> responseEntityListaObraEntity = new ResponseEntity<List<ObraEntity>>(listaObraEntityResponse, HttpStatus.OK);

        when(obraController.listarObra())
                .thenReturn(responseEntityListaObraEntity);

        this.mockMvc
                .perform(get(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarObraValidResponse.json")));

    }

    @Test
    public void deveriaCadastrarUmaObraPositivo() throws Exception {

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.OK);

        when(obraController.cadastrarObra(any(ObraEntity.class))).thenReturn(responseObraEntity);

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarObraValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "cadastrarObraValidResponse.json")));
    }

    @Test
    public void deveriaCadastrarUmaObraNegativo() throws Exception {

        this.mockMvc
                .perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "cadastrarObraInvalidRequest.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deveriaListarUmaObraPositivo() throws Exception {

        deveriaCadastrarUmaObraPositivo();

        String uriId = "/1";

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.OK);

        when(obraController.listarObraPorId(any(Long.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "listarUmaObraValidResponse.json")));

    }

    @Test
    public void deveriaListarUmaObraNegativo() throws Exception {

        deveriaCadastrarUmaObraPositivo();

        String uriId = "/1001";

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1001l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.NOT_FOUND);

        when(obraController.listarObraPorId(any(Long.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(get(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaAtualizarUmaObraPositivo() throws Exception {

        deveriaCadastrarUmaObraPositivo();

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção 2");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.OK);

        when(obraController.atualizarObra(any(ObraEntity.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmaObraValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "atualizarUmaObraValidResponse.json")));

    }

    @Test
    public void deveriaAtualizarUmaObraNegativo() throws Exception {

        deveriaCadastrarUmaObraPositivo();

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1l);
        obraEntityResponse.setNome(null);
        obraEntityResponse.setTipoObra("Construção 2");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.OK);

        when(obraController.atualizarObra(any(ObraEntity.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmaObraInvalidRequest.json")))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deveriaAtualizarUmaObraNegativoIdInexistente() throws Exception {

        deveriaCadastrarUmaObraPositivo();

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1001l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção 2");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.NOT_FOUND);

        when(obraController.atualizarObra(any(ObraEntity.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(put(URI).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_FAIL + "atualizarUmaObraIdInexistenteInvalidRequest.json")))
                .andExpect(status().isNotFound());

    }

    @Test
    public void deveriaExcluirUmaObraPositivo() throws Exception {

        deveriaCadastrarUmaObraPositivo();

        String uriId = "/1";

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.OK);

        when(obraController.excluirObra(any(Long.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isOk())
                .andExpect(content().json(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "deletarObraValidResponse.json")));
    }

    @Test
    public void deveriaExcluirUmaObraNegativo() throws Exception {

        String uriId = "/1001";

        ObraEntity obraEntityResponse = new ObraEntity();
        obraEntityResponse.setId(1l);
        obraEntityResponse.setNome("Obra 1");
        obraEntityResponse.setTipoObra("Construção");
        obraEntityResponse.setStatusObra("Em andamento");
        obraEntityResponse.setDataInicial(s.parse("01/01/2018"));
        obraEntityResponse.setPrevisaoTermino(s.parse("01/01/2019"));

        ResponseEntity<ObraEntity> responseEntityObraEntity = new ResponseEntity<ObraEntity>(obraEntityResponse, HttpStatus.NOT_FOUND);

        when(obraController.excluirObra(any(Long.class)))
                .thenReturn(responseEntityObraEntity);

        this.mockMvc
                .perform(delete(URI + uriId).contentType(MediaType.APPLICATION_JSON)
                        .content(ResourceUtils.loadResourceAsString(SRC_JSON_PAYLOAD + "defaultEmptyValidRequest.json")))
                .andExpect(status().isNotFound());

    }

}

