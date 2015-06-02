package com.anontech.client;

import com.anontech.client.find.SearchPanel;
import com.anontech.client.find.SearchPanelSearcher;
import com.anontech.client.forms.IssueItem;
import com.anontech.client.forms.Requisition;
import com.anontech.client.items.AddItemForm;
import com.anontech.client.items.EquipementPanel;
import com.anontech.client.items.VendorDeptPanel;
import com.anontech.client.items.VendorEditor;
import com.anontech.client.purchase.LocalInvoice;
import com.anontech.client.users.AddUser;
import com.anontech.db.Department;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ActionsPanel extends Composite {

	private static ActionsPanelUiBinder uiBinder = GWT
			.create(ActionsPanelUiBinder.class);
	@UiField Anchor equipementPanel;
	@UiField Anchor createItem;
	@UiField Anchor addLocalPurchase, requisition, vendorDeptPanel;
	@UiField Anchor issueItem;
	@UiField Anchor addUser;
	@UiField Anchor localPurchase;

	interface ActionsPanelUiBinder extends UiBinder<Widget, ActionsPanel> {
	}

	public ActionsPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("createItem")
	void onCreateItemClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new AddItemForm());
	}
	@UiHandler("addLocalPurchase")
	void onAddLocalPurchaseClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new LocalInvoice(new Invoice()));
	}
	@UiHandler("requisition")
	void onRequisitionClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new Requisition());
	}
	@UiHandler("vendorDeptPanel")
	void onVendorPanelClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new VendorDeptPanel());
	}
	@UiHandler("equipementPanel")
	void onEquipementPanelClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new EquipementPanel());
	}
	@UiHandler("issueItem")
	void onIssueItemClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new IssueItem());
	}
	@UiHandler("receiveItem")
	void onReceiveItemClick(ClickEvent event) {
		Department department = new Department();
		department.setName("Finance");
		department.setCode("fin");
		Vendor vendor = new Vendor();
		vendor.setAddress("uttara");
		vendor.setContactName("Nowshad");
		vendor.setContactMobileNo("3535");
		vendor.setDepartment(department);
		
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new VendorEditor(vendor));
	}
	@UiHandler("addUser")
	void onAddUserClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new AddUser(new UserDetails()));
	}
	@UiHandler("listUser")
	void onListUserClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new SearchPanel(SearchPanelSearcher.USER));
	}
	@UiHandler("localPurchase")
	void onLocalPurchaseClick(ClickEvent event) {
		WorkspacePanel.panel.clear();
		WorkspacePanel.panel.add(new SearchPanel(SearchPanelSearcher.PURCHASE));
	}
}
