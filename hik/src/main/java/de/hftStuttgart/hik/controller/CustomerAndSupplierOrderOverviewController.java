package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerAndSupplierOrderOverviewController {

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
	@FXML
	private TableView<SupplierOrder> supplierOrderTable;
	@FXML
	private TableColumn<SupplierOrder, String> orderNameSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderDescriptionSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderStatusSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderDateSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderSinglePriceSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderAmountSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderTotalPriceSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderArtSupplier;
	@FXML
	private TabPane tabPane;
	@FXML
	private TextField searchCustomerOrders;
	
	private Main main;
	

	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		orderNameSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("supplier"));
		orderDescriptionSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("description"));
		orderStatusSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("status"));
		orderDateSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("date"));
		orderSinglePriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("unitPrice"));
		orderAmountSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("amount"));
		orderArtSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("itemNumb"));
		orderTotalPriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("sumPrice"));
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void refreshCustomerOrderTable() {
		int selectedIndex = customerOrderTable.getSelectionModel().getSelectedIndex();
		customerOrderTable.setItems(null);
		customerOrderTable.layout();
		customerOrderTable.setItems(main.getCustomerOrderData());
		customerOrderTable.getSelectionModel().select(selectedIndex);
	}
	
	private void refreshSupplierOrderTable() {
		int selectedIndex = supplierOrderTable.getSelectionModel().getSelectedIndex();
		supplierOrderTable.setItems(null);
		supplierOrderTable.layout();
		supplierOrderTable.setItems(main.getSupplierOrderData());
		supplierOrderTable.getSelectionModel().select(selectedIndex);
	}
	
	@FXML
	public void addSupplierOrder() {
		SupplierOrder supplierOrder = new SupplierOrder();
		boolean okClicked = main.showSupplierOrderEditWithSupplierDialog(supplierOrder);
		if (okClicked) {
			SupplierUtil.loadAllSuppliers().get(supplierOrder.getSupplierObject().getId().intValue()-1).addOrder(supplierOrder);
			supplierOrder.setSupplier(supplierOrder.getSupplierObject());
			SupplierOrderUtil.addOrder(supplierOrder);
			main.addSupplierOrder(supplierOrder);
			refreshSupplierOrderTable();
		}
	}
	
	@FXML
	private void editSupplierOrder() {
		SupplierOrder supplierOrder = supplierOrderTable.getSelectionModel().getSelectedItem();
		if (supplierOrder != null) {
			boolean okClicked = main.showSupplierOrderEditDialog(supplierOrder);
			if (okClicked) {
				refreshSupplierOrderTable();
				SupplierOrderUtil.editOrder(supplierOrder);
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Keine Bestellung ausgewählt!");
			alert.showAndWait();
		}
	}

	@FXML
	public void addCustomerOrder() {
		CustomerOrder customerOrder = new CustomerOrder();
		boolean okClicked = main.showOrderEditWithCustomerDialog(customerOrder);
		if (okClicked) {
			CustomerUtil.loadAllCustomers().get(customerOrder.getCustomerObject().getId().intValue()-1).addOrder(customerOrder);
			customerOrder.setCustomer(customerOrder.getCustomerObject());
			OrderUtil.addOrder(customerOrder);
			main.addCustomerOrder(customerOrder);
		}
	}

	@FXML
	private void editCustomerOrder() {
		CustomerOrder customerOrder = customerOrderTable.getSelectionModel().getSelectedItem();
		if (customerOrder != null) {
			boolean okClicked = main.showOrderEditDialog(customerOrder);
			if (okClicked) {
				refreshCustomerOrderTable();
				OrderUtil.editOrder(customerOrder);
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("No CustomerOrder selected!");
			alert.showAndWait();
		}
	}
	
	@FXML
	public void searchSupplierOrders() {
		/*c.clear();
		if (!searchCustomer.getText().equals("")) {
			for (Customer cus : main.getCustomerData()) {
				if (cus.getCustomerNumber() == Integer.valueOf(searchCustomer.getText())) {
					customerList.add(cus);
				}
			}
			customerTable.setItems(customerList);
		}else{
			customerTable.setItems(main.getCustomerData());
		}*/
	}
	
	@FXML
	public void searchCustomerOrders() {
		/*c.clear();
		if (!searchCustomer.getText().equals("")) {
			for (Customer cus : main.getCustomerData()) {
				if (cus.getCustomerNumber() == Integer.valueOf(searchCustomer.getText())) {
					customerList.add(cus);
				}
			}
			customerTable.setItems(customerList);
		}else{
			customerTable.setItems(main.getCustomerData());
		}*/
	}

	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		customerOrderTable.setItems(main.getCustomerOrderData());
		supplierOrderTable.setItems(main.getSupplierOrderData());
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
	
	public void setTabSelected(int selection){
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if(selection == 0){
			selectionModel.select(0);
		}else{
			selectionModel.select(1);
		}
	}
}
