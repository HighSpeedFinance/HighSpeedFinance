package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class CustomerOrderEditDialogController {

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

	private Stage dialogStage;
	private CustomerOrder customerOrder;
	private boolean okClicked = false;

	@FXML
	private void initialize() {
		paymentStatus.setItems(FXCollections.observableArrayList("erfasst", "bezahlt", "warten"));
		paymentStatus.getSelectionModel().select(0);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
		if (customerOrder.getStatus() == Status.ENABLED || customerOrder.getStatus() == Status.PENDING
				|| customerOrder.getStatus() == Status.SUCCEEDED) {
			date.setValue(new LocalDateStringConverter().fromString(customerOrder.getDate()));
			artNr.setText(String.valueOf(customerOrder.getItemNumb()));
			description.setText(customerOrder.getDescription());
			switch (customerOrder.getStatus().toString()) {
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
			singlePrice.setText(String.valueOf(customerOrder.getUnitPrice()));
			amount.setText(String.valueOf(customerOrder.getAmount()));
			tax.setText(String.valueOf(customerOrder.getTax()));
			orderNr.setText(String.valueOf(customerOrder.getOrderNumber()));
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {
			customerOrder.setAmount(Integer.parseInt(amount.getText()));
			customerOrder.setDate(new LocalDateStringConverter().toString(date.getValue()));
			customerOrder.setDescription(description.getText());
			customerOrder.setItemNumb(Integer.parseInt(artNr.getText()));
			customerOrder.setOrderNumber(Integer.parseInt(orderNr.getText()));
			switch (paymentStatus.getSelectionModel().getSelectedItem()) {
			case "erfasst":
				customerOrder.setStatus(Status.ENABLED);
				break;
			case "bezahlt":
				customerOrder.setStatus(Status.SUCCEEDED);
				break;
			case "warten":
				customerOrder.setStatus(Status.PENDING);
				break;
			}
			customerOrder.setUnitPrice(Double.parseDouble(singlePrice.getText()));
			customerOrder.setTax(Double.parseDouble(tax.getText()));

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
			errorMessage += "Keine gueltige Menge eingegeben!\n";
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
