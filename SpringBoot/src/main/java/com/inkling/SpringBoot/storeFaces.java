package com.anjana.SpringBoot;

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
import com.amazonaws.services.rekognition.model.Face;
import com.amazonaws.services.rekognition.model.FaceRecord;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.IndexFacesRequest;
import com.amazonaws.services.rekognition.model.IndexFacesResult;
import com.amazonaws.services.rekognition.model.ListFacesRequest;
import com.amazonaws.services.rekognition.model.ListFacesResult;
import com.amazonaws.services.rekognition.model.S3Object;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class storeFaces {

    @RequestMapping(method = RequestMethod.POST, value = "/addImage")
    public ModelAndView IndexAndListFacesExample() {

        String COLLECTION_ID = "colletionimagescloud";
        //String S3_BUCKET_SOURCE = "anjanasrekog";
        String S3_BUCKET_TARGET = "targetimages";
        AWSCredentials credentials;

        ModelAndView view = new ModelAndView();
        try {
            credentials = new BasicAWSCredentials("", "");
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (/Users/userid/.aws/credentials), and is in valid format.",
                    e);
        }

        ObjectMapper objectMapper = new ObjectMapper();


        AmazonRekognition amazonRekognition = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();


        // 1. Index face 1
        Image image = getImageUtil(S3_BUCKET_TARGET, "image1.jpg");
        String externalImageId = "image1.jpg";
        IndexFacesResult indexFacesResult = callIndexFaces(COLLECTION_ID,
                externalImageId, "ALL", image, amazonRekognition);
        System.out.println(externalImageId + " added");
        List < FaceRecord > faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected: Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        // 2. Index face 2
        indexFacesResult = null;
        faceRecords = null;
        Image image2 = getImageUtil(S3_BUCKET_TARGET, "image2.jpg");
        String externalImageId2 = "image2.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId2,
                "ALL", image2, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image3 = getImageUtil(S3_BUCKET_TARGET, "image3.jpg");
        String externalImageId3 = "image3.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId3,
                "ALL", image3, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image4 = getImageUtil(S3_BUCKET_TARGET, "image4.jpg");
        String externalImageId4 = "image9.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId4,
                "ALL", image4, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }
        indexFacesResult = null;
        faceRecords = null;
        Image image9 = getImageUtil(S3_BUCKET_TARGET, "image9.jpg");
        String externalImageId9 = "image9.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId9,
                "ALL", image9, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image5 = getImageUtil(S3_BUCKET_TARGET, "image5.jpg");
        String externalImageId5 = "image5.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId5,
                "ALL", image5, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image8 = getImageUtil(S3_BUCKET_TARGET, "image8.jpg");
        String externalImageId8 = "image8.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId8,
                "ALL", image8, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image7 = getImageUtil(S3_BUCKET_TARGET, "image7.jpg");
        String externalImageId7 = "image7.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId7,
                "ALL", image7, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image6 = getImageUtil(S3_BUCKET_TARGET, "image6.jpg");
        String externalImageId6 = "image6.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId6,
                "ALL", image6, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image10 = getImageUtil(S3_BUCKET_TARGET, "image10.jpg");
        String externalImageId10 = "image10.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId10,
                "ALL", image10, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image1_c = getImageUtil(S3_BUCKET_TARGET, "image1_c.jpg");
        String externalImageId1_c = "image1_c.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId1_c,
                "ALL", image1_c, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image2_c = getImageUtil(S3_BUCKET_TARGET, "image2_c.jpg");
        String externalImageId2_c = "image2_c.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId2_c,
                "ALL", image2_c, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }

        indexFacesResult = null;
        faceRecords = null;
        Image image3_c = getImageUtil(S3_BUCKET_TARGET, "image3_c.jpg");
        String externalImageId3_c = "image3_c.jpg";
        System.out.println(externalImageId2 + " added");
        indexFacesResult = callIndexFaces(COLLECTION_ID, externalImageId3_c,
                "ALL", image3_c, amazonRekognition);
        faceRecords = indexFacesResult.getFaceRecords();
        for (FaceRecord faceRecord: faceRecords) {
            System.out.println("Face detected. Faceid is " +
                    faceRecord.getFace().getFaceId());
        }



        // 3. Page through the faces with ListFaces
        ListFacesResult listFacesResult = null;
        System.out.println("Faces in collection " + COLLECTION_ID);

        String paginationToken = null;
        do {
            if (listFacesResult != null) {
                paginationToken = listFacesResult.getNextToken();
            }
            listFacesResult = callListFaces(COLLECTION_ID, 1, paginationToken,
                    amazonRekognition);
            List < Face > faces = listFacesResult.getFaces();
            for (Face face: faces) {
                try {
                    System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(face));
                } catch (JsonProcessingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } while (listFacesResult != null && listFacesResult.getNextToken() !=
                null);

        view.setViewName("AddedImage");
        return view;
    }

    private static IndexFacesResult callIndexFaces(String collectionId, String externalImageId,
                                                   String attributes, Image image, AmazonRekognition amazonRekognition) {
        IndexFacesRequest indexFacesRequest = new IndexFacesRequest()
                .withImage(image)
                .withCollectionId(collectionId)
                .withExternalImageId(externalImageId)
                .withDetectionAttributes(attributes);
        return amazonRekognition.indexFaces(indexFacesRequest);

    }

    private static ListFacesResult callListFaces(String collectionId, int limit,
                                                 String paginationToken, AmazonRekognition amazonRekognition) {
        ListFacesRequest listFacesRequest = new ListFacesRequest()
                .withCollectionId(collectionId)
                .withMaxResults(limit)
                .withNextToken(paginationToken);
        return amazonRekognition.listFaces(listFacesRequest);
    }

    private static Image getImageUtil(String bucket, String key) {
        return new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(key));
//	   AmazonRekognitionClient amazonRekognition = new AmazonRekognitionClient(credentials);
//       AmazonS3 clientConnection = new AmazonS3Client(credentials);
//       clientConnection.createBucket("anjanasrekog");
//       CompareFacesRequest compareFaces = new CompareFacesRequest();
//       compareFaces.setSourceImage(getImageUtil(S3_BUCKET_SOURCE,"IMG_7266.JPG"));
//       compareFaces.setTargetImage(getImageUtil(S3_BUCKET_TARGET,"*"));
//       CompareFacesResult compareFaceResult = amazonRekognition.compareFaces(compareFaces);
//       List <CompareFacesMatch> faceDetails = compareFaceResult.getFaceMatches();
//
//       for (CompareFacesMatch match: faceDetails){
//
//       ComparedFace face= match.getFace();
//
//       BoundingBox position = face.getBoundingBox();
//
//       System.out.println("Face at " + position.getLeft().toString()
//
//       + " " + position.getTop()
//
//       + " matches with " + face.getConfidence().toString()
//
//       + "% confidence.");
//
//
//
//       }
//
//
//       view.setViewName("AddedImage");
//       return view;
    }


//   private static Image getImageUtil(String bucket, String key){
//
//       return new Image()
//               .withS3Object(new S3Object().withBucket(bucket).withName(key));
//   }
//
    
    
 

}



