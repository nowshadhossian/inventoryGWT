package com.anontech.client.find;

import java.util.ArrayList;
import java.util.List;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.db.Department;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class UserDetailsCellTable {

	private SearchPanel searchPanel;
	private InventoryServiceAsync inventoryServiceAsync;
	
	ListDataProvider<UserDetails> dataProvider;
	
	public UserDetailsCellTable(SearchPanel searchPanel, InventoryServiceAsync inventoryServiceAsync) {
		this.searchPanel = searchPanel;
		this.inventoryServiceAsync = inventoryServiceAsync;
		
		PopularUI.clearCellTableColumn(searchPanel.cellTable); //clear all column from cellTable
		inventoryServiceAsync.getUserDetails(new AsyncCallback<ArrayList<UserDetails>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to load User");
			}

			public void onSuccess(ArrayList<UserDetails> result) {
				initializeUserDetailsTable(result);
				
			}
		});
	}
	
	public void reGenerateCellTableBySearch(String name){
		dataProvider.getList().clear();
		inventoryServiceAsync.getUserDetailsByName(name, new MyAssyncCallback<ArrayList<UserDetails>>(){
			@Override
			public void onSuccess(ArrayList<UserDetails> result) {
				for(UserDetails de: result)
					dataProvider.getList().add(de);
				searchPanel.cellTable.redraw();
			}
			
		});
	}
	
	protected void initializeUserDetailsTable(ArrayList<UserDetails> result) {
		 TextColumn<UserDetails> companyName = new TextColumn<UserDetails>() {
		      @Override
		      public String getValue(UserDetails user) {
		        return user.getName();
		      }
		  };
		  searchPanel.cellTable.addColumn(companyName, "Name");
		  
	    TextColumn<UserDetails> phoneColumn = new TextColumn<UserDetails>() {
		      @Override
		      public String getValue(UserDetails userDetails) {
		        return userDetails.getPhone();
		      }
		 };
		 searchPanel.cellTable.addColumn(phoneColumn, "Phone");
		 
		 TextColumn<UserDetails> addressColumn = new TextColumn<UserDetails>() {
		      @Override
		      public String getValue(UserDetails user) {
		        return user.getAddress();
		      }
		 };
		 searchPanel.cellTable.addColumn(addressColumn, "Address");
		 
		  searchPanel.cellTable.setHeight("12px");

		    dataProvider = new ListDataProvider<UserDetails>();
		    // Connect the table to the data provider.
		    dataProvider.addDataDisplay(searchPanel.cellTable);
		    

		    // Add the data to the data provider, which automatically pushes it to the
		    // widget.
		   List<UserDetails> list = dataProvider.getList();
		    for (UserDetails user : result) {
		      list.add(user);
		    }
		    
			//cell selection
			 searchPanel.selectionModel = new SingleSelectionModel<UserDetails>();
			 searchPanel.cellTable.setSelectionModel(searchPanel.selectionModel);
			
			//for pagination
			 searchPanel.pager.setDisplay(searchPanel.cellTable);
			//2 rows per page
			 searchPanel.pager.setPageSize(20);
		
	}

}
