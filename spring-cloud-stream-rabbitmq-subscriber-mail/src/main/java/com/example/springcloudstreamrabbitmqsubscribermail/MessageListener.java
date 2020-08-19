package com.example.springcloudstreamrabbitmqsubscribermail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

import com.example.springcloudstreamrabbitmqsubscribermail.MailService.MailSenderService;

@Service
@EnableBinding(Sink.class)
public class MessageListener
{
    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
    
    @Autowired
    private MailSenderService mailsender;

    @StreamListener(target = Sink.INPUT)
    public void listenForOrder(MessageModel message)
    {
      logger.info(" received message ["+message.toString()+"] ");
      mailsender.sendmail(message);
    }
}
