package com.anontech.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MainWindow extends Composite  {

	private static MainWindowUiBinder uiBinder = GWT.create(MainWindowUiBinder.class);
	@UiField HTMLPanel panel;
	
	interface MainWindowUiBinder extends UiBinder<Widget, MainWindow> {
	}
	public MainWindow() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
