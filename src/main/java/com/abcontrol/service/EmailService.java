package com.abcontrol.service;

import com.abcontrol.entity.CompraEntity;
import com.abcontrol.entity.MaterialEntity;
import com.abcontrol.entity.OrdemCompraEntity;
import com.abcontrol.repository.CompraRepository;
import com.abcontrol.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    MaterialRepository materialRepository;

    public ResponseEntity enviarEmail(String emailFornecedor, long idCompra) throws MessagingException {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("abcontrol.contato@gmail.com", "Teste123");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("abcontrol.contato@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(emailFornecedor));
        message.setSubject("Requisição de Compras - Empresa ABControl");


        if(!compraRepository.existsById(idCompra)){
            return ResponseEntity.notFound().build();
        } else if(compraRepository.findById(idCompra) == null){
            return ResponseEntity.notFound().build();
        }

        CompraEntity compraEntity = compraRepository.findById(idCompra);
        ArrayList<OrdemCompraEntity> listOrdemCompra = (ArrayList<OrdemCompraEntity>) compraEntity.getOrdemMaterial();

        ArrayList<Long> listIdsMaterial = new ArrayList<>();
        for(int i = 0; i < listOrdemCompra.size(); i++){
            listIdsMaterial.add(listOrdemCompra.get(i).getIdMaterial());
        }

        ArrayList<Optional<MaterialEntity>> materialEntityCollection = new ArrayList<>();
        for(int i = 0; i < listIdsMaterial.size(); i++){
            materialEntityCollection.add(materialRepository.findById(listIdsMaterial.get(i)));
        }

        String msg = "Lista de Materiais - Compra na data de " + compraEntity.getDataCompra() + " :\n";
        for (int i = 0; i < materialEntityCollection.size(); i++){
            msg += "- Material: " + materialEntityCollection.get(i).get().getDescricaoMaterial() + " - ";
            msg += "Quantidade: " + listOrdemCompra.get(i).getQuantidadeMaterial() + "\n";
        }

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        return ResponseEntity.ok().build();

    }

}
