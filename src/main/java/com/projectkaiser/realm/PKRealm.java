package com.projectkaiser.realm;

import java.security.Principal;
import java.util.Arrays;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;

public class PKRealm extends RealmBase{

	protected static final String name = "Project Kaiser Realm";
	
	private String url;
	
	@Override
	public Principal authenticate(String usr, String pwd) {
		if(null == url){
			return null;
		}
		if(null == usr){
			return null;
		}
		if(null == pwd){
			return null;
		}	
		
		PKAPIClient cl = new PKAPIClient(url);
		if(null == cl.queryUserId(usr, pwd)){
			return null;
		}
		return new GenericPrincipal(usr,  pwd, Arrays.asList("pkuser"));

	}
	
	@Override
	protected String getName() {
		return name;
	}

	@Override
	protected String getPassword(String arg0) {
		return null;
	}

	@Override
	protected Principal getPrincipal(String arg0) {
		return null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
