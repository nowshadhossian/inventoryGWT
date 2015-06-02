package com.anontech.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class InventoryGWT implements EntryPoint {
	
	public void onModuleLoad() {
	//	LoginUI loginUI = new LoginUI();
	//	RootPanel.get().add(loginUI, (Window.getClientWidth()/2)-100, (Window.getClientHeight()/2)-200);
		
		MainWindow mainWindow = new MainWindow();
		RootPanel.get().add(mainWindow);//plz disable the last two lines and enable the top part
	}
}
