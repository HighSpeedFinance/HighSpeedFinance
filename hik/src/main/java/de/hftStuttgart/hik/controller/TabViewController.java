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
	private Label customerFax;
	@FXML
	private Label customerMail;
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
	private Label supplierCompanyLabel;
	@FXML
	private Label supplierFaxLabel;
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
	private void initialize() {
		supplierNumberColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierNumber"));
		supplierNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierCompanyName"));

		customerNameColumn
				.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerContactPersonName"));
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

	@FXML
	private void showBestellung() {
		if (customerTable.getSelectionModel().getSelectedItem() != null)
			main.showCustomerOrder(customerTable.getSelectionModel().getSelectedItem());
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Bitte wählen Sie einen Kunden aus!");
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
			alert.setContentText("Bitte wählen Sie einen Lieferanten aus!");
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
						|| cus.getCustomerContactPersonFirstName().toLowerCase().equals(searchCustomer.getText().toLowerCase())
						|| (cus.getCustomerContactPersonFirstName().toLowerCase() + " " + cus.getCustomerContactPersonLastName().toLowerCase())
								.equals(searchCustomer.getText().toLowerCase())
						|| cus.getCustomerContactPersonLastName().toLowerCase().equals(searchCustomer.getText().toLowerCase())) {
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
						|| sup.getSupplierContactPersonFirstName().toLowerCase().equals(searchSupplier.getText().toLowerCase())
						|| sup.getSupplierContactPersonLastName().toLowerCase().equals(searchSupplier.getText().toLowerCase())
						|| sup.getSupplierAdressCity().toLowerCase().equals(searchSupplier.getText().toLowerCase())
						|| sup.getSupplierCompanyName().toLowerCase().equals(searchSupplier.getText().toLowerCase())) {
					supplierList.add(sup);
				}
			}
			supplierTable.setItems(supplierList);
		} else {
			supplierTable.setItems(main.getSupplierData());
		}
	}

	private void showCustomerDetails(Customer customer) {
		if (customer != null) {
			customerHeading.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerTitel.setText(customer.getCustomerTitel());
			customerName.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerStreet.setText(customer.getCustomerAdressStreet()+ " " + String.valueOf(customer.getCustomerAdressHouseNumber()));
			customerPLZ.setText(
					String.valueOf(customer.getCustomerAdressPostIndex()) + " " + customer.getCustomerAdressCity());
			customerCountry.setText(customer.getCustomerAdressCountry());
			customerPhone.setText(customer.getCustomerPhoneNumber());
			customerFax.setText(customer.getCustomerFax());
			customerMail.setText(customer.getCustomerEmail());
		} else {
			customerHeading.setText("");
			customerTitel.setText("");
			customerName.setText("");
			customerStreet.setText("");
			customerPLZ.setText("");
			customerCountry.setText("");
			customerPhone.setText("");
			customerFax.setText("");
			customerMail.setText("");
		}
	}

	private void showSupplierDetails(Supplier supplier) {
		if (supplier != null) {
			supplierPhoneNumberLabel.setText(supplier.getSupplierPhoneNumber());
			supplierCompanyNameLabel.setText(String.valueOf(supplier.getSupplierCompanyName()));
			supplierContactPersonLabel.setText(
					supplier.getSupplierContactPersonFirstName() + " " + supplier.getSupplierContactPersonLastName());
			supplierPostalCodeCityLabel.setText(
					String.valueOf(supplier.getSupplierAdressPostIndex() + " " + supplier.getSupplierAdressCity()));
			supplierStreetLabel.setText(supplier.getSupplierAdressStreet() + " " + supplier.getSupplierAdressHouseNumber());
			supplierEmailLabel.setText(supplier.getSupplierEmail());
			supplierFaxLabel.setText(supplier.getSupplierFax());
			supplierCompanyLabel.setText(supplier.getSupplierCompanyName());
		} else {
			supplierPhoneNumberLabel.setText("");
			supplierCompanyNameLabel.setText("");
			supplierContactPersonLabel.setText("");
			supplierPostalCodeCityLabel.setText("");
			supplierStreetLabel.setText("");
			supplierEmailLabel.setText("");
			supplierFaxLabel.setText("");
			supplierCompanyLabel.setText("");
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
