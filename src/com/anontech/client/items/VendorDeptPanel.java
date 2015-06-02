package com.anontech.client.items;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DecoratedTabPanel;

public class VendorDeptPanel extends Composite {

	private static VendorDeptPanelUiBinder uiBinder = GWT
			.create(VendorDeptPanelUiBinder.class);
	public @UiField DecoratedTabPanel tabPanel;
	
	private static VendorDeptPanel vendorDeptPanel;

	interface VendorDeptPanelUiBinder extends UiBinder<Widget, VendorDeptPanel> {
	}

	public VendorDeptPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		vendorDeptPanel = this; 
	}
	public static VendorDeptPanel getInstance(){
		if(vendorDeptPanel == null){
			vendorDeptPanel = new VendorDeptPanel(); 
		}
		return vendorDeptPanel;
	}


}
