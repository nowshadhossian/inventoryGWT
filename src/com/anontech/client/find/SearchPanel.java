package com.anontech.client.find;

import java.util.ArrayList;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.client.items.AddDepartment;
import com.anontech.client.items.AddVendor;
import com.anontech.client.items.VendorDeptPanel;
import com.anontech.client.purchase.LocalInvoice;
import com.anontech.client.users.AddUser;
import com.anontech.db.Department;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.SingleItem;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.anontech.service.LoginService;
import com.anontech.service.LoginServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;

public class SearchPanel<T> extends Composite {

	private static SearchPanelUiBinder uiBinder = GWT
			.create(SearchPanelUiBinder.class);
	@UiField(provided=true)CellTable<T> cellTable = new CellTable<T>();
	@UiField Button btnFind;
	@UiField SimplePager pager;
	@UiField Button btnEdit;
	@UiField Button test;
	@UiField TextBox tbxSearch;
	@UiField HTMLPanel htmlPanel;
	
	String searchTable;
	SingleSelectionModel<T> selectionModel;
	
	VendorCellTable vendorCellTable;
	DepartmentCellTable departmentCellTable;
	UserDetailsCellTable userDetailsCellTable;
	PurchaseCellTable purchaseCellTable;
	
	private final InventoryServiceAsync inventoryServiceAsync = GWT
	.create(InventoryService.class);

	private final LoginServiceAsync loginServiceAsync = GWT
			.create(LoginService.class);

	interface SearchPanelUiBinder extends UiBinder<Widget, SearchPanel> {
	}

	public SearchPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public SearchPanel(String searchTable) {
		setSearch(searchTable);
		initWidget(uiBinder.createAndBindUi(this));
	}
	public void setSearch(String searchTable){
		this.searchTable = searchTable;
		addCellTable();
	}


	@UiHandler("btnFind")
	void onBtnFindClick(ClickEvent event) {
		if(searchTable.equals(SearchPanelSearcher.VENDOR)){
			vendorCellTable.reGenerateCellTableBySearch(tbxSearch.getText() + "%");
		}else if(searchTable.equals(SearchPanelSearcher.DEPARTMENT)){
			departmentCellTable.reGenerateCellTableBySearch(tbxSearch.getText() + "%");
		}else if(searchTable.equals(SearchPanelSearcher.USER)){
			userDetailsCellTable.reGenerateCellTableBySearch(tbxSearch.getText() + "%");//% is for like 'name%'
		}else if(searchTable.equals(SearchPanelSearcher.PURCHASE)){
		}else{
			Window.alert("Programming error: cellTable not yet defined/found");
		}
	}

	public void addCellTable() {
		if(searchTable.equals(SearchPanelSearcher.VENDOR)){
			vendorCellTable = new VendorCellTable(SearchPanel.this, inventoryServiceAsync);
		}else if(searchTable.equals(SearchPanelSearcher.DEPARTMENT)){
			departmentCellTable = new DepartmentCellTable(SearchPanel.this, inventoryServiceAsync);
		}else if(searchTable.equals(SearchPanelSearcher.USER)){
			userDetailsCellTable = new UserDetailsCellTable(SearchPanel.this, inventoryServiceAsync);
		}else if(searchTable.equals(SearchPanelSearcher.PURCHASE)){
			purchaseCellTable = new PurchaseCellTable(SearchPanel.this, inventoryServiceAsync);
		}else{
			Window.alert("Programming error: cellTable not yet defined/found");
		}
	}
	
	@UiHandler("test")
	void onTestClick(ClickEvent event) {
		/*PopularUI.showTextboxErrorMessagePopup(tbxSearch, "Error Very bad how the winds are laughing hahva");
		
		loginServiceAsync.getLoggedUserFromSession(new AsyncCallback<User>() {
			@Override
			public void onSuccess(User user) {
				if(user != null)
					Window.alert(user.getName());
				else{
					PopularUI.LoginWidgetAfterLogout();
				}
				
			}
			@Override
			public void onFailure(Throwable caught) {
				
				
			}
		});*/
		
		inventoryServiceAsync.getItemsByInvoice(4l, new MyAssyncCallback<ArrayList<Items>>(){
			@Override
			public void onSuccess(ArrayList<Items> result) {
				System.out.println("---------------------" + result.size());
			//	itemEditor.reGenerateCellTable(result);
			}
			
		});
	}

