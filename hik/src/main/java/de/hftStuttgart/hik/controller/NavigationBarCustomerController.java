package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationBarCustomerController.
 */
public class NavigationBarCustomerController {
	
	/** The main. */
	private Main main;

	/**
	 * Show customer overview.
	 */
	@FXML
	private void showCustomerOverview(){
		main.showCustomerAndSupplierOverview();
	}
	
	/**
	 * Show new customers.
	 */
	@FXML
	private void showNewCustomers(){
		main.showNewCustomersAndSuppliers(0);
	}
	
	/**
	 * Show customer order overview.
	 */
	@FXML
	private void showCustomerOrderOverview(){
		main.showCustomerAndSupplierOrderOverview(0);
	}
	
	/**
	 * Show new customer orders.
	 */
	@FXML
	private void showNewCustomerOrders(){
		main.showNewOrders(0);
	}
	
	/**
	 * Show open customer orders.
	 */
	@FXML
	private void showOpenCustomerOrders(){
		main.showOpenCustomerAndSupplierOrders(0);
	}
	
	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}
}
