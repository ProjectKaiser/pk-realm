package com.projectkaiser.realm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class PKRealmTest extends TestCase {
	
	/**
	 * 
	 */
	public static final String SOAP_URL = "http://localhost:8080/alpha/soap";
	public void testOnRealPK(){
    	PKAPIClient pkClient = new PKAPIClient(SOAP_URL); 
        String s = pkClient.login("u1", "qq");
        assertNotNull(s);
        System.out.println(s);
	}
	
	public void testMatcher(){
		
		// "result"[^"]*:[^"]*"([^"])"
		
        String s = "{\"jsonrpc\":\"2.0\",\"id\":1,\"result\":\"fab52936-e81f-4d8b-a4e1-f4ec0886f778\"}";
        String exp = "\"result\"[^\"]*:[^\"]*\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(exp);
        Matcher m = pattern.matcher(s);
        String res = null;
        while(m.find()){
        	res = m.group(1);
        }
        assertNotNull(res);
	}
	

}
