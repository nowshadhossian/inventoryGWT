package com.anontech.client;

import com.anontech.db.User;
import com.anontech.service.LoginService;
import com.anontech.service.LoginServiceAsync;
import com.anontech.utils.Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginUI extends Composite {

	private final Label userNameLabel = new Label("User name:");
	private final Label passwordLabel = new Label("Password:");
	private final TextBoxBase userNameField = new TextBox();
	private final TextBoxBase passwordField = new PasswordTextBox();
	private final Button loginBtn = new Button("Login");

	private final LoginServiceAsync loginServiceAsync = GWT
			.create(LoginService.class);

	public LoginUI() {
		final FormPanel form = new FormPanel();
		form.add(this.createAndFormatContentPanel());
		this.initWidget(form);
		this.loginBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(Utils.isEmpty(userNameField.getText())) {
					Window.alert("User name is required");
					userNameField.setFocus(true);
					return;
				} else if(Utils.isEmpty(userNameField.getText())) {
					Window.alert("Password is required");
					passwordField.setFocus(true);
					return; 
				}
				loginServiceAsync.login(userNameField.getText(), passwordField.getText(),
						new AsyncCallback<User>() {
					public void onFailure(Throwable caught) {
						Window.alert("Login Failed");
					}

					public void onSuccess(User user) {
						if(user == null) {
							Window.alert("Login Failed");
							return;
						}
						RootPanel.get().clear();
						MainWindow mainWindow = new MainWindow();
						RootPanel.get().add(mainWindow);
					}
				});
			}
		});
	}

	private Widget createAndFormatContentPanel() {
		VerticalPanel verticalPanel = new VerticalPanel();
		Image image = new Image("myimage/logo.png");
		verticalPanel.add(image);
		final Grid grid = new Grid(3,2);
		this.userNameField.setWidth("22em");
		this.passwordField.setWidth("22em");
		this.loginBtn.setWidth("7em");
		grid.setWidget(0, 0, this.userNameLabel);
		grid.setWidget(0, 1, this.userNameField);
		grid.setWidget(1, 0, this.passwordLabel);
		grid.setWidget(1, 1, this.passwordField);
		grid.setWidget(2, 1, this.loginBtn);
		verticalPanel.add(grid);
		return verticalPanel;
	}
}
