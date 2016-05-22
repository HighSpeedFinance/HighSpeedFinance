package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

public class NavigationBarSupplierController {
	
	private Main main;

	@FXML
	public void showSupplierOverview(){
		main.showSupplierOverview();
	}
	
	@FXML
	public void showNewSuppliers(){
		main.showNewCustomersAndSuppliers(1);
	}
	
	@FXML
	public void showNewSupplierOrders(){
		main.showNewOrders(1);
	}
	
	@FXML
	public void showAllSupplierOrders(){
		main.showCustomerAndSupplierOrderOverview(1);
	}
	
	@FXML
	public void showOpenSupplierOrders(){
		main.showOpenCustomerAndSupplierOrders(1);
	}
	public void setMainApp(Main main){
		this.main = main;
	}
}
