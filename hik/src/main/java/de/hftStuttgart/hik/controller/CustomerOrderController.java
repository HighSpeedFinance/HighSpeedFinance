package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	@FXML
	private TableColumn<CustomerOrder, String> orderTax;

	private Customer customer;
	private Main main;

	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		leer.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("supId"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("itemNumb"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		orderTax.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("tax"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void refreshCustomerOrderTable() {
		int selectedIndex = customerOrderTable.getSelectionModel().getSelectedIndex();
		customerOrderTable.setItems(null);
		customerOrderTable.layout();
		customerOrderTable.setItems(main.getCustomerOrderData());
		customerOrderTable.getSelectionModel().select(selectedIndex);
	}

	@FXML
	public void addCustomerOrder() {
		CustomerOrder customerOrder = new CustomerOrder();
		boolean okClicked = main.showOrderEditDialog(customerOrder);
		if (okClicked) {
			CustomerUtil.loadAllCustomers().get(customer.getId().intValue()-1).addOrder(customerOrder);
			customerOrder.setCustomer(customer);
			OrderUtil.addOrder(customerOrder);
			main.addCustomerOrder(customerOrder);
			refreshCustomerOrderTable();
		}
	}

	@FXML
	private void editCustomerOrder() {
		CustomerOrder customerOrder = customerOrderTable.getSelectionModel().getSelectedItem();
		if (customerOrder != null) {
			boolean okClicked = main.showOrderEditDialog(customerOrder);
			if (okClicked) {
				OrderUtil.editOrder(customerOrder);
				refreshCustomerOrderTable();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("No CustomerOrder selected!");
			alert.showAndWait();
		}
	}

	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		customerOrderTable.setItems(main.getCustomerOrderData());
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
