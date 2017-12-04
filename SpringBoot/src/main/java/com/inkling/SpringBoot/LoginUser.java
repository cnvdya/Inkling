package com.inkling.SpringBoot;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.inkling.forms.imageAttributes;
import com.mysql.jdbc.Connection;

import javazoom.jl.decoder.JavaLayerException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Controller
public class LoginUser {
  
  

	String name = "";
	String password = "";
	String externalImageId = "";

	@Autowired
	Label label;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView Login(HttpServletRequest request, HttpSession session)
			throws SQLException, JavaLayerException, InterruptedException, IOException {
		String curlabel = "";
		ModelAndView view = new ModelAndView();
		imageAttributes imageURL = new imageAttributes();
		String imageURLString;
		List<imageAttributes> imageURLAll = new ArrayList<imageAttributes>() ;
		Boolean flag = false;
		Connection conn = getConnection();
		AWSCredentials credentials;
		try {
			credentials = new BasicAWSCredentials("", "");
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (/Users/userid/.aws/credentials), and is in valid format.", e);
		}
		
		//Get all images in target bucket
		AmazonS3 s3Connection = new AmazonS3Client(credentials);
		final ListObjectsV2Request req = new ListObjectsV2Request().withBucketName("targetimages");
		ListObjectsV2Result s3Objects;
		s3Objects = s3Connection.listObjectsV2(req);
		for(S3ObjectSummary s3SummaryOfFiles : s3Objects.getObjectSummaries()) {
			imageAttributes img = new imageAttributes();
			img.setImageURL(s3SummaryOfFiles.getKey());
			imageURLAll.add(img);
		}
		
		name = (request.getParameter("name")).toLowerCase();
		password = request.getParameter("password");
		System.out.println(name);
		System.out.println(password);
		String query = "SELECT UserName,ExternalImageId FROM CloudDB.LoginUserImg where UserName = ? and Password = ?;";
		PreparedStatement setupStatement = conn.prepareStatement(query);
		setupStatement.setString(1, name);
		setupStatement.setString(2, password);
		ResultSet rs = setupStatement.executeQuery();
		while (rs.next()) {

			flag = true;
			externalImageId = rs.getString(2);

		}
		imageURL.setImageURLSource(externalImageId);
		
		imageURLString = imageURL.getImageURLSource();
		if (flag == true) {
//			view.addObject("name", name);
			view.addObject("imageURLString", imageURLString);
			view.addObject("imageURLAll", imageURLAll);
			System.out.println(imageURLString);
			System.out.println(imageURLString);
			String SAMPLE = "Hey " + name +"! Welcome to Inkling. Hope you are doing great.";
			System.out.println(SAMPLE);
			AmazonPolly amazonPolly = AmazonPollyClientBuilder.standard().withRegion(Regions.US_EAST_1)
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
			DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
			// Synchronously ask Amazon Polly to describe available TTS voices.
			DescribeVoicesResult describeVoicesResult = amazonPolly.describeVoices(describeVoicesRequest);
			Voice voice = describeVoicesResult.getVoices().get(0);
			OutputFormat format = OutputFormat.Mp3;
			SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(SAMPLE).withVoiceId(voice.getId())
					.withOutputFormat(format);
			SynthesizeSpeechResult synthRes = amazonPolly.synthesizeSpeech(synthReq);
			InputStream speechStream = synthRes.getAudioStream();
			String pollyURL = "https://s3.amazonaws.com/mp3bucketpolly/" + name + ".mp3";
//			File targetFile = new File("/Users/andrew/Documents/anjana/sam.mp3");	
//			java.nio.file.Files.copy(
//					speechStream, 
//				      targetFile.toPath(), 
//				      StandardCopyOption.REPLACE_EXISTING);
//				 
//				    IOUtils.closeQuietly(speechStream);
			// create an MP3 player
//			AdvancedPlayer player = new AdvancedPlayer(speechStream,
//					javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
//			System.out.println("6");
//			player.setPlayBackListener(new PlaybackListener() {
//				@Override
//				public void playbackStarted(PlaybackEvent evt) {
//					System.out.println("Playback started");
//					System.out.println(SAMPLE);
//				}
//
//				@Override
//				public void playbackFinished(PlaybackEvent evt) {
//					System.out.println("Playback finished");
//				}
//			});
//
//			// play it!
//			Thread.sleep(2000);
//			player.play();
			try{
				curlabel=label.getcurlabel();
				System.out.println("new value  : "+curlabel);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			view.addObject("name",name);
			view.addObject("pollyURL",pollyURL);
			view.addObject("speechStream",speechStream);
			view.addObject("imageURLString", imageURLString);
			view.setViewName("Dashboard");
		} else {
			System.out.println("false");
			request.setAttribute("message", "Invalid Credentials");
			view.setViewName("LoginInvalid");
		}
		String rname="";
		System.out.println("instance variable name  "+name);

		if(name.equals("sam")||name.equals("Sam")||name.equals("SAM")){
			rname="Sam";
		}
		if(name.equals("sarah")||name.equals("Sarah")||name.equals("SARAH")){
			rname="Sarah";
		}

		session = request.getSession(true);
		session.setAttribute("externalImageId", externalImageId);
		session.setAttribute("name", rname);
		session.setAttribute("imageURLString", imageURLString);

		view.addObject("name",rname);
		view.addObject("curlabel",curlabel);


		return view;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public ModelAndView Logout(HttpServletRequest request, HttpSession session)
			throws SQLException, JavaLayerException, InterruptedException, IOException {
		String curlabel = "";
		ModelAndView view = new ModelAndView();

		AWSCredentials credentials;
		try {
			credentials = new BasicAWSCredentials("", "");
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (/Users/userid/.aws/credentials), and is in valid format.", e);
		}
		String SAMPLE = "Thank you " + name +" for using Inking!!  Good Bye. ";
		System.out.println(SAMPLE);
		AmazonPolly amazonPolly = AmazonPollyClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
		// Synchronously ask Amazon Polly to describe available TTS voices.
		DescribeVoicesResult describeVoicesResult = amazonPolly.describeVoices(describeVoicesRequest);
		Voice voice = describeVoicesResult.getVoices().get(0);
		OutputFormat format = OutputFormat.Mp3;
		SynthesizeSpeechRequest synthReq = new SynthesizeSpeechRequest().withText(SAMPLE).withVoiceId(voice.getId())
				.withOutputFormat(format);
		SynthesizeSpeechResult synthRes = amazonPolly.synthesizeSpeech(synthReq);
		InputStream speechStream = synthRes.getAudioStream();
		String pollyURL = "https://s3.amazonaws.com/mp3bucketpolly/" + "logout" + ".mp3";
		try{
			curlabel=label.getcurlabel();
			System.out.println("new value  : "+curlabel);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		view.addObject("name",name.toUpperCase());
		view.addObject("pollyURL",pollyURL);
		view.addObject("speechStream",speechStream);
		view.setViewName("myView");
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
