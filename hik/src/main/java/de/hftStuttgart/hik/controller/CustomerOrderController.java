package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerOrderController {

	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	@FXML
	private TableColumn<CustomerOrder, String> orderDescription;
	@FXML
	private TableColumn<CustomerOrder, String> leer;
	@FXML
	private TableColumn<CustomerOrder, String> orderStatus;
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	@FXML
	private TableColumn<CustomerOrder, String> orderSinglePrice;
	@FXML
	private TableColumn<CustomerOrder, String> orderAmount;
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	@FXML
	private TableColumn<CustomerOrder, String> orderArt;
	
	private Customer customer;
	private Main main;
	
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("itemNumb"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		leer.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	@FXML
	public void addCustomerOrder(){
		
	}
	
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		customerOrderTable.setItems(main.getCustomerOrderData(null));
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
