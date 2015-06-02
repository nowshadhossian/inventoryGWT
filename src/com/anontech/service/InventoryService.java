package com.anontech.service;

import java.util.ArrayList;
import java.util.List;

import com.anontech.db.Department;
import com.anontech.db.Equipment;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.Purchase;
import com.anontech.db.SingleItem;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.utils.LoginFailedException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("springGwtServices/inventoryService")
public interface InventoryService extends RemoteService {
	
	public abstract ArrayList<Department> getDepartments();
	
	public abstract void saveOrUpdate(Department object) throws LoginFailedException;
	public abstract void saveOrUpdate(Vendor object) throws LoginFailedException;
	public abstract void saveOrUpdate(UserDetails object) throws LoginFailedException;
	public abstract void saveOrUpdate(Purchase object)throws LoginFailedException;
	public abstract void saveOrUpdate(Invoice object) throws LoginFailedException;
	public abstract void saveLocalPurchase(Purchase purchaseObj, Invoice invoiceObj, List<Items> itemList) throws LoginFailedException;
	public abstract void deleteItem(Items object) throws LoginFailedException;
	
	public abstract ArrayList<Vendor> getVendors();

	public abstract ArrayList<UserDetails> getUserDetails();

	public abstract ArrayList<Vendor> getVendorsByName(String name);

	public abstract ArrayList<Department> getDepartmentByName(String name);

	public abstract ArrayList<UserDetails> getUserDetailsByName(String name);

	public abstract ArrayList<Equipment> getEquipments();

	public abstract ArrayList<Invoice> getInvoices();

	public abstract ArrayList<Items> getItemsByInvoice(long invoiceId);


//	public abstract Object load(Long id, Class clz);


}
