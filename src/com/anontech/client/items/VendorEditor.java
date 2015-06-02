package com.anontech.client.items;

import java.util.ArrayList;
import java.util.HashMap;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.anontech.utils.LoginFailedException;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.editor.ui.client.ValueBoxEditorDecorator;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class VendorEditor extends Composite implements Editor<Vendor> {

	private static VendorEditorUiBinder uiBinder = GWT
			.create(VendorEditorUiBinder.class);
	@UiField Button save;
	@UiField ValueBoxEditorDecorator<String> address;
	@UiField ValueBoxEditorDecorator<String> contactName;
	//@UiField TextBox contactMobileNo;
	@UiField ValueBoxEditorDecorator<String> contactMobileNo;
	@Path("department.name")
	@UiField TextBox deptName;
	interface VendorEditorUiBinder extends UiBinder<Widget, VendorEditor> {
	}

	private final EditVendorWorkflow<Vendor, Editor<Vendor>> vendorDriver;
	
	interface Driver extends  SimpleBeanEditorDriver<Vendor, VendorEditor>{}
	Driver driver = GWT.create(Driver.class);
	
	private final InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);
	
	public VendorEditor(Vendor p) {
		initWidget(uiBinder.createAndBindUi(this));
		vendorDriver = GWT.create(EditVendorWorkflow.class);
		vendorDriver.initialize(this, driver);
		vendorDriver.edit(p);
		
	}

	@UiHandler("save")
	void onSaveClick(ClickEvent event) {
		boolean shouldSave = vendorDriver.bind();
		if(shouldSave){
			Vendor v =driver.flush();
			v.setName("hawa");
			v.setPhone("333");
			
			inventoryServiceAsync.saveOrUpdate(v, new AsyncCallback<Void>() {
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
				public void onSuccess(Void result) {
					Window.alert("Saved");
				}
			});
		}
	}
	
	
	
}
