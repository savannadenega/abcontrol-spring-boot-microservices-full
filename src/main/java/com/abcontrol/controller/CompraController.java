package com.abcontrol.controller;

import com.abcontrol.entity.CompraEntity;
import com.abcontrol.entity.MaterialEntity;
import com.abcontrol.entity.OrdemCompraEntity;
import com.abcontrol.repository.CompraRepository;
import com.abcontrol.repository.MaterialRepository;
import com.abcontrol.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author SavannaDenega
 *
 * Validacao pela anotacao @Valid, ou seja, caso os parametros não estejam de
 * acordo com as anotacoes de validacao nos atributos do FormaPagamentoEntity,
 * retorna http status 400 Bad Request automaticamente, junto com a mensagem
 * de erro de qual parametro esta incorreto.
 */
@RestController
@RequestMapping(value="/compra")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MaterialRepository materialRepository;

    @GetMapping
    public ResponseEntity<List<CompraEntity>> listarCompras() {

        return ResponseEntity.ok(compraRepository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraEntity> listarCompraPorId(@Valid @PathVariable(value = "id") long id) {

        if(!compraRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        } else if(compraRepository.findById(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(compraRepository.findById(id));

    }

    @PostMapping
    public ResponseEntity<CompraEntity> realizarCompra(@Valid @RequestBody CompraEntity compraEntity) {

        return ResponseEntity.ok(compraRepository.save(compraEntity));

    }

    @PutMapping
    public ResponseEntity<CompraEntity> atualizarCompra(@Valid @RequestBody CompraEntity compraEntity){

        if(!compraRepository.existsById(compraEntity.getId())){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(compraRepository.save(compraEntity));

    }

    @RequestMapping(value = "/enviarEmailCompra/{idCompra}", method = RequestMethod.POST)
    public ResponseEntity enviarEmailCompra(@PathVariable(value = "idCompra") long idCompra){

        if(!compraRepository.existsById(idCompra)){
            return ResponseEntity.notFound().build();
        } else if(compraRepository.findById(idCompra) == null){
            return ResponseEntity.notFound().build();
        }

        CompraEntity compraEntity = compraRepository.findById(idCompra);
        ArrayList<OrdemCompraEntity> listOrdemCompra = new ArrayList<>(compraEntity.getOrdemMaterial());

        ArrayList<Long> listIdsMaterial = new ArrayList<>();
        for(int i = 0; i < listOrdemCompra.size(); i++){
            listIdsMaterial.add(listOrdemCompra.get(i).getIdMaterial());
        }

        ArrayList<Optional<MaterialEntity>> materialEntityCollection = new ArrayList<>();
        for(int i = 0; i < listIdsMaterial.size(); i++){
            materialEntityCollection.add(materialRepository.findById(listIdsMaterial.get(i)));
        }

        String msgListMateriais = "Lista de Materiais para Compra ABControl - Data " + compraEntity.getDataCompra() + " :<br/><br/>";
        for (int i = 0; i < materialEntityCollection.size(); i++){
            msgListMateriais += "- Material: " + materialEntityCollection.get(i).get().getDescricaoMaterial() + " - ";
            msgListMateriais += "Quantidade: " + listOrdemCompra.get(i).getQuantidadeMaterial() + "<br/>";
        }

        try {
            emailService.enviarEmail(compraEntity.getEmailFornecedor(), msgListMateriais);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

}
