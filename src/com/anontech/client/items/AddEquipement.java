package com.anontech.client.items;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AddEquipement extends Composite {

	//private Equipment equipment;
	private static AddEquipementUiBinder uiBinder = GWT
			.create(AddEquipementUiBinder.class);

	interface AddEquipementUiBinder extends UiBinder<Widget, AddEquipement> {
	}

	public AddEquipement() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	/*public void initializeTbxValues(Equipment equipment) {
		this.equipment = equipment;
		tbxName.setText(department.getName());
		tbxCode.setText(department.getCode());
	}
	public Equipment initializeClassValues(){
		if(department == null)
			department = new Department();
		department.setName(tbxName.getValue());
		department.setCode(tbxCode.getValue());
		return equipment;
	}*/


}
