package com.anontech.client.purchase;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.anontech.client.WorkspacePanel;
import com.anontech.client.factory.FormBinder;
import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.items.AddItemForm;
import com.anontech.db.BulkItem;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.Purchase;
import com.anontech.db.SingleItem;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.TextBox;

public class LocalInvoice extends Composite  implements Editor<Invoice>{

	private static LocalInvoiceUiBinder uiBinder = GWT
			.create(LocalInvoiceUiBinder.class);
	
	@UiField Button savePurchase;
	
	@UiField(provided = true) 
	  ValueListBox<UserDetails> boughtBy = new ValueListBox(new Renderer<UserDetails>() {
	    public String render(UserDetails c) {
	    	if(c != null && c.getName() != null)
	    		return c.getName();
	    	return "";
	    }
	    public void render(UserDetails object, Appendable appendable) throws IOException {
	      String s = render(object);
	      appendable.append(s);
	    }
	  });
	
	@UiField(provided = true) 
	 ValueListBox<Vendor> vendor = new ValueListBox(new Renderer<Vendor>() {
		    public String render(Vendor c) {
		    	if(c != null && c.getName() != null)
		    		return c.getName();
		    	return "";
		    }
		    public void render(Vendor object, Appendable appendable) throws IOException {
		      String s = render(object);
		      appendable.append(s);
		    }
		  });
	
	@UiField LocalPurchase purchase;
	
	
	@UiField @Editor.Ignore ItemAddGrid itemEditor;
	@UiField DateBox buyingDate;
	@UiField TextBox invoiceNumber;
	
	
	interface LocalInvoiceUiBinder extends UiBinder<Widget, LocalInvoice> {
	}

	private final FormBinder<Invoice, Editor<Invoice>> invoiceDriver;
	interface Driver extends  SimpleBeanEditorDriver<Invoice, LocalInvoice>{}
	Driver driver = GWT.create(Driver.class);
	
	private final static InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);
	
	public LocalInvoice(Invoice p) {
		generateListBoxOfUsers();
		generateListBoxOfVendors();
		initWidget(uiBinder.createAndBindUi(this));
		invoiceDriver = GWT.create(FormBinder.class);
		invoiceDriver.initialize(this, driver , p);
		inventoryServiceAsync.getItemsByInvoice(p.getId(), new MyAssyncCallback<ArrayList<Items>>(){
			@Override
			public void onSuccess(ArrayList<Items> result) {
				itemEditor.reGenerateCellTable(result);
			}
			
		});
	}
	
	@UiHandler("savePurchase")
	void onSavePurchaseClick(ClickEvent event) {
		//get purchase obj from form
		boolean shouldSave = purchase.localPurchaseDriver.bind();
		Purchase purchaseObj = null;
		if(shouldSave){
			purchaseObj = purchase.driver.flush();
		}
		
		//get Invoice obj from form
		Invoice invoiceObj = null;
		shouldSave = invoiceDriver.bind();
		if(shouldSave){
			invoiceObj = driver.flush();
		}
		
		//save purchase then invoice then list of items
		inventoryServiceAsync.saveLocalPurchase(purchaseObj, invoiceObj, itemEditor.getItems(), new MyAssyncCallback());
	}
	private void generateListBoxOfUsers(){
		inventoryServiceAsync.getUserDetails(new MyAssyncCallback<ArrayList<UserDetails>>() {
			@Override
			public void onSuccess(ArrayList<UserDetails> result) {
				boughtBy.setAcceptableValues(result);
			}
		});
	}
	
	private void generateListBoxOfVendors(){
		inventoryServiceAsync.getVendors(new MyAssyncCallback<ArrayList<Vendor>>() {
			@Override
			public void onSuccess(ArrayList<Vendor> result) {
				vendor.setAcceptableValues(result);
			}
		});
	}
}
