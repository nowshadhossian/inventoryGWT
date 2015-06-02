package com.anontech.service;

import com.anontech.db.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	void login(String name, String password, AsyncCallback<User> callback);

	void getLoggedUserFromSession(AsyncCallback<User> callback);

	void logout(AsyncCallback<Void> callback);

}
