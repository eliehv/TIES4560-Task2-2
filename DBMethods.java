package com.dbclientgroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.URI;
import java.net.URL;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
//import java.net.URLConnection;



public class DBMethods 
{
	public void helloeli()
	{
		System.out.println("Hello Eli");
		
	}
	public String sendRequest(String str) throws URISyntaxException, IOException 
	{
		// basically builds corresponding GET request that will be returnd to the front-end...
		String appKey = "**************"; //get from AppConsole when create the DropBox App
		String redirectURI="http://localhost:8080/MyDBoxClient/"; //any url to where you want to redirect the user
		URI uri=new URI("https://www.dropbox.com/oauth2/authorize");
		StringBuilder requestUri=new StringBuilder(uri.toString());
		requestUri.append("?client_id=");
		requestUri.append(URLEncoder.encode(appKey,"UTF-8"));
		requestUri.append("&response_type=code");
		requestUri.append("&redirect_uri="+redirectURI.toString());
		requestUri.append(str);
		String queryResult = requestUri.toString();
		System.out.println(queryResult);
		return queryResult;
		/*URL url=new URL("https://api.dropbox.com/oauth2/token");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
		outputStreamWriter.write(queryResult.toString());
		outputStreamWriter.flush();*/
		
	}
	
	
	public String accessToken(String codeStr) throws URISyntaxException, IOException 
	{
		String code = ""+codeStr; //code get from previous step
		String appKey = "b7c81t2d02vnes6"; //get from AppConsole when create the DropBox App
		String appSecret = "bnss1fxtb4hwkh7"; //get from AppConsole when create the DropBox App
		String redirectURI="http://localhost:8080/DB_Client/"; //any url to where you want to redirect the user
		StringBuilder tokenUri=new StringBuilder("code=");
		tokenUri.append(URLEncoder.encode(code,"UTF-8"));
		tokenUri.append("&grant_type=");
		tokenUri.append(URLEncoder.encode("authorization_code","UTF-8"));
		tokenUri.append("&client_id=");
		tokenUri.append(URLEncoder.encode(appKey,"UTF-8"));
		tokenUri.append("&client_secret=");
		tokenUri.append(URLEncoder.encode(appSecret,"UTF-8"));
		tokenUri.append("&redirect_uri="+redirectURI);
		URL url=new URL("https://api.dropbox.com/oauth2/token");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try 
		{
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", "" + tokenUri.toString().length());
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write(tokenUri.toString());
			outputStreamWriter.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			}
			in.close();
			String queryResult = response.toString();
			return queryResult.toString();
			} finally {
			connection.disconnect();
			}
			
	
			
	
	}
		
}
