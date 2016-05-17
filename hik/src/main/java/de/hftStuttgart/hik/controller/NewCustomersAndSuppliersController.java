package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.Supplier;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class NewCustomersAndSuppliersController {
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
	private RadioButton radioDays;
	@FXML
	private RadioButton radioDate;
	@FXML
	private ComboBox<String> daysCombobox;

	private Main main;
	
	@FXML
	private void initialize() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20Tage", "30Tage"));
		daysCombobox.getSelectionModel().select(0);
	}

	public void setMainApp(Main main) {
		this.main = main;
	}

	@FXML
	private void showBestellung() {
		main.showCustomerOrder(customerTable.getSelectionModel().getSelectedItem());
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
