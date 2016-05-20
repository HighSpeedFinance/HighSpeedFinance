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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.LocalDateStringConverter;

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
	@FXML
	private ToggleGroup zeitraum;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;

	private Main main;

	@FXML
	private void initialize() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysCombobox.getSelectionModel().select(0);

		daysCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadCustomerList(t1);
			}
		});

		zeitraum.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysCombobox.getSelectionModel().select(0);
				loadCustomerList(daysCombobox.getSelectionModel().getSelectedItem());
			}
		});

		customerNameColumn
				.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerContactPersonFirstName"));
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

	public void setMainApp(Main main) {
		this.main = main;
		loadCustomerList("10 Tage");
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

	@FXML
	public void loadCustomers() {
		loadCustomerList(daysCombobox.getSelectionModel().getSelectedItem());
	}

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
}
