package com.anontech.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkspacePanel extends Composite  {

	private static WorkspacePanelUiBinder uiBinder = GWT
			.create(WorkspacePanelUiBinder.class);

	interface WorkspacePanelUiBinder extends UiBinder<Widget, WorkspacePanel> {
	}

	@UiField public static HTMLPanel panel;
	
	public WorkspacePanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}




}
