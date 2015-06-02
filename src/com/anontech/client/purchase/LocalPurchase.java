package com.anontech.client.purchase;

import com.anontech.client.factory.FormBinder;
import com.anontech.client.users.AddUser;
import com.anontech.db.Purchase;
import com.anontech.db.UserDetails;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.DoubleBox;

public class LocalPurchase extends Composite implements Editor<Purchase>{

	private static LocalPurchaseUiBinder uiBinder = GWT
			.create(LocalPurchaseUiBinder.class);
	@UiField DoubleBox otherExpenses;

	interface LocalPurchaseUiBinder extends UiBinder<Widget, LocalPurchase> {
	}
	
	public final FormBinder<Purchase, Editor<Purchase>> localPurchaseDriver;
	interface Driver extends  SimpleBeanEditorDriver<Purchase, LocalPurchase>{}
	Driver driver = GWT.create(Driver.class);

	private final InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);

	public LocalPurchase() {
		Purchase p = new Purchase();
		initWidget(uiBinder.createAndBindUi(this));
		localPurchaseDriver = GWT.create(FormBinder.class);
		localPurchaseDriver.initialize(this, driver , p);
	}
}
