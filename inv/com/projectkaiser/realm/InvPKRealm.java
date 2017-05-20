package com.projectkaiser.realm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    	
    	PKAPIClient pkClient = new PKAPIClient(SOAP_URL); 
        String s = pkClient.login("u1", "qq");
        System.out.println(s);
    }
	
}
