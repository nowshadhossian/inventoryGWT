package com.anontech.client.items;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EquipementPanel extends Composite{

	private static EquipementPanelUiBinder uiBinder = GWT
			.create(EquipementPanelUiBinder.class);

	interface EquipementPanelUiBinder extends UiBinder<Widget, EquipementPanel> {
	}

	public EquipementPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
