package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.Supplier;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupplierEditDialogController {

	@FXML
	private TextField supplierEditLastName;
	@FXML
	private TextField supplierEditFirstName;
	@FXML
	private TextField supplierEditStreet; 
	@FXML
	private TextField supplierEditHouseNumber;
	@FXML
	private TextField supplierEditPLZ;
	@FXML
	private TextField supplierEditCity;
	@FXML
	private TextField supplierEditCountry;
	@FXML
	private TextField supplierEditPhone;
	@FXML
	private TextField supplierEditFax;
	@FXML
	private TextField supplierEditMail;
	@FXML
	private ChoiceBox<String> supplierTitel;

	private Stage dialogStage;
	private Supplier supplier;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
		supplierTitel.setItems(FXCollections.observableArrayList(
			    "Frau", "Herr", "Firma"));
		supplierTitel.getSelectionModel().select(0);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;

		supplierEditFirstName.setText(supplier.getSupplierContactPersonFirstName());
		supplierEditLastName.setText(supplier.getSupplierContactPersonLastName());
		supplierEditStreet.setText(supplier.getSupplierStreet());
		supplierEditHouseNumber.setText(String.valueOf(supplier.getSupplierHouseNumber()));
		supplierEditPLZ.setText(String.valueOf(supplier.getSupplierPostalCode()));
		supplierEditCity.setText(supplier.getSupplierCity());
		supplierEditCountry.setText(supplier.getSupplierCountry());
		supplierEditPhone.setText(String.valueOf(supplier.getSupplierPhoneNumber()));
		supplierEditFax.setText(String.valueOf(supplier.getSupplierFax()));
		supplierEditMail.setText(supplier.getSupplierEmail());
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			supplier.setSupplierContactPersonFirstName(supplierEditFirstName.getText());
			supplier.setSupplierContactPersonLastName(supplierEditLastName.getText());
			supplier.setSupplierStreet(supplierEditStreet.getText());
			supplier.setSupplierHouseNumber(Integer.parseInt(supplierEditHouseNumber.getText()));
			supplier.setSupplierPostalCode(Integer.parseInt(supplierEditPLZ.getText()));
			supplier.setSupplierCity(supplierEditCity.getText());
			supplier.setSupplierCountry(supplierEditCountry.getText());
			supplier.setSupplierPhoneNumber(Integer.parseInt(supplierEditPhone.getText()));
			supplier.setSupplierFax(Integer.parseInt(supplierEditFax.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (supplierEditLastName.getText() == null || supplierEditLastName.getText().length() == 0) {
			errorMessage += "Kein gültiger Nachname!\n";
		}
		if (supplierEditFirstName.getText() == null || supplierEditFirstName.getText().length() == 0) {
			errorMessage += "Kein gültiger Vorname!\n";
		}
		if (supplierEditStreet.getText() == null || supplierEditStreet.getText().length() == 0) {
			errorMessage += "Keine gültige Straße!\n";
		}
		if (supplierEditHouseNumber.getText() == null || supplierEditHouseNumber.getText().length() == 0) {
			errorMessage += "Keine gültige Hausnummer!\n";
		} else {
			try {
				Integer.parseInt(supplierEditHouseNumber.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Hausnummer (muss eine Zahl sein)!\n";
			}
		}
		if (supplierEditPLZ.getText() == null || supplierEditPLZ.getText().length() == 0) {
			errorMessage += "Keine gültige Postleitzahlr!\n";
		} else {
			try {
				Integer.parseInt(supplierEditPLZ.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Postleitzahl (muss eine Zahl sein)!\n";
			}
		}
		if (supplierEditCity.getText() == null || supplierEditCity.getText().length() == 0) {
			errorMessage += "Keine gültige Stadt!\n";
		}

		if (supplierEditCountry.getText() == null || supplierEditCountry.getText().length() == 0) {
			errorMessage += "Kein gültiges Land!\n";
		}
		if (supplierEditPhone.getText() == null || supplierEditPhone.getText().length() == 0) {
			errorMessage += "Keine gültige Telefonnummer!\n";
		} else {
			try {
				Integer.parseInt(supplierEditPhone.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Telefonnummer (muss eine Zahl sein)!\n";
			}
		}
		if (supplierEditFax.getText() == null || supplierEditFax.getText().length() == 0) {
			errorMessage += "Keine gültige Fax!\n";
		} else {
			try {
				Integer.parseInt(supplierEditFax.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gültige Fax (muss eine Zahl sein)!\n";
			}
		}
		if (supplierEditMail.getText() == null || supplierEditMail.getText().length() == 0) {
			errorMessage += "Kein gültige Email!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}