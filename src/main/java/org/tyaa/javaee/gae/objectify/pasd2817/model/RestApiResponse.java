package org.tyaa.javaee.gae.objectify.pasd2817.model;

public class RestApiResponse {

	public String status;
	public String message;
	public Object data;
	
	public RestApiResponse(String status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
