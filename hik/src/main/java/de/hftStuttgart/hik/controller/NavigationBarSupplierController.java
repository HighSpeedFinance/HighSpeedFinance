package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.fxml.FXML;

public class NavigationBarSupplierController {

	private Main main;

	@FXML
	private void showSupplierOverview() {
		main.showSupplierOverview();
	}

	@FXML
	private void showNewSuppliers() {
		main.showNewCustomersAndSuppliers(1);
	}

	@FXML
	private void showNewSupplierOrders() {
		main.showNewOrders(1);
	}

	@FXML
	private void showAllSupplierOrders() {
		main.showCustomerAndSupplierOrderOverview(1);
	}

	@FXML
	private void showOpenSupplierOrders() {
		main.showOpenCustomerAndSupplierOrders(1);
	}

	public void setMainApp(Main main) {
		this.main = main;
	}
}
