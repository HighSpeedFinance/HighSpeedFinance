package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

public class NavigationBarCustomerController {
	
	private Main main;

	@FXML
	public void showCustomerOverview(){
		main.showCustomerAndSupplierOverview();
	}
	
	@FXML
	public void showNewCustomers(){
		main.showNewCustomersAndSuppliers();
	}
	
	@FXML
	public void showCustomerOrderOverview(){
		main.showCustomerAndSupplierOrderOverview();
	}
	
	@FXML
	public void showNewCustomerOrders(){
		main.showNewOrders();
	}
	
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}
}
