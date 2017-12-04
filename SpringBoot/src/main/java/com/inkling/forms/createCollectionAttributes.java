package com.inkling.forms;

public class createCollectionAttributes {

	private String CollectionArn;
	private String statusCode;
	private String collectionName;
	
	public String getCollectionArn() {
		return CollectionArn;
	}
	public void setCollectionArn(String collectionArn) {
		CollectionArn = collectionArn;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getCollectionName() {
		return collectionName;
	}
	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}
	
}
