package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.utilities.AlertUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

/**
 * The Class CustomerOrderEditDialogController manages the views from
 * CustomerOrderEditDialog and is responsible for handling user input and
 * responses.
 */
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

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		paymentStatus.setItems(FXCollections.observableArrayList("offen", "bezahlt"));
		paymentStatus.getSelectionModel().select(0);
	}

	/**
	 * Sets the dialog stage.
	 *
	 * @param dialogStage
	 *            the new dialog stage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Gets the customer order.
	 *
	 * @return the customer order
	 */
	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	/**
	 * Sets the customer order.
	 *
	 * @param customerOrder
	 *            the new customer order
	 */
	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
		date.setEditable(false);
		if (customerOrder.getStatus() == Status.ENABLED || customerOrder.getStatus() == Status.PENDING
				|| customerOrder.getStatus() == Status.SUCCEEDED) {
			date.setValue(new LocalDateStringConverter().fromString(customerOrder.getDate()));
			date.setEditable(false);
			date.setDisable(true);
			date.setStyle("-fx-opacity: 1");
			date.getEditor().setStyle("-fx-opacity: 1");
			artNr.setText(String.valueOf(customerOrder.getItemNumb()));
			artNr.setEditable(false);
			description.setText(customerOrder.getDescription());
			description.setEditable(false);
			switch (customerOrder.getStatus().toString()) {
			case "SUCCEEDED":
				paymentStatus.setItems(FXCollections.observableArrayList("bezahlt"));
				paymentStatus.getSelectionModel().select(0);
				break;
			case "PENDING":
				paymentStatus.setItems(FXCollections.observableArrayList("offen", "bezahlt"));
				paymentStatus.getSelectionModel().select(0);
				break;
			}
			singlePrice.setText(String.valueOf(customerOrder.getUnitPrice()));
			singlePrice.setEditable(false);
			amount.setText(String.valueOf(customerOrder.getAmount()));
			amount.setEditable(false);
			tax.setText(String.valueOf(customerOrder.getTax()));
			tax.setEditable(false);
			orderNr.setText(String.valueOf(customerOrder.getOrderNumber()));
			orderNr.setEditable(false);
		}
	}

	/**
	 * Checks if "Ok" is clicked.
	 *
	 * @return true if "OK" is clicked
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Handle ok.
	 */
	@FXML
	private void handleOk() {
		amount.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		date.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		description.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		artNr.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		orderNr.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		singlePrice.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		tax.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
		if (isInputValid()) {
			customerOrder.setAmount(Integer.parseInt(amount.getText()));
			customerOrder.setDate(new LocalDateStringConverter().toString(date.getValue()));
			customerOrder.setDescription(description.getText());
			customerOrder.setItemNumb(Integer.parseInt(artNr.getText()));
			customerOrder.setOrderNumber(Integer.parseInt(orderNr.getText()));
			switch (paymentStatus.getSelectionModel().getSelectedItem()) {
			case "offen":
				customerOrder.setStatus(Status.PENDING);
				break;
			case "bezahlt":
				customerOrder.setStatus(Status.SUCCEEDED);
				break;
			}
			customerOrder.setUnitPrice(Double.parseDouble(singlePrice.getText()));
			customerOrder.setTax(Double.parseDouble(tax.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Handle cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Checks if input is valid and shows error messages if not
	 *
	 * @return true, if input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (amount.getText() == null || amount.getText().length() == 0) {
			errorMessage += "Keine gueltige Menge eingegeben!\n";
			amount.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			try {
				Integer.parseInt(amount.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gueltige Menge (muss eine Zahl sein)!\n";
				amount.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			}
		}
		if (date.getValue() == null) {
			errorMessage += "Kein gueltiges Datum!\n";
			date.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		}
		if (description.getText() == null || description.getText().length() == 0) {
			errorMessage += "Keine gueltige Beschreibung!\n";
			description.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		}
		if (artNr.getText() == null || artNr.getText().length() == 0) {
			errorMessage += "Keine gueltige Artikelnummer!\n";
			artNr.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			try {
				Integer.parseInt(artNr.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gueltige Artikelnummer (muss eine Zahl sein)!\n";
				artNr.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			}
		}
		if (orderNr.getText() == null || orderNr.getText().length() == 0) {
			errorMessage += "Keine gueltige Ordernummer!\n";
			orderNr.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			try {
				Integer.parseInt(orderNr.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Keine gueltige Ordernummer (muss eine Zahl sein)!\n";
				orderNr.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			}
		}
		if (singlePrice.getText() == null || singlePrice.getText().length() == 0) {
			errorMessage += "Kein gueltiger Unitpreis!\n";
			singlePrice.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			try {
				Double.parseDouble(singlePrice.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Kein gueltiger Unitpreis (muss eine Zahl sein)!\n";
				singlePrice.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			}
		}
		if (tax.getText() == null || tax.getText().length() == 0) {
			errorMessage += "Kein gueltiger Steuersatz!\n";
			tax.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
		} else {
			try {
				Double.parseDouble(tax.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Kein gueltiger Steuersatz (muss eine Zahl sein)!\n";
				tax.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
			}
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			AlertUtil.isInputValid(errorMessage);
			return false;
		}
	}
}
