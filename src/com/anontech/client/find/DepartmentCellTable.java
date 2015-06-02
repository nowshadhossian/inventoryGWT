package com.anontech.client.find;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.client.factory.PopularUI;
import com.anontech.db.Department;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class DepartmentCellTable {

	private SearchPanel searchPanel;
	private InventoryServiceAsync inventoryServiceAsync;
	
	ListDataProvider<Department> dataProvider;

	public DepartmentCellTable(SearchPanel searchPanel,	InventoryServiceAsync inventoryServiceAsync) {
		this.searchPanel = searchPanel;
		this.inventoryServiceAsync = inventoryServiceAsync;
		
		PopularUI.clearCellTableColumn(searchPanel.cellTable); //clear all column from cellTable
		inventoryServiceAsync.getDepartments(new AsyncCallback<ArrayList<Department>>() {
			
			@Override
			public void onSuccess(ArrayList<Department> departments) {
				initializeDepartmentTable(departments);
			}
			

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("failed to load department");
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void reGenerateCellTableBySearch(String name){
		dataProvider.getList().clear();

		inventoryServiceAsync.getDepartmentByName(name, new MyAssyncCallback<ArrayList<Department>>(){
			@Override
			public void onSuccess(ArrayList<Department> result) {
				//initializeDepartmentTable(result);
				for(Department de: result)
					dataProvider.getList().add(de);
				searchPanel.cellTable.redraw();
			}
			
		});
	}

	protected void initializeDepartmentTable(ArrayList<Department> result) {
		 TextColumn<Department> nameColumn = new TextColumn<Department>() {
		      @Override
		      public String getValue(Department department) {
		        return department.getName();
		      }
		  };
		  searchPanel.cellTable.addColumn(nameColumn, "Name");
		  searchPanel.cellTable.setColumnWidth(nameColumn, 35.0, Unit.PCT);
		  searchPanel.cellTable.setHeight("12px");

		    dataProvider = new ListDataProvider<Department>();
		    // Connect the table to the data provider.
		    dataProvider.addDataDisplay(searchPanel.cellTable);
		    

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
			 searchPanel.cellTable.addColumn(codeColumn, "Code");
			columnSorting(nameColumn, codeColumn, list);
			// colmnSort(nameColumn, list, Department.class, "getName");
			// colmnSort(codeColumn, list, Department.class, "getCode");
			
			
			//cell selection
			 searchPanel.selectionModel = new SingleSelectionModel<Department>();
			 searchPanel.cellTable.setSelectionModel(searchPanel.selectionModel);
			
			//for pagination
			 searchPanel.pager.setDisplay(searchPanel.cellTable);
			//2 rows per page
			 searchPanel.pager.setPageSize(6);
		
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
	    searchPanel.cellTable.getColumnSortList().push(nameColumn);

	   
		
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
		 
		 searchPanel.cellTable.addColumnSortHandler(columnSortHandler);
		
	}
	/*
	private <T> void colmnSort(TextColumn<T> textColumn, List<T> list, final Class<?> classObj, final String methodName){
		textColumn.setSortable(true);
		Class<?> cc = classObj;
		System.out.println("hi");
		 ListHandler<T> columnSortHandler = new ListHandler<T>(list);
		    columnSortHandler.setComparator(textColumn,
		        new Comparator<T>() {
				@Override
				public int compare(T t1, T t2) {
					try {
						Method method1 = classObj.getClass().getMethod(methodName);
						
						Object o1 = method1.invoke(t1);
						Object o2 = method1.invoke(t2);
						
						if(o1 instanceof String){
							String s1 = (String) o1;
							String s2 = (String) o1;
							if(s1.equals(s2)){
								return 0;
							}
							if (s1 != null) {
					              return (s2 != null) ? s1.compareTo(s2) : 1;
					        }
						}else if(o1 instanceof Long){
							Long s1 = (Long) o1;
							Long s2 = (Long) o2;
							if(s1 == s2){
								return 0;
							}
							if (s1 != null) {
					              return (s2 != null) ? s1.compareTo(s2) : 1;
					        }
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return -1;
				}
		    	 
		        });
		 	
		   
		    // We know that the data is sorted alphabetically by default.
		    searchPanel.cellTable.getColumnSortList().push(textColumn);
	}*/
	

	
	
	
}
