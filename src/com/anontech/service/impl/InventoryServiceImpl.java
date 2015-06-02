package com.anontech.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anontech.dao.InventoryDao;
import com.anontech.db.BulkItem;
import com.anontech.db.Department;
import com.anontech.db.Equipment;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.Purchase;
import com.anontech.db.SingleItem;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.service.InventoryService;
import com.anontech.utils.LoginFailedException;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired InventoryDao inventoryDao;
	
	Logger logger = Logger.getLogger("NameOfYourLogger");//we can use this for testing
	public ArrayList<Department> getDepartments() {
		return inventoryDao.getDepartments();
	}

	public void saveOrUpdate(Department object) {
		inventoryDao.saveOrUpdate(object);
		
	}

	@Override
	public void saveOrUpdate(Vendor vendor) {
		if(vendor.getDepartment().getId() == null || vendor.getDepartment().getId() == 0)
			inventoryDao.saveOrUpdate(vendor.getDepartment());
		inventoryDao.saveOrUpdate(vendor);
	}

	@Override
	public ArrayList<Vendor> getVendors() {
		return inventoryDao.getVendors();
	}

	@Override
	public void saveOrUpdate(UserDetails object) throws LoginFailedException {
		inventoryDao.saveOrUpdate(object);
	}

	@Override
	public ArrayList<UserDetails> getUserDetails() {
		return inventoryDao.getUserDetails();
	}

	@Override
	public ArrayList<Vendor> getVendorsByName(String name) {
		return inventoryDao.getVendorsByName(name);
	}

	@Override
	public ArrayList<Department> getDepartmentByName(String name) {
		return inventoryDao.getDepartmentsByName(name);
	}

	@Override
	public ArrayList<UserDetails> getUserDetailsByName(String name) {
		return inventoryDao.getUserDetailsByName(name);
	}

	@Override
	public ArrayList<Equipment> getEquipments() {
		return inventoryDao.getEquipments();
	}

	@Override
	public void saveOrUpdate(Purchase object) {
		inventoryDao.saveOrUpdate(object);
	}

	@Override
	public void saveOrUpdate(Invoice object) throws LoginFailedException {
		inventoryDao.saveOrUpdate(object);
	}

	@Override
	public void saveLocalPurchase(Purchase purchaseObj, Invoice invoiceObj,	List<Items> itemList) {
		purchaseObj.setIsForeignPurchase(false);
		inventoryDao.saveOrUpdate(purchaseObj);
		invoiceObj.setPurchase(purchaseObj);
		inventoryDao.saveOrUpdate(invoiceObj);
		for(Items item : itemList){
			item.setInvoice(invoiceObj);
			if(item instanceof SingleItem){
				SingleItem singleItem = (SingleItem) item;
				inventoryDao.saveOrUpdate(singleItem);
			}else if(item instanceof BulkItem){
				BulkItem bulkItem =  (BulkItem) item;
				inventoryDao.saveOrUpdate(bulkItem);
			}
		}
	}

	@Override
	public ArrayList<Invoice> getInvoices() {
		return inventoryDao.getInvoices();
	}

	@Override
	public ArrayList<Items> getItemsByInvoice(long invoiceId) {
		return inventoryDao.getItemsByInvoice(invoiceId);
	}

	@Override
	public void deleteItem(Items object) throws LoginFailedException {
		inventoryDao.delete(object);
	}


}
