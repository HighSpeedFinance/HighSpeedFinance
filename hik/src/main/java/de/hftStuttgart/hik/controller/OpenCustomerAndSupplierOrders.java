package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.utilities.OrderUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OpenCustomerAndSupplierOrders {
	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	@FXML
	private TableColumn<CustomerOrder, String> orderStatus;
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	@FXML
	private TableColumn<CustomerOrder, String> customerNumber;

	private Main main;

	public void setMainApp(Main main) {
		this.main = main;
	}
	
	public void loadCustomerOrders(){
		ObservableList<CustomerOrder> customerOrderList = OrderUtil.loadAllOrdersWhereStatusOpen();
		customerOrderTable.setItems(customerOrderList);
	}

	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		loadCustomerOrders();
	}

	@FXML
	public void customerIsSelected() {
		try {
			main.showNavigationBarCustomer();
		} catch (Exception e) {
		}
	}

	@FXML
	public void supplierIsSelected() {
		main.showNavigationBarSupplier();
	}
}