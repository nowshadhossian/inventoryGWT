package com.anontech.client.factory;

import com.anontech.utils.LoginFailedException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MyAssyncCallback<T> implements AsyncCallback<T>{
	@Override
	public void onFailure(Throwable caught) {
		try {
			throw caught;
		}catch (LoginFailedException e) {
			Window.alert(e.getMessage());
			PopularUI.LoginWidgetAfterLogout();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onSuccess(T result) {
		Window.alert("Saved");
	}

}
