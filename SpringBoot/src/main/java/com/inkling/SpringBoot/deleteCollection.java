package com.inkling.SpringBoot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DeleteCollectionRequest;
import com.amazonaws.services.rekognition.model.DeleteCollectionResult;

@Controller
public class deleteCollection {

	@RequestMapping(method = RequestMethod.POST, value = "/deleteCollection")
	public ModelAndView CollectionExample() {
		
		 String collectionId = "colletionimagescloud";
		 AWSCredentials credentials;
	      try {
	    	  credentials = new BasicAWSCredentials("",
	   				"");
	      } catch (Exception e) {
	         throw new AmazonClientException(
	            "Cannot load the credentials from the credential profiles file. " +
	            "Please make sure that your credentials file is at the correct " +
	            "location (/Users/userid/.aws/credentials), and is in valid format.",
	            e);
	      }
	      
	      AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
	 	         .standard()
	 	         .withRegion(Regions.US_EAST_1)
	 	         .withCredentials(new AWSStaticCredentialsProvider(credentials))
	 	         .build();
	System.out.println("Deleting collections");
    DeleteCollectionResult deleteCollectionResult = callDeleteCollection(
       collectionId, amazonRekognition);
    System.out.println(collectionId + ": " + deleteCollectionResult.getStatusCode()
       .toString());
	return null;
	
	}
	
	private static DeleteCollectionResult callDeleteCollection(String collectionId,
		      AmazonRekognition amazonRekognition) {
		      DeleteCollectionRequest request = new DeleteCollectionRequest()
		         .withCollectionId(collectionId);
		      return amazonRekognition.deleteCollection(request);
		   }
}

