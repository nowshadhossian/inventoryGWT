package com.anontech.client.find;

import java.util.ArrayList;
import java.util.List;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.db.Department;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class VendorCellTable {

	private SearchPanel searchPanel;
	private InventoryServiceAsync inventoryServiceAsync;
	
	ListDataProvider<Vendor> dataProvider;
	public VendorCellTable(SearchPanel searchPanel,	InventoryServiceAsync inventoryServiceAsync) {
		this.searchPanel = searchPanel;
		this.inventoryServiceAsync = inventoryServiceAsync;
		
		PopularUI.clearCellTableColumn(searchPanel.cellTable); //clear all column from cellTable
		inventoryServiceAsync.getVendors(new AsyncCallback<ArrayList<Vendor>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to load Vendor");
			}

			public void onSuccess(ArrayList<Vendor> result) {
				initializeVendorTable(result);
				
			}
		});
	}
	public void reGenerateCellTableBySearch(String name){
		dataProvider.getList().clear();
		inventoryServiceAsync.getVendorsByName(name, new MyAssyncCallback<ArrayList<Vendor>>(){
			@Override
			public void onSuccess(ArrayList<Vendor> result) {
				for(Vendor de: result)
					dataProvider.getList().add(de);
				searchPanel.cellTable.redraw();
			}
			
		});
	}
	protected void initializeVendorTable(ArrayList<Vendor> result) {
		 TextColumn<Vendor> companyName = new TextColumn<Vendor>() {
		      @Override
		      public String getValue(Vendor vendor) {
		        return vendor.getName();
		      }
		  };
		  searchPanel.cellTable.addColumn(companyName, "Company Name");
		  
	    TextColumn<Vendor> phoneColumn = new TextColumn<Vendor>() {
		      @Override
		      public String getValue(Vendor vendor) {
		        return vendor.getPhone();
		      }
		 };
		 searchPanel.cellTable.addColumn(phoneColumn, "Phone");
		 
		 TextColumn<Vendor> addressColumn = new TextColumn<Vendor>() {
		      @Override
		      public String getValue(Vendor vendor) {
		        return vendor.getAddress();
		      }
		 };
		 searchPanel.cellTable.addColumn(addressColumn, "Address");
		 
		  searchPanel.cellTable.setHeight("12px");

		    dataProvider = new ListDataProvider<Vendor>();
		    // Connect the table to the data provider.
		    dataProvider.addDataDisplay(searchPanel.cellTable);
		    

		    // Add the data to the data provider, which automatically pushes it to the
		    // widget.
		    List<Vendor> list = dataProvider.getList();
		    for (Vendor vendor : result) {
		      list.add(vendor);
		    }
		    
			//cell selection
			 searchPanel.selectionModel = new SingleSelectionModel<Vendor>();
			 searchPanel.cellTable.setSelectionModel(searchPanel.selectionModel);
			
			//for pagination
			 searchPanel.pager.setDisplay(searchPanel.cellTable);
			//2 rows per page
			 searchPanel.pager.setPageSize(2);
		
	}

}
