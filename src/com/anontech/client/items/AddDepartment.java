package com.anontech.client.items;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.reflections.Reflections;

import com.anontech.client.factory.PopularUI;
import com.anontech.db.Department;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.anontech.utils.LoginFailedException;
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

public class AddDepartment extends Composite {

	private static AddDepartmentUiBinder uiBinder = GWT
			.create(AddDepartmentUiBinder.class);
	@UiField TextBox tbx_name;
	@UiField TextBox tbx_code;
	@UiField Button btnSave;
	
	private Department department;
	
	private final InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);

	interface AddDepartmentUiBinder extends UiBinder<Widget, AddDepartment> {
	}

	public AddDepartment() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void initializeTbxValues(Department department) {
		this.department = department;
		tbx_name.setText(department.getName());
		tbx_code.setText(department.getCode());
	}
	public Department initializeClassValues(){
		if(department == null)
			department = new Department();
		department.setName(tbx_name.getValue());
		department.setCode(tbx_code.getValue());
		return department;
	}

	private void resetFields() {
		tbx_name.setText("");
		tbx_code.setText("");
	}

	@UiHandler("btnSave")
	void onBtnSaveClick(ClickEvent event) {
		Department department = initializeClassValues();

		ValidatorFactory factory = Validation.byDefaultProvider().configure().buildValidatorFactory();
		Validator validator = factory.getValidator();
		
		Set<ConstraintViolation<Department>> violations = PopularUI.getValidator().validate(department);
		//Set<ConstraintViolation<Department>> violations = validator.validateProperty(department, "code");
		for(ConstraintViolation<Department> violation : violations) {
			if(violation.getPropertyPath().iterator().next().getName().equals("name"))
				PopularUI.showTextboxErrorMessagePopup(tbx_name, violation.getMessage());
			if(violation.getPropertyPath().iterator().next().getName().equals("code"))
				PopularUI.showTextboxErrorMessagePopup(tbx_code, violation.getMessage());
		 }
		if(violations.isEmpty()){
			inventoryServiceAsync.saveOrUpdate(department, new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					try {
						throw caught;
					}catch (LoginFailedException e) {
						Window.alert(e.getMessage());
						PopularUI.LoginWidgetAfterLogout();
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
				@Override
				public void onSuccess(Void result) {
					Window.alert("Saved");
					resetFields();
				}
			});
		}
	}
	
}
