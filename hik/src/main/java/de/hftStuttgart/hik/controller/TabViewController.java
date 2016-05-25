package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.Supplier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TabViewController {
	@FXML
	private TableView<Customer> customerTable;
	@FXML
	private TableColumn<Customer, String> customerNumberColumn;
	@FXML
	private TableColumn<Customer, String> customerNameColumn;
	@FXML
	private Label customerHeading;
	@FXML
	private Label customerTitel;
	@FXML
	private Label customerName;
	@FXML
	private Label customerStreet;
	@FXML
	private Label customerPLZ;
	@FXML
	private Label customerCountry;
	@FXML
	private Label customerPhone;
	@FXML
	private TableView<Supplier> supplierTable;
	@FXML
	private TableColumn<Supplier, String> supplierNumberColumn;
	@FXML
	private TableColumn<Supplier, String> supplierNameColumn;
	@FXML
	private Label supplierCompanyNameLabel;
	@FXML
	private Label supplierContactPersonLabel;
	@FXML
	private Label supplierStreetLabel;
	@FXML
	private Label supplierPostalCodeCityLabel;
	@FXML
	private Label supplierPhoneNumberLabel;
	@FXML
	private Label supplierEmailLabel;
	@FXML
	private Label supplierNameLabel;
	@FXML
	private TextField searchCustomer;
	@FXML
	private TextField searchSupplier;
	@FXML
	private Tab customerTab;
	@FXML
	private Tab supplierTab;
	@FXML
	private TabPane tabPane;

	private Main main;
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	private ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

	public TabViewController() {
	}

	@FXML
	private void showBestellung() {
		if (customerTable.getSelectionModel().getSelectedItem() != null)
			main.showCustomerOrder(customerTable.getSelectionModel().getSelectedItem());
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Keinen Kunden ausgewählt!");
			alert.showAndWait();
		}
	}

	@FXML
	private void showBestellungSupplier() {
		if (supplierTable.getSelectionModel().getSelectedItem() != null)
			main.showSupplierOrder(supplierTable.getSelectionModel().getSelectedItem());
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Keinen Lieferanten ausgewaehlt!");
			alert.showAndWait();
		}
	}

	@FXML
	public void searchCustomer() {
		customerList.clear();
		int searchInteger = 0;
		try {
			searchInteger = Integer.valueOf(searchCustomer.getText());
		} catch (NumberFormatException e) {
		}
		if (!searchCustomer.getText().equals("")) {
			for (Customer cus : main.getCustomerData()) {
				if (cus.getCustomerNumber() == searchInteger
						|| cus.getCustomerContactPersonFirstName().equals(searchCustomer.getText())
						|| cus.getCustomerCompanyName().equals(searchCustomer.getText())
						|| (cus.getCustomerContactPersonFirstName() + " " + cus.getCustomerContactPersonLastName())
								.equals(searchCustomer.getText())) {
					customerList.add(cus);
				}
			}
			customerTable.setItems(customerList);
		} else {
			customerTable.setItems(main.getCustomerData());
		}
	}

	@FXML
	public void searchSuppliers() {
		supplierList.clear();
		int searchInteger = 0;
		try {
			searchInteger = Integer.valueOf(searchSupplier.getText());
		} catch (Exception e) {
		}
		if (!searchSupplier.getText().equals("")) {
			for (Supplier sup : main.getSupplierData()) {
				if (sup.getSupplierNumber() == searchInteger
						|| sup.getSupplierContactPersonFirstName().equals(searchSupplier.getText())
						|| sup.getSupplierContactPersonLastName().equals(searchSupplier.getText())
						|| sup.getSupplierCity().equals(searchSupplier.getText())
						|| sup.getSupplierCompanyName().equals(searchSupplier.getText())) {
					supplierList.add(sup);
				}
			}
			supplierTable.setItems(supplierList);
		} else {
			supplierTable.setItems(main.getSupplierData());
		}

	}

	@FXML
	private void initialize() {
		supplierNumberColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierNumber"));
		supplierNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierCompanyName"));

		customerNameColumn
				.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerContactPersonFirstName"));
		customerNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerNumber"));

		supplierTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		showSupplierDetails(null);

		customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		showCustomerDetails(null);

		supplierTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
			public void changed(ObservableValue<? extends Supplier> observable, Supplier oldValue, Supplier newValue) {
				showSupplierDetails(newValue);
			}
		});

		customerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				showCustomerDetails(newValue);
			}
		});

		supplierTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
					main.showSupplierOrder(supplierTable.getSelectionModel().getSelectedItem());
				}
			}
		});

		customerTable.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
					main.showCustomerOrder(customerTable.getSelectionModel().getSelectedItem());
				}
			}
		});
	}

	private void showCustomerDetails(Customer customer) {
		if (customer != null) {
			customerHeading.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerTitel.setText(customer.getCustomerTitel());
			customerName.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerStreet
					.setText(customer.getCustomerStreet() + ". " + String.valueOf(customer.getCustomerHouseNumber()));
			customerPLZ.setText(String.valueOf(customer.getCustomerPostalCode()) + " " + customer.getCustomerCity());
			customerCountry.setText(customer.getCustomerCountry());
			customerPhone.setText(String.valueOf(customer.getCustomerPhoneNumber()));
		} else {
			customerHeading.setText("");
			customerTitel.setText("");
			customerName.setText("");
			customerStreet.setText("");
			customerPLZ.setText("");
			customerCountry.setText("");
			customerPhone.setText("");
		}
	}

	private void showSupplierDetails(Supplier supplier) {
		if (supplier != null) {
			supplierPhoneNumberLabel.setText(String.valueOf(supplier.getSupplierPhoneNumber()));
			supplierCompanyNameLabel.setText(String.valueOf(supplier.getSupplierCompanyName()));
			supplierContactPersonLabel.setText(
					supplier.getSupplierContactPersonFirstName() + " " + supplier.getSupplierContactPersonLastName());
			supplierPostalCodeCityLabel
					.setText(String.valueOf(supplier.getSupplierPostalCode() + " " + supplier.getSupplierCity()));
			supplierStreetLabel.setText(supplier.getSupplierStreet());
			supplierEmailLabel.setText(supplier.getSupplierEmail());
		} else {
			supplierPhoneNumberLabel.setText("");
			supplierCompanyNameLabel.setText("");
			supplierContactPersonLabel.setText("");
			supplierPostalCodeCityLabel.setText("");
			supplierStreetLabel.setText("");
			supplierEmailLabel.setText("");
		}
	}

	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		supplierTable.setItems(mainApp.getSupplierData());
		customerTable.setItems(mainApp.getCustomerData());
	}

	public void setTabSelected(int selection) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if (selection == 0) {
			selectionModel.select(0);
		} else {
			selectionModel.select(1);
		}
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
