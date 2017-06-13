package com.projectkaiser.realm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class InvPKAPIClientTest extends TestCase {
	
	public static final String SOAP_URL = "http://localhost:8080/alpha/soap";
	/**
	 * PK server must be running and have usr = u1 pwd = q 
	 */
	public void testOnRealPK(){
    	PKAPIClient pkClient = new PKAPIClient(SOAP_URL); 
        Object s = pkClient.queryUserId("u1", "q");
        assertNotNull(s);
        s = pkClient.queryUserId("u1", "qq");
        assertNull(s);
	}


}
