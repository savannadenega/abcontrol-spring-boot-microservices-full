package com.abcontrol.service;

import com.abcontrol.service.EmailService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Spy
    @InjectMocks
    EmailService emailService;

//    @Before
//    public void setUp(){
//        emailService = new EmailService();
//    }

    @Test
    @Ignore
    public void testEnviarEmail() throws MessagingException {

        emailService.enviarEmail("savanna.denega@hotmail.com", "Teste");

    }

    @Test
    @Ignore
    public void testEnviarEmailNegativo() throws MessagingException {

        assertThatThrownBy(() -> emailService.enviarEmail("", "Teste"))
                .isInstanceOf(SendFailedException.class);

    }

}
