package com.examples.core;

import lombok.Data;
import java.io.Serializable;

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