	@UiHandler("btnEdit")
	void onBtnEditClick(ClickEvent event) {
		final VendorDeptPanel vendorDeptPanel = VendorDeptPanel.getInstance();
		AbsolutePanel absPanel = new AbsolutePanel();
		String tabPanelName = "";
		if(searchTable.equals(SearchPanelSearcher.DEPARTMENT)){
			AddDepartment addDept = new AddDepartment();
			absPanel.add(addDept);
			addDept.initializeTbxValues((Department) selectionModel.getSelectedObject());
			tabPanelName = "Edit Department";
		}else if(searchTable.equals(SearchPanelSearcher.VENDOR)){
			AddVendor addVendor = new AddVendor();
			absPanel.add(addVendor);
			addVendor.initializeTbxValues((Vendor) selectionModel.getSelectedObject());
			tabPanelName = "Edit Vendor";
		}else if(searchTable.equals(SearchPanelSearcher.USER)){
			AddUser addUser = new AddUser((UserDetails) selectionModel.getSelectedObject());
			absPanel.add(addUser);
			PopularUI popularUI = new PopularUI();
			popularUI.showDialogBox(addUser, "Edit User");
		}else if(searchTable.equals(SearchPanelSearcher.PURCHASE)){
			LocalInvoice localInvoice = new LocalInvoice((Invoice) selectionModel.getSelectedObject());
			absPanel.add(localInvoice);
			PopularUI popularUI = new PopularUI();
			popularUI.showDialogBox(localInvoice, "Edit Local Invoice");
		}
		

		int previousTabIndex = vendorDeptPanel.tabPanel.getTabBar().getSelectedTab();
		PopularUI popularUI = new PopularUI();
		popularUI.addTabWithCloseButton(tabPanelName, vendorDeptPanel.tabPanel, absPanel, previousTabIndex);

	}
	
	
	/*

	protected void initializeDepartmentTable(ArrayList<Department> result) {
		 TextColumn<Department> nameColumn = new TextColumn<Department>() {
		      @Override
		      public String getValue(Department department) {
		        return department.getName();
		      }
		  };
		    cellTable.addColumn(nameColumn, "Name");
		    cellTable.setColumnWidth(nameColumn, 35.0, Unit.PCT);
		    cellTable.setHeight("12px");

		    ListDataProvider<Department> dataProvider = new ListDataProvider<Department>();

		    // Connect the table to the data provider.
		    dataProvider.addDataDisplay(cellTable);

		    // Add the data to the data provider, which automatically pushes it to the
		    // widget.
		    List<Department> list = dataProvider.getList();
		    for (Department department : result) {
		      list.add(department);
		    }

		    
		    // Create address column.
		    TextColumn<Department> codeColumn = new TextColumn<Department>() {
			      @Override
			      public String getValue(Department department) {
			        return department.getCode();
			      }
			 };
			cellTable.addColumn(codeColumn, "Code");
			
		    
			columnSorting(nameColumn, codeColumn, list);
			
			//cell selection
			selectionModel = new SingleSelectionModel<Department>();
			cellTable.setSelectionModel(selectionModel);
			
			//for pagination
			pager.setDisplay(cellTable);
			//2 rows per page
			pager.setPageSize(2);
		
	}

	
	private void columnSorting(TextColumn nameColumn, TextColumn codeColumn, List<Department> list) {
		// Add a ColumnSortEvent.ListHandler to connect sorting to the
	    // java.util.List.
		nameColumn.setSortable(true);
		codeColumn.setSortable(true);
		
	    ListHandler<Department> columnSortHandler = new ListHandler<Department>(
	    		list);
	    columnSortHandler.setComparator(nameColumn,
	        new Comparator<Department>() {
	          public int compare(Department o1, Department o2) {
	            if (o1.getName() == o2.getName()) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1.getName() != null) {
	              return (o2.getName() != null) ? o1.getName().compareTo(o2.getName()) : 1;
	            }
	            return -1;
	          }
	    	 
	        });
	   

	    // We know that the data is sorted alphabetically by default.
	    cellTable.getColumnSortList().push(nameColumn);

	   
		
		columnSortHandler.setComparator(codeColumn,
			        new Comparator<Department>() {
			          public int compare(Department o1, Department o2) {
			            if (o1.getCode() == o2.getCode()) {
			              return 0;
			            }

			            // Compare the name columns.
			            if (o1.getCode() != null) {
			              return (o2.getCode() != null) ? o1.getCode().compareTo(o2.getCode()) : 1;
			            }
			            return -1;
			          }
			    	 
	   });
		 
		 cellTable.addColumnSortHandler(columnSortHandler);
		
	}*/
	
}
