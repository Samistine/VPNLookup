package com.samistine.vpnlookup.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Samuel Seidel
 */
public class HTTPClient {

    private final String USER_AGENT = "Samistine-EmbeddedHTTPClient/1.0 - VPNLookup";

    public HTTPClient() {
    }

    // HTTP GET request
    public String get(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        // add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        // set reasonable timeout
        con.setConnectTimeout(2000);//2 seconds

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            System.out.println("Sending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        //StringBuffer response = new StringBuffer();
        StringBuilder response = new StringBuilder();

        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } finally {
            in.close();
        }

        // print result
        //System.out.println(response.toString());
        return response.toString();

    }

}
