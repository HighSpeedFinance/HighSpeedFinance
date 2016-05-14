package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.Order;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerOrderController {

	@FXML
	private TableView<Order> customerOrderTable;
	@FXML
	private TableColumn<Order, String> orderNumber;
	
	private Stage dialogStage;
	private Customer customer;
	private Main main;
	
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<Order, String>("orderNumber"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		customerOrderTable.setItems((ObservableList<Order>) customer.getOrder());
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
