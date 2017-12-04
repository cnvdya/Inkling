package com.inkling.SpringBoot;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.inkling.forms.imageAttributes;

@Controller
public class dropdownEvents {

	@Autowired
	com.inkling.SpringBoot.Label label;
	
	@RequestMapping(method = RequestMethod.GET, value = "/events", params = {"event"})
	public ModelAndView selectedEvents(@RequestParam(value="event") String event){
		
		ModelAndView view = new ModelAndView();
		List<imageAttributes> imageURLEvent = new ArrayList<imageAttributes>() ;
		System.out.println(event+"########");
		
		AWSCredentials credentials;
		try{
			
			credentials = new BasicAWSCredentials("", "");
		} catch (Exception e) {
	         throw new AmazonClientException(
	 	            "Cannot load the credentials from the credential profiles file. " +
	 	            "Please make sure that your credentials file is at the correct " +
	 	            e);
	 	      }
		System.out.println("########Credentials Set");
		
		AmazonS3 s3Connection = new AmazonS3Client(credentials);
		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName("targetimages");
		ListObjectsV2Result s3Objects;
		s3Objects = s3Connection.listObjectsV2(req);
		for(S3ObjectSummary s3SummaryOfFiles : s3Objects.getObjectSummaries()) {
			imageAttributes img = new imageAttributes();
			String key;
			key = s3SummaryOfFiles.getKey();
			System.out.println("########"+key);
			String last_charac = key.substring(key.length() - 5);
			System.out.println("########"+last_charac);
			if (event.contains("Christmas")) {
				if (key.contains("c")) {
					img.setImageURL(key);
					System.out.println(img.getImageURL());
					imageURLEvent.add(img);
				}
			} else if (event == "ThankGiving"){
				if (key.contains("t")) {
					img.setImageURL(key);
					System.out.println(img.getImageURL());
					imageURLEvent.add(img);
				}
			} else if (event == "Halloween"){
				if (key.contains("h")) {
					img.setImageURL(key);
					System.out.println(img.getImageURL());
					imageURLEvent.add(img);
				}
			} else {
				if (key.contains("an")) {
					img.setImageURL(key);
					System.out.println(img.getImageURL());
					imageURLEvent.add(img);
				}
			}

		}

		String curlabel="";
		try{
			curlabel=label.getcurlabel();
			System.out.println("new value  : "+curlabel);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		view.addObject("imageURLEvent",imageURLEvent);
		view.addObject("showEventImages",true);
		view.addObject("curlabel",curlabel);
		view.addObject("label",event);
		view.setViewName("Dashboard");
		return view;
		
	}

}
