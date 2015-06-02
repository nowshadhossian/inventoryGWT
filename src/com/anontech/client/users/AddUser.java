package com.anontech.client.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.anontech.client.factory.FormBinder;
import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.db.RoleEnum;
import com.anontech.db.UserDetails;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;

public class AddUser extends Composite implements Editor<UserDetails>{

	private static AddUserUiBinder uiBinder = GWT.create(AddUserUiBinder.class);
	@UiField Button btnSave;
	@UiField ValueBoxEditorDecorator<String> name;
	@UiField ValueBoxEditorDecorator<String> login;
	@UiField ValueBoxEditorDecorator<String> password;
	@UiField ValueBoxEditorDecorator<String> email;
	@UiField(provided = true) 
	  ValueListBox<Integer> role = new ValueListBox(new Renderer<Integer>() {
	    public String render(Integer c) {
	      return RoleEnum.enumMap.get(c);
	    }
	    public void render(Integer object, Appendable appendable) throws IOException {
	      String s = render(object);
	      appendable.append(s);
	    }
	  });
	@UiField TextBox phone;
	
	interface AddUserUiBinder extends UiBinder<Widget, AddUser> {
	}
	
	private final FormBinder<UserDetails, Editor<UserDetails>> userDriver;
	interface Driver extends  SimpleBeanEditorDriver<UserDetails, AddUser>{}
	Driver driver = GWT.create(Driver.class);

	private final InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);
	
	public AddUser(UserDetails p) {
		role.setAcceptableValues(new ArrayList(RoleEnum.enumMap.keySet()));
		initWidget(uiBinder.createAndBindUi(this));
		userDriver = GWT.create(FormBinder.class);
		userDriver.initialize(this, driver , p);
		//userDriver.edit(p);
	}

	@UiHandler("btnSave")
	void onBtnSaveClick(ClickEvent event) {
		boolean shouldSave = userDriver.bind();
		if(shouldSave){
			UserDetails user = driver.flush();
			inventoryServiceAsync.saveOrUpdate(user, new MyAssyncCallback());
		}
	}
}
