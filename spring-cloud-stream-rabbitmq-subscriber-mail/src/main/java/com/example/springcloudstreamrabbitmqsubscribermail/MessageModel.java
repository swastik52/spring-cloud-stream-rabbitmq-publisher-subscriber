package com.example.springcloudstreamrabbitmqsubscribermail;

import java.io.Serializable;
import lombok.Data;


@Data
public class MessageModel implements Serializable{
	
	private String name;
	private String[] to;
	private String from;
	//flag
	private String messagefor;
	private String vehicleno;
	private String vehicleInfo;

}