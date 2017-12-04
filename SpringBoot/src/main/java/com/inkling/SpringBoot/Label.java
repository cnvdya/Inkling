package com.inkling.SpringBoot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class Label {

    public String getcurlabel () throws Exception{
        String label = "";
        String output = null;
        try {

            URL url = new URL("https://4ofiss7q6l.execute-api.us-east-1.amazonaws.com/prod/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                label=label+output;
                System.out.println(output);
            }
            System.out.println("Label "+label.toLowerCase());


            InklingLabel inklingLabel = new ObjectMapper().readValue(label.toLowerCase(), InklingLabel.class);

            System.out.println(inklingLabel.getInklingpictures());
            conn.disconnect();

            return inklingLabel.getInklingpictures();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    return label;
    }
}
