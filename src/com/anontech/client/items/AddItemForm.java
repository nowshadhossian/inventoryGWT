package com.anontech.client.items;

import java.io.IOException;
import java.util.ArrayList;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.client.find.SearchPanel;
import com.anontech.client.purchase.ItemAddGrid;
import com.anontech.db.BulkItem;
import com.anontech.db.Equipment;
import com.anontech.db.Items;
import com.anontech.db.SingleItem;
import com.anontech.db.UserDetails;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;

public class AddItemForm extends Composite  {

	private static AddItemFormUiBinder uiBinder = GWT
			.create(AddItemFormUiBinder.class);
	@UiField CheckBox isBulkItem;
	@UiField Grid bulkGrid;
	@UiField Grid singleItemGrid;
	@UiField Button addToGrid;
	@UiField(provided = true) 
	  ValueListBox<Equipment> selEquipement = new ValueListBox(new Renderer<Equipment>() {
	    public String render(Equipment c) {
	    	if(c != null && c.getModel() != null)
	    		return c.getModel();
	    	return "";
	    }
	    public void render(Equipment object, Appendable appendable) throws IOException {
	      String s = render(object);
	      appendable.append(s);
	    }
	  });
	
	@UiField TextBox tbxSerial;
	@UiField TextBox tbxBarcode;
	@UiField TextArea tbxNote;
	@UiField TextBox tbxQuantity;

	private final static InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);
	
	//ui name calling AddItemForm 
	ItemAddGrid itemAddGrid;
	interface AddItemFormUiBinder extends UiBinder<Widget, AddItemForm> {
	}

	public AddItemForm() {
		generateListBoxOfEquipement();
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public AddItemForm(ItemAddGrid itemAddGrid) {
		//super();
		generateListBoxOfEquipement();
		initWidget(uiBinder.createAndBindUi(this));
		this.itemAddGrid = itemAddGrid;
	}
	@UiHandler("isBulkItem")
	void onIsBulkItemClick(ClickEvent event) {
		 boolean checked = ((CheckBox) event.getSource()).getValue();
		 if(checked ==true){
			 bulkGrid.setVisible(true);
			 singleItemGrid.setVisible(false);
		 }else{
			 bulkGrid.setVisible(false);
			 singleItemGrid.setVisible(true);
		 }
	}
	@UiHandler("addToGrid")
	void onAddToGridClick(ClickEvent event) {
		 boolean checked = isBulkItem.getValue();
		 if(checked ==true){
			 BulkItem bulkItem = new BulkItem();
			 bulkItem.setEquipment(selEquipement.getValue());
			 bulkItem.setNote(tbxNote.getText());
			 bulkItem.setQuantity(Integer.parseInt(tbxQuantity.getText()));
			 itemAddGrid.addObjToDataProvider(bulkItem);
		 }else{
			 SingleItem singleItem = new SingleItem();
			 singleItem.setBarcode(tbxBarcode.getText());
			 singleItem.setEquipment(selEquipement.getValue());
			 singleItem.setNote(tbxNote.getText());
			 singleItem.setSerial(tbxSerial.getText());
			 itemAddGrid.addObjToDataProvider(singleItem);
		 }
	}
	
	private void generateListBoxOfEquipement(){
		inventoryServiceAsync.getEquipments(new MyAssyncCallback<ArrayList<Equipment>>() {
			@Override
			public void onSuccess(ArrayList<Equipment> result) {
				selEquipement.setAcceptableValues(result);
			}
		});
	}
}
