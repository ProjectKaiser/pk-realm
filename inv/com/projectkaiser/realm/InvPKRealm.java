package com.projectkaiser.realm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

// http://stackoverflow.com/questions/3251354/making-json-rpc-calls-from-java

public class InvPKRealm {

	
	public static final String SOAP_URL = "http://localhost:8080/pk/soap";  
	
	@SuppressWarnings("rawtypes")
	public static String readResource(Class cls, String resourceName){
	    StringBuffer res = new StringBuffer();
	    char buf[] = new char[256];
	    try {
	        InputStream is = cls.getResourceAsStream(resourceName);
	        InputStreamReader iReader = new InputStreamReader(is, "utf-8");
	        BufferedReader br = new BufferedReader(iReader);
	        int nRead;
	        while ((nRead = br.read(buf)) > 0) {
	            res.append(buf, 0, nRead);
	        }
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    return res.toString();
	}
	
    public static void main(String[] args) throws Exception {
        URL url = new URL(SOAP_URL);

        
        String login = "{\"jsonrpc\":\"2.0\", \"method\":\"login\", \"params\":[\"u1\",\"q\"]}";
        //String logout = "{\"jsonrpc\":\"2.0\", \"method\":\"logout\", \"params\":[]}";

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/json");
        //conn.setRequestProperty("Content-Length", String.valueOf(postData.length()));
        conn.setDoOutput(true);
        conn.getOutputStream().write(login.getBytes("UTF-8"));

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

//        for (int c; (c = in.read()) >= 0;)
//            System.out.print((char)c);
        
        StringBuffer sb = new StringBuffer(); 
        for (int c; (c = in.read()) >= 0;)sb.append((char)c);
        String s = sb.toString();
        System.out.println("s=" + s);

    }
	
}
