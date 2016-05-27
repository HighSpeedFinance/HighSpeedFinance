package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class SupplierOrderEditWithSupplierDialogController {
	@FXML
	private TextField artNr;
	@FXML
	private TextField description;
	@FXML
	private ChoiceBox<String> paymentStatus;
	@FXML
	private DatePicker date;
	@FXML
	private TextField singlePrice;
	@FXML
	private TextField amount;
	@FXML
	private TextField tax;
	@FXML
	private TextField orderNr;
	@FXML
	private ChoiceBox<Supplier> supplierChoiceBox;

	private Stage dialogStage;
	private SupplierOrder supplierOrder;
	private boolean okClicked = false;

	public void setPaymentStatus() {
		paymentStatus.setItems(FXCollections.observableArrayList("erfasst", "bezahlt", "warten"));
		paymentStatus.getSelectionModel().select(0);
	}

	public void setSupplierChoiceBox() {
		supplierChoiceBox.setItems(SupplierUtil.loadAllSuppliers());
		supplierChoiceBox.getSelectionModel().select(0);
	}

	@FXML
	private void initialize() {
		setPaymentStatus();
		setSupplierChoiceBox();
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
		if (supplierOrder.getStatus() == Status.ENABLED || supplierOrder.getStatus() == Status.PENDING
				|| supplierOrder.getStatus() == Status.SUCCEEDED) {
			date.setValue(new LocalDateStringConverter().fromString(supplierOrder.getDate()));
			artNr.setText(String.valueOf(supplierOrder.getItemNumb()));
			description.setText(supplierOrder.getDescription());
			switch (supplierOrder.getStatus().toString()) {
			case "SUCCEEDED":
				paymentStatus.getSelectionModel().select(1);
				break;
			case "PENDING":
				paymentStatus.getSelectionModel().select(2);
				break;
			case "ENABLED":
				paymentStatus.getSelectionModel().select(0);
				break;
			}
			singlePrice.setText(String.valueOf(supplierOrder.getUnitPrice()));
			amount.setText(String.valueOf(supplierOrder.getAmount()));
			tax.setText(String.valueOf(supplierOrder.getTax()));
			orderNr.setText(String.valueOf(supplierOrder.getOrderNumber()));
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			supplierOrder.setAmount(Integer.parseInt(amount.getText()));
			supplierOrder.setDate(new LocalDateStringConverter().toString(date.getValue()));
			supplierOrder.setDescription(description.getText());
			supplierOrder.setItemNumb(Integer.parseInt(artNr.getText()));
			supplierOrder.setOrderNumber(Integer.parseInt(orderNr.getText()));
			switch (paymentStatus.getSelectionModel().getSelectedItem()) {
			case "erfasst":
				supplierOrder.setStatus(Status.ENABLED);
				break;
			case "bezahlt":
				supplierOrder.setStatus(Status.SUCCEEDED);
				break;
			case "warten":
				supplierOrder.setStatus(Status.PENDING);
				break;
			}
			supplierOrder.setUnitPrice(Double.parseDouble(singlePrice.getText()));
			supplierOrder.setSupplier(supplierChoiceBox.getSelectionModel().getSelectedItem());
			supplierOrder.setTax(Double.parseDouble(tax.getText()));

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

		if (amount.getText() == null || amount.getText().length() == 0) {
			errorMessage += "Keine gueltige Menge!\n";
		} else {
			try {
				Integer.parseInt(amount.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gueltige Menge (muss eine Zahl sein)!\n";
			}
		}
		if (date.getValue() == null) {
			errorMessage += "Kein gueltiges Datum!\n";
		}
		if (description.getText() == null || description.getText().length() == 0) {
			errorMessage += "Keine gueltige Beschreibung!\n";
		}
		if (artNr.getText() == null || artNr.getText().length() == 0) {
			errorMessage += "Keine gueltige Artikelnummer!\n";
		} else {
			try {
				Integer.parseInt(artNr.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gueltige Artikelnummer (muss eine Zahl sein)!\n";
			}
		}
		if (orderNr.getText() == null || orderNr.getText().length() == 0) {
			errorMessage += "Keine gueltige Ordernummer!\n";
		} else {
			try {
				Integer.parseInt(orderNr.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gueltige Ordernummer (muss eine Zahl sein)!\n";
			}
		}
		if (singlePrice.getText() == null || singlePrice.getText().length() == 0) {
			errorMessage += "Kein gueltiger Unitpreis!\n";
		} else {
			try {
				Double.parseDouble(singlePrice.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Kein gueltiger Unitpreis (muss eine Zahl sein)!\n";
			}
		}
		if (tax.getText() == null || tax.getText().length() == 0) {
			errorMessage += "Kein gueltiger Steuersatz!\n";
		} else {
			try {
				Double.parseDouble(tax.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Kein gueltiger Steuersatz (muss eine Zahl sein)!\n";
			}
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
