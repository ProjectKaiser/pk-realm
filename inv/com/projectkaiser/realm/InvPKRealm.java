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

	
	public static final String SOAP_URL = "http://localhost:8080/alpha/soap";  
	
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

        
        // {"jsonrpc":"2.0","id":1,"error":{"message"
        // {"jsonrpc":"2.0","id":1,"result":"565cb6e8-93e8-4925-9983-7b1dd47ffc7d"}
        
        String postData = "{\"jsonrpc\":\"2.0\", \"method\":\"login\", \"params\":[\"u1\",\"q\"]}";

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/json");
        //conn.setRequestProperty("Content-Length", String.valueOf(postData.length()));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postData.getBytes("UTF-8"));

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuffer sb = new StringBuffer(); 
        for (int c; (c = in.read()) >= 0;){
            sb.append((char)c);
        }
        String s = sb.toString();
        System.out.println(s);
    }
	
}
