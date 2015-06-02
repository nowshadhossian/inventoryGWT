package com.anontech.client.purchase;

import java.util.ArrayList;
import java.util.List;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.client.items.AddItemForm;
import com.anontech.db.BulkItem;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.SingleItem;
import com.anontech.db.UserDetails;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.widget.client.TextButton;

public class ItemAddGrid extends Composite {

	private static ItemAddGridUiBinder uiBinder = GWT
			.create(ItemAddGridUiBinder.class);
	@UiField(provided=true) CellTable cellTable = new CellTable();
	@UiField SimplePager pager;
	@UiField Button addRow;
	@UiField TextButton deleteRow;

	SingleSelectionModel selectionModel;
	ListDataProvider<Items> dataProvider;
	
	private final static InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);
	
	
	interface ItemAddGridUiBinder extends UiBinder<Widget, ItemAddGrid> {
	}
	
	public ItemAddGrid() {
		initWidget(uiBinder.createAndBindUi(this));
		ArrayList<Items> list = new ArrayList<Items>();
		initializeItemsTable(list);
	}
	
	protected void initializeItemsTable(ArrayList<Items> result) {
		 TextColumn<Items> equipmentColumn = new TextColumn<Items>() {
		      @Override
		      public String getValue(Items item) {
		    	  if(item.getEquipment() != null)
		    		  return item.getEquipment().getModel();
		    	  return "";
		      }
		  };
		  cellTable.addColumn(equipmentColumn, "Equipment");
		  
	    TextColumn<Items> barcodeColumn = new TextColumn<Items>() {
		      @Override
		      public String getValue(Items item) {
		    	  if(item instanceof SingleItem){
		    		  SingleItem singleItem = (SingleItem) item;
		    		  return singleItem.getBarcode();
		    	  }
		    	  return "";
		      }
		 };
		 cellTable.addColumn(barcodeColumn, "Barcode");
		 
		 TextColumn<Items> serialColumn = new TextColumn<Items>() {
		      @Override
		      public String getValue(Items item) {
		    	  if(item instanceof SingleItem){
		    		  SingleItem singleItem = (SingleItem) item;
		    		  return singleItem.getSerial();
		    	  }
		    	  return "";
		      }
		 };
		 cellTable.addColumn(serialColumn, "Serial");
		 
		Column<Items, Number> quantityColumn = new Column<Items, Number>(new NumberCell()) {
		      @Override
		      public Integer getValue(Items item) {
		    	  if(item instanceof BulkItem){
		    		  BulkItem bulk = (BulkItem) item;
		    		  return bulk.getQuantity();
		    	  }
				return null;
		      }
		 };
		 cellTable.addColumn(quantityColumn, "Quantity");
		 
		 TextColumn<Items> noteColumn = new TextColumn<Items>() {
		      @Override
		      public String getValue(Items item) {
		    	  return item.getNote();
		      }
		 };
		 cellTable.addColumn(noteColumn, "Note");
		 
		 
		 cellTable.setHeight("12px");

		    dataProvider = new ListDataProvider<Items>();
		    // Connect the table to the data provider.
		    dataProvider.addDataDisplay(cellTable);
		    

		    // Add the data to the data provider, which automatically pushes it to the
		    // widget.
		    List<Items> list = dataProvider.getList();
		    for (Items item : result) {
		      list.add(item);
		    }
		    
			//cell selection
			 selectionModel = new SingleSelectionModel<Items>();
			 cellTable.setSelectionModel(selectionModel);
			
			//for pagination
			 pager.setDisplay(cellTable);
			//2 rows per page
			 pager.setPageSize(40);
		
	}

	@UiHandler("addRow")
	void onAddRowClick(ClickEvent event) {
		PopularUI popularUI = new PopularUI();
		popularUI.showDialogBox(new AddItemForm(this), "Add/Edit Item");
	}
	public void addObjToDataProvider(Items item){
		dataProvider.getList().add(item);
	}
	public List<Items> getItems(){
		List<Items> serializeList = new ArrayList<Items>();
		serializeList.addAll(dataProvider.getList());
		return serializeList;
	}
	
	//populate when invoice is loaded
	public void reGenerateCellTable(List<Items> items){
		dataProvider.getList().addAll(items);
	}

	@UiHandler("deleteRow")
	void onDeleteRowClick(ClickEvent event) {
		inventoryServiceAsync.deleteItem((Items) selectionModel.getSelectedObject(), new MyAssyncCallback());
		dataProvider.getList().remove(selectionModel.getSelectedObject());
	}
}
