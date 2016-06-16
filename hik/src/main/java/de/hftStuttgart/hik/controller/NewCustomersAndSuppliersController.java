package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.utilities.AlertUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.converter.LocalDateStringConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class NewCustomersAndSuppliersController manages the views from
 * NewCustomersAndSuppliers and is responsible for handling user input and
 * responses.
 */
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
	private Label customerFax;

	@FXML
	private Label customerMail;

	@FXML
	private Label supplierCompanyName;

	@FXML
	private Label supplierContactPerson;

	@FXML
	private Label supplierStreet;

	@FXML
	private Label supplierPLZ;

	@FXML
	private Label supplierPhoneNumber;

	@FXML
	private Label supplierMail;

	@FXML
	private Label supplierFax;
	
	@FXML
	private Label supplierNameLabel;

	@FXML
	private TableView<Supplier> supplierTable;

	@FXML
	private TableColumn<Supplier, String> supplierNumberColumn;

	@FXML
	private TableColumn<Supplier, String> supplierNameColumn;

	@FXML
	private RadioButton radioDays;

	@FXML
	private RadioButton radioDate;

	@FXML
	private RadioButton radioDaysSupplier;

	@FXML
	private RadioButton radioDateSupplier;

	@FXML
	private ComboBox<String> daysCombobox;

	@FXML
	private ComboBox<String> daysComboboxSupplier;

	@FXML
	private ToggleGroup period;

	@FXML
	private ToggleGroup periodSupplier;

	@FXML
	private DatePicker startDate;

	@FXML
	private DatePicker endDate;

	@FXML
	private DatePicker startDateSupplier;

	@FXML
	private DatePicker endDateSupplier;

	@FXML
	private TabPane tabPane;

	private Main main;

	/**
	 * Sets the days combo box. selectable options are 10 days, 20 days, 30 days
	 * or all
	 */
	public void setDaysComboBox() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysCombobox.getSelectionModel().select(0);
	}

	/**
	 * Sets the days combobox supplier. selectable options are 10 days, 20 days,
	 * 30 days or all
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

		period.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysCombobox.getSelectionModel().select(0);
				loadCustomerList(daysCombobox.getSelectionModel().getSelectedItem());
			}
		});

		periodSupplier.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysComboboxSupplier.getSelectionModel().select(0);
				loadSupplierList(daysComboboxSupplier.getSelectionModel().getSelectedItem());
			}
		});

		customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerContactPersonName"));
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
	 * @param customer
	 *            the customer
	 */
	private void showCustomerDetails(Customer customer) {
		if (customer != null) {
			customerHeading.setText(
					customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerHeading.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
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
	 * @param supplier
	 *            the supplier
	 */
	private void showSupplierDetails(Supplier supplier) {
		if (supplier != null) {
			supplierNameLabel.setText(supplier.getSupplierCompanyName());
			supplierNameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
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
	 * Show Order. And shows error Message if no customer is selected
	 */
	@FXML
	private void showCustomerOrder() {
		if (customerTable.getSelectionModel().getSelectedItem() != null)
			main.showCustomerOrder(customerTable.getSelectionModel().getSelectedItem());
		else {
			AlertUtil.noCustomerOrderSelected();
		}
	}

	/**
	 * Show supplier order. And shows error Message if no Supplier is selected.
	 */
	@FXML
	private void showSupplierOrder() {
		if (supplierTable.getSelectionModel().getSelectedItem() != null)
			main.showSupplierOrder(supplierTable.getSelectionModel().getSelectedItem());
		else {
			AlertUtil.noSupplierOrderSelected();
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
	 * Load supplier list. selectable options are 10 days, 20 days, 30 days or
	 * all
	 *
	 * @param comboValue
	 *            the combo value
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
	 * Load customer list. selectable options are 10 days, 20 days, 30 days or
	 * all
	 *
	 * @param comboValue
	 *            the combo value
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
	 * Sets the main app with the default selection: 10 days
	 *
	 * @param main
	 *            the new main app
	 */
	public void setMainApp(Main main) {
		this.main = main;
		loadCustomerList("10 Tage");
		loadSupplierList("10 Tage");
	}

	/**
	 * Shows NavigationBarCustomer if Customer is selected
	 */
	@FXML
	public void customerIsSelected() {
		try {
			main.showNavigationBarCustomer();
		} catch (Exception e) {
		}
	}

	/**
	 * Shows NavigationBarSupplier if Supplier is selected
	 */
	@FXML
	public void supplierIsSelected() {
		main.showNavigationBarSupplier();
	}

	/**
	 * Sets the tab selected.
	 *
	 * @param selection
	 *            the new tab selected
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