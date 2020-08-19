package com.example.springcloudstreamrabbitmqsubscribermail;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class MessageModel implements Serializable
{
    @JsonProperty("sender_name")
    private String senderName;
    private String message;
}
