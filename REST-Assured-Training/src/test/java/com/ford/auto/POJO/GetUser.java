package com.ford.auto.POJO;

public class GetUser {
	/* Sample JSON Response returned
	 {
	    "code": 200,
	    "meta": null,
	    "data": {
	        "id": 1403,
	        "name": "Stephen M S",
	        "email": "testEmailThree@gorestApiSample3.com",
	        "gender": "Male",
	        "status": "Active",
	        "created_at": "2021-01-15T09:50:34.375+05:30",
	        "updated_at": "2021-01-15T09:50:34.375+05:30"
		}
	}
*/
	
	private int code;
	private String meta;
	private Data data;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	
	
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getUserRecord() {
		return(this.code+ " "+this.meta+ " "+this.data);
	}

}
