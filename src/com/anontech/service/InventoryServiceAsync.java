package com.anontech.service;

import java.util.ArrayList;
import java.util.List;

import com.anontech.client.factory.MyAssyncCallback;
import com.anontech.db.Department;
import com.anontech.db.Equipment;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.Purchase;
import com.anontech.db.SingleItem;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InventoryServiceAsync {

	public void getDepartments(AsyncCallback<ArrayList<Department>> callback);

	public void saveOrUpdate(Department object, AsyncCallback<Void> callback);

	public void saveOrUpdate(Vendor object, AsyncCallback<Void> callback);

	public void getVendors(AsyncCallback<ArrayList<Vendor>> callback);
	
	public void getVendorsByName(String name, AsyncCallback<ArrayList<Vendor>> callback);
	
	public void getUserDetails(AsyncCallback<ArrayList<UserDetails>> callback);

	public void saveOrUpdate(UserDetails object, AsyncCallback<Void> callback);

	public void getDepartmentByName(String name, AsyncCallback<ArrayList<Department>> callback);

	public void getUserDetailsByName(String name, AsyncCallback<ArrayList<UserDetails>> callback);

	public void getEquipments(AsyncCallback<ArrayList<Equipment>> callback);

	public void saveOrUpdate(Purchase object, AsyncCallback<Void> callback);

	public void saveOrUpdate(Invoice invoiceObj, AsyncCallback<Void> callback);

	public void saveLocalPurchase(Purchase purchaseObj, Invoice invoiceObj,	List<Items> itemList, AsyncCallback<Void> callback);

	public void getInvoices(AsyncCallback<ArrayList<Invoice>> asyncCallback);

//	public <T> void load(Long id, Class clz, AsyncCallback<T> callback);

	public void getItemsByInvoice(long invoiceId, AsyncCallback<ArrayList<Items>> myAssyncCallback);

	public void deleteItem(Items object, AsyncCallback<Void> callback);

}
