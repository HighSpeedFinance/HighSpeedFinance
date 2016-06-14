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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// TODO: Auto-generated Javadoc
/**
 * The Class TabViewController.
 */
public class TabViewController {
	
	/** The customer table. */
	@FXML
	private TableView<Customer> customerTable;
	
	/** The customer number column. */
	@FXML
	private TableColumn<Customer, String> customerNumberColumn;
	
	/** The customer name column. */
	@FXML
	private TableColumn<Customer, String> customerNameColumn;
	
	/** The customer heading. */
	@FXML
	private Label customerHeading;
	
	/** The customer titel. */
	@FXML
	private Label customerTitel;
	
	/** The customer name. */
	@FXML
	private Label customerName;
	
	/** The customer street. */
	@FXML
	private Label customerStreet;
	
	/** The customer plz. */
	@FXML
	private Label customerPLZ;
	
	/** The customer country. */
	@FXML
	private Label customerCountry;
	
	/** The customer phone. */
	@FXML
	private Label customerPhone;
	
	/** The customer fax. */
	@FXML
	private Label customerFax;
	
	/** The customer mail. */
	@FXML
	private Label customerMail;
	
	/** The supplier table. */
	@FXML
	private TableView<Supplier> supplierTable;
	
	/** The supplier number column. */
	@FXML
	private TableColumn<Supplier, String> supplierNumberColumn;
	
	/** The supplier name column. */
	@FXML
	private TableColumn<Supplier, String> supplierNameColumn;
	
	/** The supplier company name label. */
	@FXML
	private Label supplierCompanyNameLabel;
	
	/** The supplier contact person label. */
	@FXML
	private Label supplierContactPersonLabel;
	
	/** The supplier street label. */
	@FXML
	private Label supplierStreetLabel;
	
	/** The supplier postal code city label. */
	@FXML
	private Label supplierPostalCodeCityLabel;
	
	/** The supplier phone number label. */
	@FXML
	private Label supplierPhoneNumberLabel;
	
	/** The supplier email label. */
	@FXML
	private Label supplierEmailLabel;
	
	/** The supplier name label. */
	@FXML
	private Label supplierNameLabel;
	
	/** The supplier company label. */
	@FXML
	private Label supplierCompanyLabel;
	
	/** The supplier fax label. */
	@FXML
	private Label supplierFaxLabel;
	
	/** The supplier account owner label. */
	@FXML
	private Label supplierAccountOwnerLabel;
	
	/** The supplier bic label. */
	@FXML
	private Label supplierBICLabel;
	
	/** The supplier credit label. */
	@FXML
	private Label supplierCreditLabel;
	
	/** The supplier iban label. */
	@FXML
	private Label supplierIBANLabel;
	
	/** The search customer. */
	@FXML
	private TextField searchCustomer;
	
	/** The search supplier. */
	@FXML
	private TextField searchSupplier;
	
	/** The customer tab. */
	@FXML
	private Tab customerTab;
	
	/** The supplier tab. */
	@FXML
	private Tab supplierTab;
	
	/** The tab pane. */
	@FXML
	private TabPane tabPane;

	/** The main. */
	private Main main;
	
	/** The customer list. */
	private ObservableList<Customer> customerList = FXCollections.observableArrayList();
	
	/** The supplier list. */
	private ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

	/**
	 * Instantiates a new tab view controller.
	 */
	public TabViewController() {
	}

	/**
	 * Initialize.
	 */
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

	/**
	 * Show bestellung.
	 */
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

	/**
	 * Show bestellung supplier.
	 */
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

	/**
	 * Search customer.
	 */
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

	/**
	 * Search suppliers.
	 */
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

	/**
	 * Show customer details.
	 *
	 * @param customer the customer
	 */
	private void showCustomerDetails(Customer customer) {
		if (customer != null) {
			customerHeading.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerHeading.setFont(Font.font("Verdana", FontWeight.BOLD,12));
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

	/**
	 * Show supplier details.
	 *
	 * @param supplier the supplier
	 */
	private void showSupplierDetails(Supplier supplier) {
		if (supplier != null) {
			supplierPhoneNumberLabel.setText(supplier.getSupplierPhoneNumber());
			supplierCompanyNameLabel.setText(String.valueOf(supplier.getSupplierCompanyName()));
			supplierCompanyNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD,12));
			supplierContactPersonLabel.setText(
					supplier.getSupplierContactPersonFirstName() + " " + supplier.getSupplierContactPersonLastName());
			supplierPostalCodeCityLabel.setText(
					String.valueOf(supplier.getSupplierAdressPostIndex() + " " + supplier.getSupplierAdressCity()));
			supplierStreetLabel.setText(supplier.getSupplierAdressStreet() + " " + supplier.getSupplierAdressHouseNumber());
			supplierEmailLabel.setText(supplier.getSupplierEmail());
			supplierFaxLabel.setText(supplier.getSupplierFax());
			supplierCompanyLabel.setText(supplier.getSupplierCompanyName());
			supplierAccountOwnerLabel.setText(supplier.getSupplierPaymentDetails().getAccountOwner());
			supplierBICLabel.setText(supplier.getSupplierPaymentDetails().getBic());
			supplierCreditLabel.setText(supplier.getSupplierPaymentDetails().getCreditInstitution());
			supplierIBANLabel.setText(supplier.getSupplierPaymentDetails().getIban());
		} else {
			supplierPhoneNumberLabel.setText("");
			supplierCompanyNameLabel.setText("");
			supplierContactPersonLabel.setText("");
			supplierPostalCodeCityLabel.setText("");
			supplierStreetLabel.setText("");
			supplierEmailLabel.setText("");
			supplierFaxLabel.setText("");
			supplierCompanyLabel.setText("");
			supplierAccountOwnerLabel.setText("");
			supplierBICLabel.setText("");
			supplierCreditLabel.setText("");
			supplierIBANLabel.setText("");
		}
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		supplierTable.setItems(mainApp.getSupplierData());
		customerTable.setItems(mainApp.getCustomerData());
	}

	/**
	 * Sets the tab selected.
	 *
	 * @param selection the new tab selected
	 */
	public void setTabSelected(int selection) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if (selection == 0) {
			selectionModel.select(0);
		} else {
			selectionModel.select(1);
		}
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
}
