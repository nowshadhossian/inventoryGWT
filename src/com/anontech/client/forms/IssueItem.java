package com.anontech.client.forms;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class IssueItem extends Composite {

	private static IssueItemUiBinder uiBinder = GWT
			.create(IssueItemUiBinder.class);
	@UiField AbsolutePanel withoutRequisition;
	@UiField Button btnWithoutRequisition;

	interface IssueItemUiBinder extends UiBinder<Widget, IssueItem> {
	}

	public IssueItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@UiHandler("btnWithoutRequisition")
	void onBtnWithoutRequisitionClick(ClickEvent event) {
		withoutRequisition.setVisible(true);
	}
}
