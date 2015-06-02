package com.anontech.client.factory;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.anontech.service.InventoryService;
import com.anontech.service.InventoryServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

public class FormBinder<T, E extends Editor<T>> {
	 SimpleBeanEditorDriver<T, E> driver;
	 
	  E editor;
	  
	  //private VendorEditor editor;
	  
	  private final InventoryServiceAsync inventoryServiceAsync = GWT
		.create(InventoryService.class);
	 
	  public void edit(T p) {
	    driver.initialize(editor);
	    driver.edit(p);
	  }
	  // Called by some UI action
	  public boolean bind() {
		T edited = driver.flush();
	    final Set<ConstraintViolation<T>> violations = PopularUI.getValidator().validate(edited);
	    Iterable<ConstraintViolation<?>> iterable = new Iterable(){
	    	public Iterator<ConstraintViolation<T>> iterator() {
	    		return violations.iterator();
	    	}
	    };
	    driver.setConstraintViolations(iterable);
	    if (driver.hasErrors()) {
	    	
	    }else{
	    	//no error found
	    	return true;
	    }
	    //doSomethingWithEditedPerson(edited);
	    return false;
	  }

	public void initialize(E editor, SimpleBeanEditorDriver driver, T p) {
		this.editor = editor;
		this.driver = (SimpleBeanEditorDriver<T, E>) driver;
		edit(p);
	}
}
