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


