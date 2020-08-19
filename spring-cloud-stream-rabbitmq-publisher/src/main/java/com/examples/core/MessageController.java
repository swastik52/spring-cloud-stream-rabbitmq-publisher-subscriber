package com.examples.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableBinding(Source.class)
@RestController
public class MessageController
{
    @Autowired
    private Source source;

    @PostMapping("/orders/publish")
    public String publishOrder(@RequestBody MessageModel order)
    {
        source.output().send(MessageBuilder.withPayload(order).build());
        log.info(order.toString());
        return "Message Send Success";
    }
}