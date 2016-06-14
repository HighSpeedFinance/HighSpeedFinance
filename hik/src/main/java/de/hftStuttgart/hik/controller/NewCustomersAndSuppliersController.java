package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateStringConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class NewCustomersAndSuppliersController.
 */
public class NewCustomersAndSuppliersController {
	
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
	
	/** The supplier company name. */
	@FXML
	private Label supplierCompanyName;
	
	/** The supplier contact person. */
	@FXML
	private Label supplierContactPerson;
	
	/** The supplier street. */
	@FXML
	private Label supplierStreet;
	
	/** The supplier plz. */
	@FXML
	private Label supplierPLZ;
	
	/** The supplier phone number. */
	@FXML
	private Label supplierPhoneNumber;
	
	/** The supplier mail. */
	@FXML
	private Label supplierMail;
	
	/** The supplier fax. */
	@FXML
	private Label supplierFax;
	
	/** The supplier table. */
	@FXML
	private TableView<Supplier> supplierTable;
	
	/** The supplier number column. */
	@FXML
	private TableColumn<Supplier, String> supplierNumberColumn;
	
	/** The supplier name column. */
	@FXML
	private TableColumn<Supplier, String> supplierNameColumn;
	
	/** The radio days. */
	@FXML
	private RadioButton radioDays;
	
	/** The radio date. */
	@FXML
	private RadioButton radioDate;
	
	/** The radio days supplier. */
	@FXML
	private RadioButton radioDaysSupplier;
	
	/** The radio date supplier. */
	@FXML
	private RadioButton radioDateSupplier;
	
	/** The days combobox. */
	@FXML
	private ComboBox<String> daysCombobox;
	
	/** The days combobox supplier. */
	@FXML
	private ComboBox<String> daysComboboxSupplier;
	
	/** The zeitraum. */
	@FXML
	private ToggleGroup zeitraum;
	
	/** The zeitraum supplier. */
	@FXML
	private ToggleGroup zeitraumSupplier;
	
	/** The start date. */
	@FXML
	private DatePicker startDate;
	
	/** The end date. */
	@FXML
	private DatePicker endDate;
	
	/** The start date supplier. */
	@FXML
	private DatePicker startDateSupplier;
	
	/** The end date supplier. */
	@FXML
	private DatePicker endDateSupplier;
	
	/** The tab pane. */
	@FXML
	private TabPane tabPane;

	/** The main. */
	private Main main;

