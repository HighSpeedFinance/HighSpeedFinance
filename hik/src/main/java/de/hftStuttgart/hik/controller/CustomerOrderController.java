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

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerOrderController.
 */
public class CustomerOrderController {

	/** The customer order table. */
	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	
	/** The order number. */
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	
	/** The order description. */
	@FXML
	private TableColumn<CustomerOrder, String> orderDescription;
	
	/** The customer id. */
	@FXML
	private TableColumn<CustomerOrder, String> customerID;
	
	/** The order status. */
	@FXML
	private TableColumn<CustomerOrder, String> orderStatus;
	
	/** The order date. */
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	
	/** The order single price. */
	@FXML
	private TableColumn<CustomerOrder, String> orderSinglePrice;
	
	/** The order amount. */
	@FXML
	private TableColumn<CustomerOrder, String> orderAmount;
	
	/** The order total price. */
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	
	/** The order art. */
	@FXML
	private TableColumn<CustomerOrder, String> orderArt;
	
	/** The order tax. */
	@FXML
	private TableColumn<CustomerOrder, String> orderTax;

	/** The customer. */
	private Customer customer;
	
	/** The main. */
	private Main main;

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		customerID.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("statusString"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("itemNumb"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		orderTax.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("tax"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
	 * Adds the customer order.
	 */
	@FXML
	public void addCustomerOrder() {
		CustomerOrder customerOrder = new CustomerOrder();
		boolean okClicked = main.showOrderEditDialog(customerOrder);
		if (okClicked) {
			for(Customer cus : CustomerUtil.loadAllCustomers()){
				if(customer.getId() == cus.getId()){
					cus.addOrder(customerOrder);
				}
			}
			customerOrder.setCustomer(customer);
			OrderUtil.addOrder(customerOrder);
			main.addCustomerOrder(customerOrder);
			refreshCustomerOrderTable();
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
				OrderUtil.editOrder(customerOrder);
				refreshCustomerOrderTable();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Bitte wählen Sie eine Kundenbestellung aus!");
			alert.showAndWait();
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
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
