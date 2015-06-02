package com.anontech.dao;

import java.util.ArrayList;

import com.anontech.client.items.EquipementPanel;
import com.anontech.db.Department;
import com.anontech.db.Equipment;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.SingleItem;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.utils.LoginFailedException;

public interface InventoryDao {

    public abstract User login(String login, String password);
    public abstract ArrayList<Department> getDepartments();
    public abstract ArrayList<Department> getDepartmentsByName(String name);
    
	public abstract Object load(Long id, Class clz);
    public abstract <T> void saveOrUpdate(T object) throws LoginFailedException;
    public abstract <T> void delete(T object) throws LoginFailedException;
    
    public abstract ArrayList<Vendor> getVendors();
    public abstract ArrayList<Vendor> getVendorsByName(String name);
    public abstract ArrayList<UserDetails> getUserDetails();
    public abstract Equipment getEquipmentByMakeAndModel(String make, String model);
	public abstract ArrayList<UserDetails> getUserDetailsByName(String name);
	public abstract ArrayList<Equipment> getEquipments();
	public abstract ArrayList<Invoice> getInvoices();
	public abstract ArrayList<Items> getItemsByInvoice(long invoiceId);
}