package com.anontech.client;
import com.anontech.client.factory.PopularUI;
import com.anontech.service.LoginService;
import com.anontech.service.LoginServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MyTopPanel extends Composite{
	private static MyTopPanelUiBinder uiBinder = GWT.create(MyTopPanelUiBinder.class);
	@UiField Button btnLogout;

	interface MyTopPanelUiBinder extends UiBinder<Widget, MyTopPanel> {
	}
	
	private final LoginServiceAsync loginServiceAsync = GWT
		.create(LoginService.class);
	
	public MyTopPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnLogout")
	void onBtnLogoutClick(ClickEvent event) {
		loginServiceAsync.logout(new AsyncCallback<Void>() {
			@Override
			public void onSuccess(Void result) {
				PopularUI.LoginWidgetAfterLogout();
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}