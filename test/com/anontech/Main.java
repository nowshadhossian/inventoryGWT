package com.anontech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.anontech.dao.InventoryDao;

public class Main {
	public static void main(String []ar){
		new Main().de();
	}
	private void de(){
		ApplicationContext factory = new ClassPathXmlApplicationContext("testapplicationContext.xml");
		 InventoryDao inventoryDao = (InventoryDao) factory.getBean("inventoryDao");
		 System.out.println(inventoryDao.getDepartments().get(0).getName());
	}
}
