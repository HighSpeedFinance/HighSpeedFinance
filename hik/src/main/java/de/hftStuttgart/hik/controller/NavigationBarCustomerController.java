package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

public class NavigationBarCustomerController {
	
	private Main main;

	@FXML
	private void showCustomerOverview(){
		main.showCustomerAndSupplierOverview();
	}
	
	@FXML
	private void showNewCustomers(){
		main.showNewCustomersAndSuppliers(0);
	}
	
	@FXML
	private void showCustomerOrderOverview(){
		main.showCustomerAndSupplierOrderOverview(0);
	}
	
	@FXML
	private void showNewCustomerOrders(){
		main.showNewOrders(0);
	}
	
	@FXML
	private void showOpenCustomerOrders(){
		main.showOpenCustomerAndSupplierOrders(0);
	}
	
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}
}
