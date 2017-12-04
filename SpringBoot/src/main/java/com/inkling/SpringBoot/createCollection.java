package com.inkling.SpringBoot;
import java.util.ArrayList;
import java.util.List;

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
import com.amazonaws.services.rekognition.model.CreateCollectionRequest;
import com.amazonaws.services.rekognition.model.CreateCollectionResult;
import com.amazonaws.services.rekognition.model.ListCollectionsRequest;
import com.amazonaws.services.rekognition.model.ListCollectionsResult;
import com.inkling.forms.createCollectionAttributes;

@Controller
public class createCollection {

	
	@RequestMapping(method = RequestMethod.POST, value = "/createCollection")
	public ModelAndView CollectionExample() {

	   
		ModelAndView view = new ModelAndView();
		List<createCollectionAttributes> createCollectionAttributes = new ArrayList<createCollectionAttributes>();
		
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

	      // 1. CreateCollection 1
	      String collectionId = "colletionimagescloud";
	      System.out.println("Creating collections: " +
	         collectionId);
	      CreateCollectionResult createCollectionResult = callCreateCollection(
	         collectionId, amazonRekognition);
	      createCollectionAttributes cCA = new createCollectionAttributes();
	      cCA.setCollectionArn(createCollectionResult.getCollectionArn().toString());
	      System.out.println("______________collectionARN" + cCA.getCollectionArn());
	      cCA.setCollectionName(collectionId);
	      cCA.setStatusCode(createCollectionResult.getStatusCode().toString());
	      System.out.println("CollectionArn : " +
	         createCollectionResult.getCollectionArn());
	      System.out.println("Status code : " +
	         createCollectionResult.getStatusCode().toString());


	      // 2. Page through collections with ListCollections
	      System.out.println("Listing collections");
	      int limit = 1;
	      ListCollectionsResult listCollectionsResult = null;
	      String paginationToken = null;
	      do {
	         if (listCollectionsResult != null) {
	            paginationToken = listCollectionsResult.getNextToken();
	         }
	         listCollectionsResult = callListCollections(paginationToken, limit,
	            amazonRekognition);

	         List < String > collectionIds = listCollectionsResult.getCollectionIds();
	         for (String resultId: collectionIds) {
	            System.out.println(resultId);
	         }
	      } while (listCollectionsResult != null && listCollectionsResult.getNextToken() !=
	         null);

	      // 4. Clean up collections with DeleteCollection

//	      System.out.println("Deleting collections");
//	      DeleteCollectionResult deleteCollectionResult = callDeleteCollection(
//	         collectionId, amazonRekognition);
//	      System.out.println(collectionId + ": " + deleteCollectionResult.getStatusCode()
//	         .toString());
	      createCollectionAttributes.add(cCA);
	     view.addObject("createCollectionAttributes",createCollectionAttributes);
	     view.setViewName("CreateCollectionSuccess");
		return view;

	}

	   private static CreateCollectionResult callCreateCollection(String collectionId,
	      AmazonRekognition amazonRekognition) {
	      CreateCollectionRequest request = new CreateCollectionRequest()
	         .withCollectionId(collectionId);
	      return amazonRekognition.createCollection(request);
	   }

//	   private static DeleteCollectionResult callDeleteCollection(String collectionId,
//	      AmazonRekognition amazonRekognition) {
//	      DeleteCollectionRequest request = new DeleteCollectionRequest()
//	         .withCollectionId(collectionId);
//	      return amazonRekognition.deleteCollection(request);
//	   }

	   private static ListCollectionsResult callListCollections(String paginationToken,
	      int limit, AmazonRekognition amazonRekognition) {
	      ListCollectionsRequest listCollectionsRequest = new ListCollectionsRequest()
	         .withMaxResults(limit)
	         .withNextToken(paginationToken);
	      return amazonRekognition.listCollections(listCollectionsRequest);
	   }

	}


	
	
	

