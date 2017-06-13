package com.projectkaiser.realm;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.apache.catalina.Realm;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;

public class PKRealm extends RealmBase{

	protected static final String name = "Project Kaiser Realm";
	
	private String url;
	
	public static String getSoapUrl(String url){
		if(null == url){
			return url;
		}
		url = url.trim();
		if(url.endsWith("/")){
			url = url.substring(0, url.length() - 1);
		}
		if(url.endsWith("soap")){
			return url;
		}
		return url + "/soap";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		
		PKAPIClient cl = new PKAPIClient(getSoapUrl(url));
		if(null == cl.queryUserId(usr, pwd)){
			return null;
		}
		
		{
			Class[] intTomcat6Args = new Class[] { Realm.class, String.class, String.class, List.class };
			// Class[] intTomcat7Args = new Class[] { String.class, String.class, List.class };
			
			Constructor ctor = null;
			Class clsGenericPrincipal = GenericPrincipal.class;
			try {
				ctor = clsGenericPrincipal.getConstructor(intTomcat6Args);
			} catch (NoSuchMethodException | SecurityException e) {
			}
			if(null == ctor){
				return new GenericPrincipal(usr,  pwd, Arrays.asList("pkuser"));				
			} else {
				try {
					return (Principal) ctor.newInstance(new Object[]{this, usr,  pwd, Arrays.asList("pkuser")});
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.out.println("*** PKRealm: something wrong with GenericPrincipal constructor:");
					e.printStackTrace();
					return null;
				}
			}
		}

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
