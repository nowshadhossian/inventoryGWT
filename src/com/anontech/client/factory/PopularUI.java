package com.anontech.client.factory;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.anontech.client.LoginUI;
import com.anontech.db.Department;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class PopularUI{
	
	//show a widget with in a dialog box
	public void showDialogBox(Widget widgetToShow, String dialogTitle){
		final DialogBox dialogBox = new DialogBox();
		VerticalPanel verticalPanel = new VerticalPanel();
		Button closeButton = new Button();
		closeButton.setStyleName("dialog-closeButton");
		verticalPanel.add(closeButton);
		verticalPanel.add(widgetToShow);
		dialogBox.add(verticalPanel);
		dialogBox.setText(dialogTitle);
		dialogBox.center();
		dialogBox.show();
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide(true);
			}
		});
	}
	
	/*
	 * @param Integer previousTabIndexToSelectOnceClosed: can be null if you don't want to go back to previous tab
	 */
	public void addTabWithCloseButton(String tabTitle, final DecoratedTabPanel tabPanel, Widget widgetInTab, final Integer previousTabIndexToSelectOnceClosed){
		HorizontalPanel panel = new HorizontalPanel();
        Label text = new Label();
        text.setText(tabTitle);
        Label close = new Label();
        close.setText("X");
        panel.add(text);
        panel.add(close);

        tabPanel.add(widgetInTab, panel);
        final int tabIndex = tabPanel.getWidgetIndex(widgetInTab);
        
        tabPanel.selectTab(tabIndex);
        
        close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				tabPanel.remove(tabIndex);
				if(previousTabIndexToSelectOnceClosed != null)
					tabPanel.selectTab(previousTabIndexToSelectOnceClosed);
			}
        	
        });
	}
	//clear all columns from cellTable. Call this before loading values in celltable
	public static <T> void clearCellTableColumn(CellTable<T> cellTable) {
		for(int i=cellTable.getColumnCount()-1; i>=0; i--){
			cellTable.removeColumn(i);
		}
	}
	//clear all rows from cellTable. 
	public static <T> void clearCellTableColumn(ListDataProvider<T> listDataProvider) {
		listDataProvider.getList().clear();
	}
	
	//use this for showing validation error message beside a textbox
	public static void showTextboxErrorMessagePopup(TextBox textboxFor, String errorMessage) {
		Label label = new Label(errorMessage + " ");
		label.setStyleName("errorMessagePopupLabel");
		Image leftArrowImage = new Image("myimage/errorMessageLeftArrow.png");
		final PopupPanel pop = new PopupPanel();
		pop.setPopupPosition(textboxFor.getAbsoluteLeft() + textboxFor.getOffsetWidth(), textboxFor.getAbsoluteTop());
		pop.setStyleName("popUpBackgroudDisable");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(leftArrowImage);
		horizontalPanel.add(label);
		pop.add(horizontalPanel);
		pop.show();
		
		label.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				pop.hide();
			}
		});
	}
	
	/*
	 * Call this method if session is deleted. This will show the login page
	 */
	public static void LoginWidgetAfterLogout(){
		RootPanel.get().clear();
		LoginUI loginUI = new LoginUI();
		RootPanel.get().add(loginUI, (Window.getClientWidth()/2)-100, (Window.getClientHeight()/2)-200);
	}
	
	/*
	 * Use this to apply validation
	 */
	public static Validator getValidator(){
		ValidatorFactory factory = Validation.byDefaultProvider().configure().buildValidatorFactory();
		Validator validator = factory.getValidator();
		return validator;
	}
}
