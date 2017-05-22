package com.projectkaiser.realm;

import java.security.Principal;
import java.util.Arrays;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;

public class PKRealm extends RealmBase{

	protected static final String name = "Project Kaiser Realm";
	

	@Override
	public Principal authenticate(String usr, String pwd) {
		if(null == usr){
			return null;
		}
		if(null == pwd){
			return null;
		}		
		// TODO Auto-generated method stub
		if("usr".equals(usr) && "pwd".equals(pwd)){
			return new GenericPrincipal(usr,  pwd, Arrays.asList("pkuser"));
		}
		return null;
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

}
