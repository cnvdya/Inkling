package com.inkling.forms;

public class imageAttributes {
	
	private String imageURL = "";
	private String imageURLSource = "";

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = "https://s3.amazonaws.com/targetimages/" +imageURL;
	}

	public String getImageURLSource() {
		return imageURLSource;
	}

	public void setImageURLSource(String imageURLSource) {
		this.imageURLSource = "https://s3.amazonaws.com/anjanasrekog/" +imageURLSource;;
	}

	
}
