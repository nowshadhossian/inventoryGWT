package com.anontech.service;

import com.anontech.db.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/loginService")
public interface LoginService extends RemoteService{
	 public abstract User login(String name, String password) ;
	 public abstract User getLoggedUserFromSession();
	 public abstract void logout();
	  
}
