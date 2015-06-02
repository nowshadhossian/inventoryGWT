package com.anontech.client.items;

import com.anontech.db.Vendor;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.core.client.GWT;
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

public class AddVendor extends Composite  {

	private static AddVendorUiBinder uiBinder = GWT
			.create(AddVendorUiBinder.class);
	@UiField TextBox tbxCompanyName;
	@UiField TextBox tbxCompanyAddress;
	@UiField TextBox tbxCompanyPhone;
	@UiField TextBox tbxFax;
	@UiField TextBox tbxEmail;
	@UiField TextBox tbxContactName;
	@UiField TextBox tbxContactMobile;
	@UiField Button btnSave;
	
	private Vendor vendor;

	private final InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);
	
	interface AddVendorUiBinder extends UiBinder<Widget, AddVendor> {
	}

	public AddVendor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void initializeTbxValues(Vendor vendor) {
		this.vendor = vendor;
		tbxCompanyName.setText(vendor.getName());
		tbxCompanyAddress.setText(vendor.getAddress());
		tbxCompanyPhone.setText(vendor.getPhone());
		tbxFax.setText(vendor.getFax());
		tbxEmail.setText(vendor.getEmail());
		tbxContactName.setText(vendor.getContactName());
		tbxContactMobile.setText(vendor.getContactMobileNo());
	}
	public Vendor initializeClassValues(){
		if(vendor == null)
			vendor = new Vendor();
		vendor.setName(tbxCompanyName.getValue());
		vendor.setAddress(tbxCompanyAddress.getValue());
		vendor.setPhone(tbxCompanyPhone.getValue());
		vendor.setFax(tbxFax.getValue());
		vendor.setEmail(tbxEmail.getValue());
		vendor.setContactName(tbxContactName.getValue());
		vendor.setContactMobileNo(tbxContactMobile.getValue());
		return vendor;
	}

	@UiHandler("btnSave")
	void onBtnSaveClick(ClickEvent event) {
		Vendor vendor = initializeClassValues();
		inventoryServiceAsync.saveOrUpdate(vendor, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Saved");
			}
		});
	}
}
