package com.inkling.SpringBoot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import java.io.InputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPolly;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

@Controller
public class speechPolly{

	AmazonPollyClient polly;
	Voice voice;
	@RequestMapping(method = RequestMethod.POST, value = "/speech")
	public ModelAndView speech(HttpServletRequest request, HttpSession session) throws JavaLayerException, InterruptedException {
		AWSCredentials credentials;
		ModelAndView view = new ModelAndView();
		
		try {
			credentials = new BasicAWSCredentials("", "");
		} catch (Exception e) {
			throw new AmazonClientException("Cannot load the credentials from the credential profiles file. "
					+ "Please make sure that your credentials file is at the correct "
					+ "location (/Users/userid/.aws/credentials), and is in valid format.", e);
		}
		
		 String imageSource = (String) request.getSession(false).getAttribute("name");
		 String imageURLString = (String) request.getSession(false).getAttribute("imageURLString");
		 
	     System.out.println("imageSource:::::" + imageSource);
		 String SAMPLE = "Welcome" +imageSource;
		 System.out.println(SAMPLE);
		 AmazonPolly amazonPolly = AmazonPollyClientBuilder
                 .standard()
                 .withRegion(Regions.US_EAST_1)
                 .withCredentials(new AWSStaticCredentialsProvider(credentials))
                 .build();
		 DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

         // Synchronously ask Amazon Polly to describe available TTS voices.
         DescribeVoicesResult describeVoicesResult = amazonPolly.describeVoices(describeVoicesRequest);
         Voice voice = describeVoicesResult.getVoices().get(0);

//         SynthesizeSpeechRequest synthReq =
//                 new SynthesizeSpeechRequest().withText(SAMPLE).withVoiceId(voice.getId())
//                         .withOutputFormat(OutputFormat.Mp3);
//
//         SynthesizeSpeechResult synthRes = amazonPolly.synthesizeSpeech(synthReq);
				//create the test class
				//PollyDemo helloWorld = new PollyDemo(Region.getRegion(Regions.US_EAST_1));
				//get the audio stream
         OutputFormat format = OutputFormat.Mp3;
         SynthesizeSpeechRequest synthReq = 
        			new SynthesizeSpeechRequest().withText(SAMPLE).withVoiceId(voice.getId()).withOutputFormat(format);
        					
         
         System.out.println("synthReq:::" + synthReq);
         System.out.println("amazonPolly:::" + amazonPolly);
        			SynthesizeSpeechResult synthRes = amazonPolly.synthesizeSpeech(synthReq);
        			System.out.println("synthRes::::"+synthRes);
				System.out.println("After inputstream");
				System.out.println("Before sppech inputstream");
				InputStream speechStream = synthRes.getAudioStream();
				System.out.println("Before sppech inputstream");
				System.out.println("speechStream:::"+speechStream);
				//create an MP3 player
				AdvancedPlayer player = new AdvancedPlayer(speechStream,
						javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());
				player.setPlayBackListener(new PlaybackListener() {
					@Override
					public void playbackStarted(PlaybackEvent evt) {
						System.out.println("Playback started");
						System.out.println(SAMPLE);
					}
					
					@Override
					public void playbackFinished(PlaybackEvent evt) {
						System.out.println("Playback finished");
					}
				});
					
				
				
				// play it!
				
				player.play();
				view.addObject("imageURLString",imageURLString);
				view.setViewName("Dashboard");
				return view;
			
	}
	
//	private InputStream synthesize(String text, OutputFormat format) throws IOException {
//		SynthesizeSpeechRequest synthReq = 
//		new SynthesizeSpeechRequest().withText(text).withVoiceId(voice.getId())
//				.withOutputFormat(format);
//		SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);
//		System.out.println("synthRes::::"+synthRes);
//		return synthRes.getAudioStream();
//	}
}
//public PollyDemo(Region region) {
//	// create an Amazon Polly client in a specific region
//	polly = new AmazonPollyClient(new DefaultAWSCredentialsProviderChain(), 
//	new ClientConfiguration());
//	polly.setRegion(region);
//	// Create describe voices request.
//	DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
//
//	// Synchronously ask Amazon Polly to describe available TTS voices.
//	DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
//	voice = describeVoicesResult.getVoices().get(0);
//}

