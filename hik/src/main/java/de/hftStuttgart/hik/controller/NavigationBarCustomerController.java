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
		main.showNewCustomersAndSuppliers(0);
	}
	
	@FXML
	public void showCustomerOrderOverview(){
		main.showCustomerAndSupplierOrderOverview(0);
	}
	
	@FXML
	public void showNewCustomerOrders(){
		main.showNewOrders(0);
	}
	
	@FXML
	public void showOpenCustomerOrders(){
		main.showOpenCustomerAndSupplierOrders(0);
	}
	
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}
}
