package com.secretsanta.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.secretsanta.model.User;
import com.secretsanta.service.MailService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/send-email")
public class UserController {

	@Autowired
	private MailService notificationService;

	@PostMapping
	public String send(@RequestBody(required = false) User user) {

		try {
			notificationService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

	@PostMapping("/attachment")
	public String sendWithAttachment(@RequestBody(required = false) User user) throws MessagingException {

		try {
			notificationService.sendEmailWithAttachment(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

}