	/**
	 * Sets the days combo box.
	 */
	public void setDaysComboBox() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysCombobox.getSelectionModel().select(0);
	}

	/**
	 * Sets the days combobox supplier.
	 */
	public void setDaysComboboxSupplier() {
		daysComboboxSupplier.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysComboboxSupplier.getSelectionModel().select(0);
	}

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		startDate.setEditable(false);
		endDate.setEditable(false);
		startDateSupplier.setEditable(false);
		endDateSupplier.setEditable(false);
		setDaysComboBox();
		daysCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadCustomerList(t1);
			}
		});

		setDaysComboboxSupplier();
		daysComboboxSupplier.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadSupplierList(t1);
			}
		});

		zeitraum.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysCombobox.getSelectionModel().select(0);
				loadCustomerList(daysCombobox.getSelectionModel().getSelectedItem());
			}
		});

		zeitraumSupplier.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysComboboxSupplier.getSelectionModel().select(0);
				loadSupplierList(daysComboboxSupplier.getSelectionModel().getSelectedItem());
			}
		});

		customerNameColumn
				.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerContactPersonName"));
		customerNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerNumber"));

		customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		showCustomerDetails(null);

		customerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				showCustomerDetails(newValue);
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

		supplierNumberColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierNumber"));
		supplierNameColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierCompanyName"));

		supplierTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		showSupplierDetails(null);

		supplierTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
			public void changed(ObservableValue<? extends Supplier> observable, Supplier oldValue, Supplier newValue) {
				showSupplierDetails(newValue);
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
			customerTitel.setText(customer.getCustomerTitel());
			customerName.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerStreet.setText(customer.getCustomerAdressStreet() + ". "
					+ String.valueOf(customer.getCustomerAdressHouseNumber()));
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
			supplierCompanyName.setText(supplier.getSupplierCompanyName());
			supplierContactPerson.setText(
					supplier.getSupplierContactPersonFirstName() + " " + supplier.getSupplierContactPersonLastName());
			supplierStreet.setText(supplier.getSupplierAdressStreet() + ", "
					+ String.valueOf(supplier.getSupplierAdressHouseNumber()));
			supplierPLZ.setText(
					String.valueOf(supplier.getSupplierAdressPostIndex()) + " " + supplier.getSupplierAdressCity());
			supplierPhoneNumber.setText(supplier.getSupplierPhoneNumber());
			supplierMail.setText(supplier.getSupplierEmail());
			supplierFax.setText(supplier.getSupplierFax());
		} else {
			supplierCompanyName.setText("");
			supplierContactPerson.setText("");
			supplierStreet.setText("");
			supplierPLZ.setText("");
			supplierPhoneNumber.setText("");
			supplierMail.setText("");
			supplierFax.setText("");
		}
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
			alert.setContentText("Keinen Kunden ausgewaehlt!");
			alert.showAndWait();
		}
	}

	/**
	 * Show supplier bestellung.
	 */
	@FXML
	private void showSupplierBestellung() {
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

	/**
	 * Load customers.
	 */
	@FXML
	public void loadCustomers() {
		loadCustomerList(daysCombobox.getSelectionModel().getSelectedItem());
	}

	/**
	 * Load suppliers.
	 */
	@FXML
	public void loadSuppliers() {
		loadSupplierList(daysComboboxSupplier.getSelectionModel().getSelectedItem());
	}

	/**
	 * Load supplier list.
	 *
	 * @param comboValue the combo value
	 */
	public void loadSupplierList(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);
		ObservableList<Supplier> supplierList = main.getSupplierData();
		ObservableList<Supplier> supplierListInTime = FXCollections.observableArrayList();
		if (radioDaysSupplier.isSelected()) {
			switch (comboValue) {
			case "10 Tage":
				for (Supplier sup : supplierList) {
					if (new LocalDateStringConverter().fromString(sup.getDate()).isAfter(local.minusDays(10)))
						supplierListInTime.add(sup);
				}
				break;
			case "20 Tage":
				for (Supplier sup : supplierList) {
					if (new LocalDateStringConverter().fromString(sup.getDate()).isAfter(local.minusDays(20)))
						supplierListInTime.add(sup);
				}
				break;
			case "30 Tage":
				for (Supplier sup : supplierList) {
					if (new LocalDateStringConverter().fromString(sup.getDate()).isAfter(local.minusDays(30)))
						supplierListInTime.add(sup);
				}
				break;
			}
		} else if (radioDateSupplier.isSelected() && startDateSupplier.getValue() != null
				&& endDateSupplier.getValue() != null) {
			for (Supplier sup : supplierList) {
				if (new LocalDateStringConverter().fromString(sup.getDate()).isAfter(startDateSupplier.getValue())
						&& new LocalDateStringConverter().fromString(sup.getDate()).isBefore(endDateSupplier.getValue())
						|| new LocalDateStringConverter().fromString(sup.getDate()).isEqual(endDateSupplier.getValue())
						|| new LocalDateStringConverter().fromString(sup.getDate())
								.isEqual(startDateSupplier.getValue())) {
					supplierListInTime.add(sup);
				}
			}
		}
		supplierTable.setItems(supplierListInTime);
	}

	/**
	 * Load customer list.
	 *
	 * @param comboValue the combo value
	 */
	public void loadCustomerList(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);
		ObservableList<Customer> customerList = main.getCustomerData();
		ObservableList<Customer> customerListInTime = FXCollections.observableArrayList();
		if (radioDays.isSelected()) {
			switch (comboValue) {
			case "10 Tage":
				for (Customer cus : customerList) {
					if (new LocalDateStringConverter().fromString(cus.getDate()).isAfter(local.minusDays(10)))
						customerListInTime.add(cus);
				}
				break;
			case "20 Tage":
				for (Customer cus : customerList) {
					if (new LocalDateStringConverter().fromString(cus.getDate()).isAfter(local.minusDays(20)))
						customerListInTime.add(cus);
				}
				break;
			case "30 Tage":
				for (Customer cus : customerList) {
					if (new LocalDateStringConverter().fromString(cus.getDate()).isAfter(local.minusDays(30)))
						customerListInTime.add(cus);
				}
				break;
			}
		} else if (radioDate.isSelected() && startDate.getValue() != null && endDate.getValue() != null) {
			for (Customer cus : customerList) {
				if (new LocalDateStringConverter().fromString(cus.getDate()).isAfter(startDate.getValue())
						&& new LocalDateStringConverter().fromString(cus.getDate()).isBefore(endDate.getValue())
						|| new LocalDateStringConverter().fromString(cus.getDate()).isEqual(endDate.getValue())
						|| new LocalDateStringConverter().fromString(cus.getDate()).isEqual(startDate.getValue())) {
					customerListInTime.add(cus);
				}
			}
		}
		customerTable.setItems(customerListInTime);
	}

	/**
	 * Sets the main app.
	 *
	 * @param main the new main app
	 */
	public void setMainApp(Main main) {
		this.main = main;
		loadCustomerList("10 Tage");
		loadSupplierList("10 Tage");
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
	public void setTabSelected(int selection) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if (selection == 0) {
			selectionModel.select(0);
		} else {
			selectionModel.select(1);
		}
	}
}