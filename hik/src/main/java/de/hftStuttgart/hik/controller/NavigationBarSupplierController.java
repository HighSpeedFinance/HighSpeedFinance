package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

// TODO: Auto-generated Javadoc
/**
 * The Class NavigationBarSupplierController.
 */
public class NavigationBarSupplierController {

	/** The main. */
	private Main main;

	/**
	 * Show supplier overview.
	 */
	@FXML
	private void showSupplierOverview() {
		main.showSupplierOverview();
	}

	/**
	 * Show new suppliers.
	 */
	@FXML
	private void showNewSuppliers() {
		main.showNewCustomersAndSuppliers(1);
	}

	/**
	 * Show new supplier orders.
	 */
	@FXML
	private void showNewSupplierOrders() {
		main.showNewOrders(1);
	}

	/**
	 * Show all supplier orders.
	 */
	@FXML
	private void showAllSupplierOrders() {
		main.showCustomerAndSupplierOrderOverview(1);
	}

	/**
	 * Show open supplier orders.
	 */
	@FXML
	private void showOpenSupplierOrders() {
		main.showOpenCustomerAndSupplierOrders(1);
	}

	/**
	 * Sets the main app.
	 *
	 * @param main the new main app
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}
}
