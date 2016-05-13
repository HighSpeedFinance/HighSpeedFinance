package de.hftStuttgart.hik.controller;

import java.io.IOException;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.utilities.SupplierService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


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

	private Main main;

	public TabViewController() {
	}

	@FXML
	private void showBestellung() {

	}

	private void refreshPersonTable() {
		int selectedIndex = supplierTable.getSelectionModel().getSelectedIndex();
		supplierTable.setItems(null);
		supplierTable.layout();
		supplierTable.setItems(main.getSupplierData(null));
		supplierTable.getSelectionModel().select(selectedIndex);
	}

	@FXML
	private void initialize() {
		supplierNumberColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierNumber"));
		supplierNameColumn
				.setCellValueFactory(new PropertyValueFactory<Supplier, String>("supplierContactPersonFirstName"));

		customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerContactPersonFirstName"));
		customerNumberColumn
				.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerNumber"));

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
	}
	
	private void showCustomerDetails(Customer customer) {
		if (customer != null) {
			customerHeading.setText(customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerTitel.setText(customer.getCustomerTitel());
			customerName.setText(customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
			customerStreet.setText(customer.getCustomerStreet() + ". " + String.valueOf(customer.getCustomerHouseNumber()));
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

	@FXML
	private void deleteSupplier() {
		int selectedIndex = supplierTable.getSelectionModel().getSelectedIndex();
		if (supplierTable.getSelectionModel().getSelectedItem() != null) {
			SupplierService.deleteSupplier(supplierTable.getSelectionModel().getSelectedItem());
			supplierTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("No Supplier selected!");
			alert.showAndWait();
		}
	}

	@FXML
	private void newSupplier() {
		Supplier tempSupplier = new Supplier();
		boolean okClicked = showSupplierEditDialog(tempSupplier);
		if (okClicked) {
			main.getSupplierData(tempSupplier).add(tempSupplier);
		}
	}

	@FXML
	private void editSupplier() {
		Supplier selectedSupplier = supplierTable.getSelectionModel().getSelectedItem();
		if (selectedSupplier != null) {
			boolean okClicked = showSupplierEditDialog(selectedSupplier);
			if (okClicked) {

				refreshPersonTable();
				showSupplierDetails(selectedSupplier);
				SupplierService.editSupplier(selectedSupplier);
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("No Supplier selected!");
			alert.showAndWait();
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
			supplierNameLabel.setText(supplier.getSupplierNumber() + "-------");
		} else {
			supplierPhoneNumberLabel.setText("");
			supplierCompanyNameLabel.setText("");
			supplierContactPersonLabel.setText("");
			supplierPostalCodeCityLabel.setText("");
			supplierStreetLabel.setText("");
			supplierEmailLabel.setText("");
		}
	}

	public boolean showSupplierEditDialog(Supplier supplier) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/de/hftStuttgart/hik/view/SupplierEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Supplier");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(main.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			SupplierEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setSupplier(supplier);

			dialogStage.showAndWait();
			return controller.isOkClicked();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		supplierTable.setItems(mainApp.getSupplierData(null));
		customerTable.setItems(mainApp.getCustomerData(null));
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
