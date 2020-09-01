package com.example.springcloudstreamrabbitmqsubscribermail.MailService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.example.springcloudstreamrabbitmqsubscribermail.MessageModel;

@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	
	public void sendEmail(MessageModel request, Map<String, Object> model) {
		//MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			//helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
			
			Subject subject= Subject.valueOf(request.getMessagefor());

			Template t = config.getTemplate("email-template.jsp");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(request.getTo());
			helper.setText(html, true);
			if (subject==Subject.SUB_ENGINSTATUS) {
				helper.setSubject("The Vehicle "+request.getVehicleno()+" is "+request.getVehicleInfo());
			}else if (subject==Subject.SUB_FUEL) {
				helper.setSubject("The Vehicle "+request.getVehicleno()+" is running with low fuel.");	
			}else if (subject==Subject.SUB_SPEED) {
				helper.setSubject("The Vehicle "+request.getVehicleno()+" violated speed limit at "+request.getVehicleInfo());
			}else if(subject==Subject.SUB_GEOFENCE){
				helper.setSubject("The Vehicle "+request.getVehicleno()+" crossed geofence limit");
			}else {
				helper.setSubject("Alert Message from Server for Vehicle"+request.getVehicleno());
			}
			helper.setFrom(request.getFrom());
			sender.send(message);

			//response.setMessage("mail send to : " + request.getTo());
			//response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			//response.setMessage("Mail Sending failure : "+e.getMessage());
			//response.setStatus(Boolean.FALSE);
			System.out.println(e);
		}

		//return response;
	}

	/*
	 * public void sendmail(MessageModel message) { SimpleMailMessage mail = new
	 * SimpleMailMessage(); mail.setFrom("noreply@asiczen.com");
	 * mail.setTo(message.getSenderName());
	 * mail.setSubject("Testing From Swastik1"); mail.setText(message.getMessage());
	 * mailsender.send(mail);
	 * 
	 * }
	 */
}
