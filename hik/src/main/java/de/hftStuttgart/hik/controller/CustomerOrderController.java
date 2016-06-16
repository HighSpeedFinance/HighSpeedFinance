package de.hftStuttgart.hik.controller;

import javax.persistence.PersistenceException;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.utilities.AlertUtil;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The Class CustomerOrderController manages the views from CustomerOrder and is
 * responsible for handling user input and responses. The Methods load all
 * orders from the database, add new orders and edit orders.
 * 
 */
public class CustomerOrderController {

	@FXML
	private TableView<CustomerOrder> customerOrderTable;

	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;

	@FXML
	private TableColumn<CustomerOrder, String> orderDescription;

	@FXML
	private TableColumn<CustomerOrder, String> customerID;

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

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		customerID.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customerNumber"));
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
	 * Adds the customer order to the table
	 */
	@FXML
	public void addCustomerOrder() {
		CustomerOrder customerOrder = new CustomerOrder();
		boolean okClicked = main.showOrderEditDialog(customerOrder);
		if (okClicked) {
			try {
				for (Customer cus : CustomerUtil.loadAllCustomers()) {
					if (customer.getId() == cus.getId()) {
						cus.addOrder(customerOrder);
					}
				}
				customerOrder.setCustomer(customer);
				OrderUtil.addOrder(customerOrder);
				main.addCustomerOrder(customerOrder);
				refreshCustomerOrderTable();
			} catch (PersistenceException e) {
				AlertUtil.noConnectionToDatabase();
			}
		}
	}

	/**
	 * Edits the customer order in the database
	 */
	@FXML
	private void editCustomerOrder() {
		CustomerOrder customerOrder = customerOrderTable.getSelectionModel().getSelectedItem();
		if (customerOrder != null) {
			boolean okClicked = main.showOrderEditDialog(customerOrder);
			if (okClicked) {
				try{
				OrderUtil.editOrder(customerOrder);
				} catch (PersistenceException e) {
					AlertUtil.noConnectionToDatabase();
				}
				refreshCustomerOrderTable();
			}
		} else {
			AlertUtil.noCustomerOrderSelected();
		}
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp
	 *            the new main app
	 */
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
