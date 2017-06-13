package com.projectkaiser.realm;

import junit.framework.TestCase;

public class PKRealmTest extends TestCase {
	
	public void testGetSoapUrl() throws Exception{
		
		assertEquals(null, PKRealm.getSoapUrl(null));
		assertEquals("https://www.mycompany.com/pk/soap", PKRealm.getSoapUrl("https://www.mycompany.com/pk/"));
		assertEquals("https://www.mycompany.com/pk/soap", PKRealm.getSoapUrl("https://www.mycompany.com/pk"));
		assertEquals("https://www.mycompany.com/pk/soap", PKRealm.getSoapUrl("https://www.mycompany.com/pk/soap"));
		assertEquals("https://www.mycompany.com/pk/soap", PKRealm.getSoapUrl("https://www.mycompany.com/pk/soap/"));
		assertEquals("https://www.mycompany.com/soap", PKRealm.getSoapUrl("https://www.mycompany.com"));
		assertEquals("https://www.mycompany.com/soap", PKRealm.getSoapUrl("https://www.mycompany.com/"));
		assertEquals("https://www.mycompany.com/soap", PKRealm.getSoapUrl("https://www.mycompany.com/soap"));
		assertEquals("https://www.mycompany.com/soap", PKRealm.getSoapUrl("https://www.mycompany.com/soap/"));
		
		assertEquals("https://www.mycompany.com:8080/soap", PKRealm.getSoapUrl("https://www.mycompany.com:8080/soap/"));
	}

}
