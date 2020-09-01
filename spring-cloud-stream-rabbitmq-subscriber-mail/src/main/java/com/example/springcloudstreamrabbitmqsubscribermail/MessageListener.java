package com.example.springcloudstreamrabbitmqsubscribermail;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import com.example.springcloudstreamrabbitmqsubscribermail.MailService.MailSenderService;
import com.example.springcloudstreamrabbitmqsubscribermail.MailService.Subject;

@Service
@EnableBinding(Sink.class)
public class MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
    
    @Autowired
    private MailSenderService mailsender;

    @StreamListener(target = Sink.INPUT)
    public void listenForOrder(MessageModel request)
    {
    	Map<String, Object> model = new HashMap<>();
		Subject subject= Subject.valueOf(request.getMessagefor());
		if (subject==Subject.SUB_ENGINSTATUS) {
			model.put("message","The vehicle "+request.getVehicleno()+" is "+request.getVehicleInfo()+" now.");
		}else if (subject==Subject.SUB_FUEL) {
			model.put("message","The vehicle "+request.getVehicleno()+" is running low on fuel with "+request.getVehicleInfo()+" remaining");	
		}else if (subject==Subject.SUB_SPEED) {
			model.put("message","The vehicle "+request.getVehicleno()+" violated the speed limit with current speed as "+request.getVehicleInfo()+" now.");
		}else if(subject==Subject.SUB_GEOFENCE){
			model.put("message","The vehicle "+request.getVehicleno()+" crossed the geofence limit.");
		}else {
			model.put("message","Alert Message from Server for Vehicle"+request.getVehicleno());
		}
		
		
		 mailsender.sendEmail(request, model);

    }
}
