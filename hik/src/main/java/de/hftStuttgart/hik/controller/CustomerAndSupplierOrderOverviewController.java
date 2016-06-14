package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerAndSupplierOrderOverviewController.
 */
public class CustomerAndSupplierOrderOverviewController {

	/** The customer order table. */
	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	
	/** The order number. */
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	
	/** The order status. */
	@FXML
	private TableColumn<CustomerOrder, String> orderStatus;
	
	/** The order date. */
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	
	/** The order total price. */
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	
	/** The customer number. */
	@FXML
	private TableColumn<CustomerOrder, String> customerNumber;
	
	/** The supplier order table. */
	@FXML
	private TableView<SupplierOrder> supplierOrderTable;
	
	/** The order name supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderNameSupplier;
	
	/** The order description supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderDescriptionSupplier;
	
	/** The order status supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderStatusSupplier;
	
	/** The order date supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderDateSupplier;
	
	/** The order single price supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderSinglePriceSupplier;
	
	/** The order amount supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderAmountSupplier;
	
	/** The order total price supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderTotalPriceSupplier;
	
	/** The order art supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderArtSupplier;
	
	/** The tab pane. */
	@FXML
	private TabPane tabPane;
	
	/** The search customer orders. */
	@FXML
	private TextField searchCustomerOrders;
	
	/** The search supplier orders. */
	@FXML
	private TextField searchSupplierOrders;
	
	/** The main. */
	private Main main;
	
	/** The customer order list. */
	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();
	
	/** The supplier order list. */
	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();
	

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("statusString"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		orderNameSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("supplier"));
		orderDescriptionSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("description"));
		orderStatusSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("statusString"));
		orderDateSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("date"));
		orderSinglePriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("unitPrice"));
		orderAmountSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("amount"));
		orderArtSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("itemNumb"));
		orderTotalPriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("sumPrice"));
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	/**
	 * Refresh customer order table.
	 */
	private void refreshCustomerOrderTable() {
		int selectedIndex = customerOrderTable.getSelectionModel().getSelectedIndex();
		customerOrderTable.setItems(null);
		customerOrderTable.layout();
		customerOrderTable.setItems(main.getCustomerOrderData());
		customerOrderTable.getSelectionModel().select(selectedIndex);
	}
	
	/**
	 * Refresh supplier order table.
	 */
	private void refreshSupplierOrderTable() {
		int selectedIndex = supplierOrderTable.getSelectionModel().getSelectedIndex();
		supplierOrderTable.setItems(null);
		supplierOrderTable.layout();
		supplierOrderTable.setItems(main.getSupplierOrderData());
		supplierOrderTable.getSelectionModel().select(selectedIndex);
	}
	
	/**
	 * Adds the supplier order.
	 */
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
	
	/**
	 * Edits the supplier order.
	 */
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

	/**
	 * Adds the customer order.
	 */
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

	/**
	 * Edits the customer order.
	 */
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
	
	/**
	 * Search supplier orders.
	 */
	@FXML
	public void searchSupplierOrders() {
		supplierOrderList.clear();
		int searchInteger = 0;
		try {
			searchInteger = Integer.valueOf(searchSupplierOrders.getText());
		} catch (NumberFormatException e) {
		}
		if (!searchSupplierOrders.getText().equals("")) {
			for (SupplierOrder supOrder : main.getSupplierOrderData()) {
				if (supOrder.getOrderNumber() == searchInteger
						|| supOrder.getSupId() == searchInteger
						|| supOrder.getSupplierObject().getSupplierCompanyName().equals(searchCustomerOrders.getText())
						|| supOrder.getItemNumb()==searchInteger) {
					supplierOrderList.add(supOrder);
				}
			}
			supplierOrderTable.setItems(supplierOrderList);
		} else {
			supplierOrderTable.setItems(main.getSupplierOrderData());
		}
	}
	
	/**
	 * Search customer orders.
	 */
	@FXML
	public void searchCustomerOrders() {
		customerOrderList.clear();
		int searchInteger = 0;
		try {
			searchInteger = Integer.valueOf(searchCustomerOrders.getText());
		} catch (NumberFormatException e) {
		}
		if (!searchCustomerOrders.getText().equals("")) {
			for (CustomerOrder cusOrder : main.getCustomerOrderData()) {
				if (cusOrder.getOrderNumber() == searchInteger
						|| cusOrder.getCustomer() == searchInteger
						|| cusOrder.getCustomerObject().getCustomerCompanyName().equals(searchCustomerOrders.getText())
						|| (cusOrder.getCustomerObject().getCustomerContactPersonFirstName() + " " + cusOrder.getCustomerObject().getCustomerContactPersonLastName())
								.equals(searchCustomerOrders.getText())) {
					customerOrderList.add(cusOrder);
				}
			}
			customerOrderTable.setItems(customerOrderList);
		} else {
			customerOrderTable.setItems(main.getCustomerOrderData());
		}
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		customerOrderTable.setItems(main.getCustomerOrderData());
		supplierOrderTable.setItems(main.getSupplierOrderData());
	}

	/**
	 * Customer is selected.
	 */
	@FXML
	public void customerIsSelected() {
		try {
			main.showNavigationBarCustomer();
		} catch (Exception e) {

		}
	}

	/**
	 * Supplier is selected.
	 */
	@FXML
	public void supplierIsSelected() {
		main.showNavigationBarSupplier();
	}
	
	/**
	 * Sets the tab selected.
	 *
	 * @param selection the new tab selected
	 */
	public void setTabSelected(int selection){
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if(selection == 0){
			selectionModel.select(0);
		}else{
			selectionModel.select(1);
		}
	}
}
