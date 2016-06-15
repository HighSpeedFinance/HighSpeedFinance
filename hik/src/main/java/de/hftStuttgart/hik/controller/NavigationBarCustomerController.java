package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

/**
 * The Class NavigationBarCustomerController manages the views from
 * NavigationBarCustomer and is responsible for handling user input and
 * responses. It shows the overview, new Customers, CustomerOrderOverview , new
 * CustomerOrders and open CustomerOrders
 */
public class NavigationBarCustomerController {

	private Main main;

	/**
	 * Shows customer overview.
	 */
	@FXML
	private void showCustomerOverview() {
		main.showCustomerAndSupplierOverview();
	}

	/**
	 * Shows new customers.
	 */
	@FXML
	private void showNewCustomers() {
		main.showNewCustomersAndSuppliers(0);
	}

	/**
	 * Shows CustomerOrderOverview.
	 */
	@FXML
	private void showCustomerOrderOverview() {
		main.showCustomerAndSupplierOrderOverview(0);
	}

	/**
	 * Shows new CustomerOrders.
	 */
	@FXML
	private void showNewCustomerOrders() {
		main.showNewOrders(0);
	}

	/**
	 * Shows open CustomerOrders.
	 */
	@FXML
	private void showOpenCustomerOrders() {
		main.showOpenCustomerAndSupplierOrders(0);
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp
	 *            the new main app
	 */
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}
}
