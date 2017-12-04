package com.inkling.SpringBoot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.amazonaws.services.rekognition.model.FaceMatch;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.SearchFacesRequest;
import com.amazonaws.services.rekognition.model.SearchFacesResult;
import com.inkling.forms.imageAttributes;

@Controller
public class listCollection {
	

	
	String COLLECTION_ID = "colletionimagescloud";
	static String S3_BUCKET = "anjanasrekog";

	@Autowired
	com.inkling.SpringBoot.Label label;
	
	@RequestMapping(method = RequestMethod.POST, value = "/listCollection")
	public ModelAndView SearchFacesExample(HttpServletRequest request) {

		ModelAndView view = new ModelAndView();


	      AWSCredentials credentials;
	      try {
	    	  credentials = new BasicAWSCredentials("",
	    			  "");
	      } catch (Exception e) {
	         throw new AmazonClientException(
	            "Cannot load the credentials from the credential profiles file. " +
	            "Please make sure that your credentials file is at the correct " +
	            "location (/Users/userid/.aws/credentials), and is in a valid format.",
	            e);
	      }

	      String imageSource = (String) request.getSession(false).getAttribute("externalImageId");
	      System.out.println("imageSource:::::" + imageSource);
	      AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
	         .standard()
	         .withRegion(Regions.US_EAST_1)
	         .withCredentials(new AWSStaticCredentialsProvider(credentials))
	         .build();

	      IndexFacesResult indexFacesResult = callIndexFaces(COLLECTION_ID,
	         amazonRekognition, imageSource);

	      //2. Retrieve face ID of the 1st face added.
	      String faceId = indexFacesResult.getFaceRecords().stream()
	         .map(f-> f.getFace().getFaceId())
	         .findFirst().orElseThrow(()-> new IllegalArgumentException(
	            "No face found"));


	      //callIndexFaces(COLLECTION_ID, amazonRekognition, "image2.jpg");
	      //callIndexFaces(COLLECTION_ID, amazonRekognition, "image8.jpg");

	      Float threshold = 70F;
	      int maxFaces = 10;

	      List<imageAttributes> imageURL = new ArrayList<imageAttributes>() ;
	      //3. Search similar faces for a give face (identified by face ID).
	      System.out.println("Faces matching FaceId: " + faceId);
	      SearchFacesResult searchFacesResult = callSearchFaces(COLLECTION_ID,
	         faceId, threshold, maxFaces, amazonRekognition);
	      List < FaceMatch > faceMatches = searchFacesResult.getFaceMatches();
	      for (FaceMatch face: faceMatches) {
	    	 imageAttributes img = new imageAttributes();
	         System.out.println(face.getFace().getExternalImageId());
	         img.setImageURL(face.getFace().getExternalImageId());
	         System.out.println("ImageUrl:::" + img.getImageURL());
	         imageURL.add(img);
	         //System.out.println();
	      }

	      
	      //4. Get an image object in S3 bucket.
//	      String fileName = "image2.jpg";
//	      Image image = getImageUtil(S3_BUCKET, fileName);
////
////	      //5. Search collection for faces similar to the largest face in the image.
//	      SearchFacesByImageResult searchFacesByImageResult =
//	         callSearchFacesByImage(COLLECTION_ID, image, threshold, maxFaces,
//	            amazonRekognition);
//
//	      System.out.println("Faces matching largest face in image  " + fileName);
//	      List < FaceMatch > faceImageMatches = searchFacesByImageResult.getFaceMatches();
//	      for (FaceMatch face: faceImageMatches) {
//	         System.out.println(face.getFace().toString());
//	         System.out.println();
//	      }

		String curlabel="";
		try{
			curlabel=label.getcurlabel();
			System.out.println("new value  : "+curlabel);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	     
	      view.addObject("imageURL",imageURL);
	      view.addObject("showImage",true);
		view.addObject("label",request.getAttribute("name"));
		view.addObject("curlabel",curlabel);
	      view.setViewName("Dashboard");
	      return view;

	   }

	   private static IndexFacesResult callIndexFaces(
	      String collectionId, AmazonRekognition amazonRekognition, String name) {
	      IndexFacesRequest req = new IndexFacesRequest()
	         .withImage(getImageUtil(S3_BUCKET, name))
	         .withCollectionId(collectionId)
	         .withExternalImageId(name);

	      return amazonRekognition.indexFaces(req);
	   }

//	   private static SearchFacesByImageResult callSearchFacesByImage(String collectionId,
//	      Image image, Float threshold, int maxFaces, AmazonRekognition amazonRekognition
//	   ) {
//	      SearchFacesByImageRequest searchFacesByImageRequest = new SearchFacesByImageRequest()
//	         .withCollectionId(collectionId)
//	         .withImage(image)
//	         .withFaceMatchThreshold(threshold)
//	         .withMaxFaces(maxFaces);
//	      return amazonRekognition.searchFacesByImage(searchFacesByImageRequest);
//	   }

	   private static SearchFacesResult callSearchFaces(String collectionId, String faceId,
	      Float threshold, int maxFaces, AmazonRekognition amazonRekognition) {
	      SearchFacesRequest searchFacesRequest = new SearchFacesRequest()
	         .withCollectionId(collectionId)
	         .withFaceId(faceId)
	         .withFaceMatchThreshold(threshold)
	         .withMaxFaces(maxFaces);
	      return amazonRekognition.searchFaces(searchFacesRequest);
	   }


	   private static Image getImageUtil(String bucket, String key) {
	      return new Image()
	         .withS3Object(new S3Object()
	            .withBucket(bucket)
	            .withName(key));
	   }

	

	}




