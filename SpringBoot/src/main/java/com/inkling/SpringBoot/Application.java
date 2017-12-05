package com.inkling.SpringBoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
 
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
 
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}

//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.nio.ByteBuffer;
//import java.util.List;
//
//import com.amazonaws.AmazonClientException;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.auth.profile.ProfileCredentialsProvider;
//import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
//import com.amazonaws.services.rekognition.AmazonRekognition;
//import com.amazonaws.services.rekognition.AmazonRekognitionClient;
//import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
//import com.amazonaws.services.rekognition.model.Image;
//import com.amazonaws.util.IOUtils;
//import com.amazonaws.services.rekognition.model.BoundingBox;
//import com.amazonaws.services.rekognition.model.CompareFacesMatch;
//import com.amazonaws.services.rekognition.model.CompareFacesRequest;
//import com.amazonaws.services.rekognition.model.CompareFacesResult;
//import com.amazonaws.services.rekognition.model.ComparedFace;
//
//
//public class Application {
//
//   public static void main(String[] args) throws Exception{
//       Float similarityThreshold = 70F;
//       String sourceImage = "/Users/andrew/Documents/anjana/IMG_7266.JPG";
//       String targetImage = "/Users/andrew/Documents/anjana/IMG_5063.JPG";
//       ByteBuffer sourceImageBytes=null;
//       ByteBuffer targetImageBytes=null;
//
//
//       AWSCredentials credentials;
//       try {
//    	    credentials = new BasicAWSCredentials("AKIAJAARLAVSGAO552NA",
//   				"wbdY+U6dlicyHAHAUzuEhsyFQvnvmVz3XfK3oTto");
//           //credentials = new ProfileCredentialsProvider("AdminUser").getCredentials();
//       } catch (Exception e) {
//           throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
//                   + "Please make sure that your credentials file is at the correct "
//                   + "location (/Users/userid/.aws/credentials), and is in valid format.", e);
//       }
//
//       //EndpointConfiguration endpoint=new EndpointConfiguration("endpoint",
//         //s     "us-east-1");
//
//        AmazonRekognition rekognitionClient = new AmazonRekognitionClient(credentials);
//              
//
//
//       //Load source and target images and create input parameters
//       try (InputStream inputStream = new FileInputStream(new File(sourceImage))) {
//          sourceImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
//       }
//       catch(Exception e)
//       {
//           System.out.println("Failed to load source image " + sourceImage);
//           System.exit(1);
//       }
//       try (InputStream inputStream = new FileInputStream(new File(targetImage))) {
//           targetImageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
//       }
//       catch(Exception e)
//       {
//           System.out.println("Failed to load target images: " + targetImage);
//           System.exit(1);
//       }
//
//       Image source=new Image()
//       		.withBytes(sourceImageBytes);
//       Image target=new Image()
//       		.withBytes(targetImageBytes);
//
//       CompareFacesRequest request = new CompareFacesRequest()
//               .withSourceImage(source)
//               .withTargetImage(target)
//               .withSimilarityThreshold(similarityThreshold);
//
//       
//       
//       // Call operation
//       CompareFacesResult compareFacesResult=rekognitionClient.compareFaces(request);
//
//
//       // Display results
//       List <CompareFacesMatch> faceDetails = compareFacesResult.getFaceMatches();
//       for (CompareFacesMatch match: faceDetails){
//       	ComparedFace face= match.getFace();
//       	BoundingBox position = face.getBoundingBox();
//       	System.out.println("Face at " + position.getLeft().toString()
//       			+ " " + position.getTop()
//       			+ " matches with " + face.getConfidence().toString()
//       			+ "% confidence.");
//
//       }
//       List<ComparedFace> uncompared = compareFacesResult.getUnmatchedFaces();
//
//       System.out.println("There were " + uncompared.size()
//       		+ " that did not match");
//       System.out.println("Source image rotation: " + compareFacesResult.getSourceImageOrientationCorrection());
//       System.out.println("target image rotation: " + compareFacesResult.getTargetImageOrientationCorrection());
//   }
//}
//
//

//package com.anjana.springboot;

