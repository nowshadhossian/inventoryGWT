package com.anontech.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anontech.db.Department;
import com.anontech.db.Equipment;
import com.anontech.db.Invoice;
import com.anontech.db.Items;
import com.anontech.db.Persistent;
import com.anontech.db.SingleItem;
import com.anontech.db.User;
import com.anontech.db.UserDetails;
import com.anontech.db.Vendor;
import com.anontech.service.LoginService;
import com.anontech.utils.LoginFailedException;

@Service("inventoryDao")
@Transactional
public class InventoryDaoImpl extends HibernateDaoSupport implements InventoryDao {
	
	@Autowired LoginService loginService;
	Logger logger = Logger.getLogger("NameOfYourLogger");
    public User login(String login, String password) {
        List<User> list = getHibernateTemplate().find("from User user where  "
                + "user.login = ? and user.password = ?", new Object[]{login, password});
        if (list == null || list.size() == 0) {
            return null;
        }
        return (User) list.get(0);
    }
    
    public ArrayList<Department> getDepartments(){
    	return (ArrayList<Department>) getHibernateTemplate().find("from Department");
    }
    
    public Equipment getEquipmentByMakeAndModel(String make, String model){
    	ArrayList<Equipment> equipments = (ArrayList<Equipment>) getHibernateTemplate().find("from Equipment E where upper(E.make) like ? and upper(E.model) like ?", new Object[]{make.toUpperCase(), model.toUpperCase()});
    	if(equipments.size() >0) return equipments.get(0);
    	return null;
    }
    
	public <T> void saveOrUpdate(T object) throws LoginFailedException {
		User loggedUser = loginService.getLoggedUserFromSession();
		if(loggedUser == null) {
			//Window.alert("Unable to find Login Info"); //TODO: not working
			throw new LoginFailedException();
		}
		Date today = new Date();
		Persistent persistent = (Persistent)object;
		if(persistent.getCreated() == null) {
			persistent.setCreated(today);
			persistent.setCreatedBy(loggedUser.getId());
		} else {
			persistent.setUpdated(today);
			persistent.setUpdatedBy(loggedUser.getId());
		}
		getSession(false).saveOrUpdate(object);
	}
	
	public ArrayList<Vendor> getVendors() {
		return (ArrayList<Vendor>) getHibernateTemplate().find("from Vendor");
	}
	
	public ArrayList<Vendor> getVendorsByName(String name) {
		return (ArrayList<Vendor>) getHibernateTemplate().find("from Vendor where name like ?", name);
	}

	@Override
	public Object load(Long id, Class clz) {
		return getHibernateTemplate().get(clz, id);
	}

	@Override
	public ArrayList<UserDetails> getUserDetails() {
		return (ArrayList<UserDetails>) getHibernateTemplate().find("from UserDetails");
	}

	@Override
	public ArrayList<Department> getDepartmentsByName(String name) {
		return (ArrayList<Department>) getHibernateTemplate().find("from Department where name like ?", name);
	}

	@Override
	public ArrayList<UserDetails> getUserDetailsByName(String name) {
		return (ArrayList<UserDetails>) getHibernateTemplate().find("from UserDetails where name like ?", name);
	}

	@Override
	public ArrayList<Equipment> getEquipments() {
		return (ArrayList<Equipment>) getHibernateTemplate().find("from Equipment");
	}

	@Override
	public ArrayList<Invoice> getInvoices() {
		return (ArrayList<Invoice>) getHibernateTemplate().find("from Invoice");
	}

	@Override
	public ArrayList<Items> getItemsByInvoice(long p) {
		return (ArrayList<Items>) getHibernateTemplate().find("from Items where invoice.id = ?", p);
	}

	@Override
	public <T> void delete(T object) throws LoginFailedException {
		getHibernateTemplate().delete(object);
	}

	
}