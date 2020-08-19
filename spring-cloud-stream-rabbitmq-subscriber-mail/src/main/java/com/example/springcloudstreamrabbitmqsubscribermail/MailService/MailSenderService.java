package com.example.springcloudstreamrabbitmqsubscribermail.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.springcloudstreamrabbitmqsubscribermail.MessageModel;

@Component
public class MailSenderService {
	
	@Autowired
	private JavaMailSender mailsender;
	

	public void sendmail(MessageModel message) {
		 SimpleMailMessage mail = new SimpleMailMessage(); 
	        mail.setFrom("mymail@asiczen.com");
	        mail.setTo(message.getSenderName()); 
	        mail.setSubject("Testing From Swastik1"); 
	        mail.setText(message.getMessage());
	        mailsender.send(mail);
		
	}
}
