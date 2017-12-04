package com.inkling.SpringBoot;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.inkling.forms.imageAttributes;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetectLabels {

	@Autowired
	com.inkling.SpringBoot.Label label;
	
@RequestMapping(method = RequestMethod.GET ,value = "/detectlabels")
public ModelAndView DetectLabelsImage(HttpServletRequest request) throws SQLException{

	String labelName = request.getParameter("labelTag");

	String imageid;
    String bucket = "targetimages";
    ModelAndView view = new ModelAndView();
    List<imageAttributes> imageDetectLabel = new ArrayList<imageAttributes>() ;
    AWSCredentials credentials;
    try {
    	credentials = new BasicAWSCredentials("", "");
    } catch(Exception e) {
       throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
        + "Please make sure that your credentials file is at the correct "
        + "location (/Users/userid/.aws/credentials), and is in a valid format.", e);
    }

    AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
  	         .standard()
  	         .withRegion(Regions.US_EAST_1)
  	         .withCredentials(new AWSStaticCredentialsProvider(credentials))
  	         .build();
    Connection conn = getConnection();
    AmazonS3 s3Connection = new AmazonS3Client(credentials);
	final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName("targetimages");
	ListObjectsV2Result s3Objects;
	s3Objects = s3Connection.listObjectsV2(req);
//	for(S3ObjectSummary s3SummaryOfFiles : s3Objects.getObjectSummaries()) {

		//imageAttributes img = new imageAttributes();
//		 DetectLabelsRequest requestLabel = new DetectLabelsRequest()
//		  		  .withImage(new Image()
//		  		  .withS3Object(new S3Object()
//		  		  .withName(s3SummaryOfFiles.getKey()).withBucket(bucket)))
//		  		  .withMaxLabels(10)
//		  		  .withMinConfidence(75F);

		    try {

//		    	 DetectLabelsResult result = rekognitionClient.detectLabels(requestLabel);
//		    	
//		       List <Label> labels = result.getLabels();

		       //System.out.println("Detected labels for " + photo);
//		       for (Label label: labels) {
//		          System.out.println(label.getName() + ": " + label.getConfidence().toString());
//		          if(labelName.equalsIgnoreCase(label.getName())){
//		        	 img.setImageURL(s3SummaryOfFiles.getKey());
//		        	 imageDetectLabel.add(img);
//		          }
//		          String insertTable = "INSERT INTO `CloudDB`.`imageLabel`(`image id`,`labels`)VALUES(?,?);";
//		          PreparedStatement setupStatement = conn.prepareStatement(insertTable);
//		          setupStatement.setString(1, s3SummaryOfFiles.getKey());
//		          setupStatement.setString(2, label.getName());
//		          setupStatement.execute();
//		          setupStatement.close();

	//	       }
		    	System.out.println(labelName);
		       String selectTable = "SELECT distinct imageid FROM CloudDB.imageLabel where labels = ?;";
		       PreparedStatement setupStatement = conn.prepareStatement(selectTable);
				setupStatement.setString(1, labelName);
				ResultSet rs = setupStatement.executeQuery();
				while(rs.next()){
					imageAttributes img = new imageAttributes();
					imageid = rs.getString(1);
					System.out.println(imageid);
					img.setImageURL(imageid);
					imageDetectLabel.add(img);

				}

		    } catch(AmazonRekognitionException e) {
		       e.printStackTrace();
		    }

	//}
	String curlabel="";
	try{
		curlabel=label.getcurlabel();
		System.out.println("new value  : "+curlabel);
	}
	catch(Exception e)
	{
		System.out.println(e);
	}

    view.addObject("imageDetectLabel",imageDetectLabel);
    view.addObject("showLabelImages",true);
	view.addObject("label",labelName);
    view.addObject("curlabel",curlabel);
    view.setViewName("Dashboard");
    return view;
}


	@RequestMapping(method = RequestMethod.GET ,value = "/alexadetectlabels")
	public ModelAndView alexaDetectLabelsImage(HttpServletRequest request) throws SQLException{

		String labelName = request.getParameter("labelTag");


		String imageid;
		String bucket = "targetimages";
		ModelAndView view = new ModelAndView();
		List<imageAttributes> imageDetectLabel = new ArrayList<imageAttributes>() ;
		AWSCredentials credentials;
		try {
			credentials = new BasicAWSCredentials("", "");
		} catch(Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (/Users/userid/.aws/credentials), and is in a valid format.", e);
		}

		AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
		Connection conn = getConnection();
		AmazonS3 s3Connection = new AmazonS3Client(credentials);
		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName("targetimages");
		ListObjectsV2Result s3Objects;
		s3Objects = s3Connection.listObjectsV2(req);


		if(labelName.equals("all")||labelName.equals("All")){
			try{
				String selectTable = "SELECT distinct imageid FROM CloudDB.imageLabel";
				PreparedStatement setupStatement = conn.prepareStatement(selectTable);
//			setupStatement.setString(1, labelName);
				ResultSet rs = setupStatement.executeQuery();
				while(rs.next()){
					imageAttributes img = new imageAttributes();
					imageid = rs.getString(1);
					System.out.println(imageid);
					img.setImageURL(imageid);
					imageDetectLabel.add(img);

				}

			} catch(AmazonRekognitionException e) {
				e.printStackTrace();
			}
		} else {
//	for(S3ObjectSummary s3SummaryOfFiles : s3Objects.getObjectSummaries()) {

		//imageAttributes img = new imageAttributes();
//		 DetectLabelsRequest requestLabel = new DetectLabelsRequest()
//		  		  .withImage(new Image()
//		  		  .withS3Object(new S3Object()
//		  		  .withName(s3SummaryOfFiles.getKey()).withBucket(bucket)))
//		  		  .withMaxLabels(10)
//		  		  .withMinConfidence(75F);

		try {

//		    	 DetectLabelsResult result = rekognitionClient.detectLabels(requestLabel);
//
//		       List <Label> labels = result.getLabels();

			//System.out.println("Detected labels for " + photo);
//		       for (Label label: labels) {
//		          System.out.println(label.getName() + ": " + label.getConfidence().toString());
//		          if(labelName.equalsIgnoreCase(label.getName())){
//		        	 img.setImageURL(s3SummaryOfFiles.getKey());
//		        	 imageDetectLabel.add(img);
//		          }
//		          String insertTable = "INSERT INTO `CloudDB`.`imageLabel`(`image id`,`labels`)VALUES(?,?);";
//		          PreparedStatement setupStatement = conn.prepareStatement(insertTable);
//		          setupStatement.setString(1, s3SummaryOfFiles.getKey());
//		          setupStatement.setString(2, label.getName());
//		          setupStatement.execute();
//		          setupStatement.close();

			//	       }
			System.out.println(labelName);
			String selectTable = "SELECT distinct imageid FROM CloudDB.imageLabel where labels = ?;";
			PreparedStatement setupStatement = conn.prepareStatement(selectTable);
			setupStatement.setString(1, labelName);
			ResultSet rs = setupStatement.executeQuery();
			while(rs.next()){
				imageAttributes img = new imageAttributes();
				imageid = rs.getString(1);
				System.out.println(imageid);
				img.setImageURL(imageid);
				imageDetectLabel.add(img);

			}

		} catch(AmazonRekognitionException e) {
			e.printStackTrace();
		}}

		//}

		view.addObject("imageDetectLabel",imageDetectLabel);
		view.addObject("showLabelImages",true);
		view.addObject("label",labelName);
		view.addObject("curlabel",labelName);
		view.setViewName("Dashboard");
		return view;
	}

private Connection getConnection() {
	// TODO Auto-generated method stub
	try {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://dbinstance.cdamcjojbqhe.us-east-1.rds.amazonaws.com:3306/CloudDB";
		String username = "";// Provide a username
		String password = "";// Provide password
		Class.forName(driver);
		Connection conn = (Connection) DriverManager.getConnection(url, username, password);
		System.out.println("Connected");
		return conn;
	} catch (Exception e) {
		System.out.println(e);
	}
	return null;
}

}


