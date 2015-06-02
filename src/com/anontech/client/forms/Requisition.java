package com.anontech.client.forms;

import com.anontech.client.factory.PopularUI;
import com.anontech.client.find.SearchPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Requisition extends Composite  {

	private static RequisitionUiBinder uiBinder = GWT
			.create(RequisitionUiBinder.class);
	@UiField Button addItemRequisition;

	interface RequisitionUiBinder extends UiBinder<Widget, Requisition> {
	}

	public Requisition() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@UiHandler("addItemRequisition")
	void onAddItemRequisitionClick(ClickEvent event) {
		PopularUI popularUI = new PopularUI();
		popularUI.showDialogBox(new SearchPanel(), "Search Item");
	}
}
