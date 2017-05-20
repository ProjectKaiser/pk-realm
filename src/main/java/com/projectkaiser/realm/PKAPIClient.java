package com.projectkaiser.realm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PKAPIClient {
	
	private URL url;

	public PKAPIClient(String url){
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String escape(String s){
		s = s.replace("\\", "\\\\");
		s = s.replace("\"", "\\\"");
		return s;
	}
	
	/*
	 * 
	 * OK:
	 * {"jsonrpc":"2.0","id":1,"result":"fab52936-e81f-4d8b-a4e1-f4ec0886f778"}
	 * Error:
	 * {"jsonrpc":"2.0","id":1,"error":{"message":"com.triniforce.utils.ApiAlgs$RethrownException: java.lang.reflect.InvocationTargetException","code":-32600
	 * 
	 */
	
	public String login(String usr, String pwd){
		
		try{
		
			String postData = String.format("{\"jsonrpc\":\"2.0\", \"method\":\"login\", \"params\":[\"%s\",\"%s\"]}"
					,escape(usr)
					,escape(pwd));
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "text/json");
	        conn.setRequestProperty("Content-Length", String.valueOf(postData.length()));
	        conn.setDoOutput(true);
	        conn.getOutputStream().write(postData.getBytes("UTF-8"));
	
	        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	
	        StringBuffer sb = new StringBuffer(); 
	        for (int c; (c = in.read()) >= 0;){
	            sb.append((char)c);
	        }
	        String s = sb.toString();
	        String exp = "\"result\"[^\"]*:[^\"]*\"([^\"]*)\"";
	        Pattern pattern = Pattern.compile(exp);
	        Matcher m = pattern.matcher(s);
	        String res = null;
	        while(m.find()){
	        	res = m.group(1);
	        }
	        return res;
		} catch (Exception e){
			return null;
		}
	}

}
