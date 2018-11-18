package com.abcontrol.controller;

import com.abcontrol.service.EmailService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;

public class EmailServiceTest {

    @Autowired
    EmailService emailService;

//    @Before
//    public void setUp(){
//        emailService = new EmailService();
//    }

    @Test
    public void testEnviarEmail() throws MessagingException {

        emailService.enviarEmail("savanna.denega@hotmail.com", 74);

    }


}
