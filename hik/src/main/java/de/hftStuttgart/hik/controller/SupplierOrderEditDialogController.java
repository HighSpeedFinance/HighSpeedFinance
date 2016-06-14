package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierOrderEditDialogController.
 */
public class SupplierOrderEditDialogController {
	
	/** The art nr. */
	@FXML
	private TextField artNr;
	
	/** The description. */
	@FXML
	private TextField description;
	
	/** The payment status. */
	@FXML
	private ChoiceBox<String> paymentStatus;
	
	/** The date. */
	@FXML
	private DatePicker date;
	
	/** The single price. */
	@FXML
	private TextField singlePrice;
	
	/** The amount. */
	@FXML
	private TextField amount;
	
	/** The tax. */
	@FXML
	private TextField tax;
	
	/** The order nr. */
	@FXML
	private TextField orderNr;

	/** The dialog stage. */
	private Stage dialogStage;
	
	/** The supplier order. */
	private SupplierOrder supplierOrder;
	
	/** The ok clicked. */
	private boolean okClicked = false;

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		paymentStatus.setItems(FXCollections.observableArrayList("offen", "bezahlt", "freigegeben"));
		paymentStatus.getSelectionModel().select(0);
	}

	/**
	 * Sets the dialog stage.
	 *
	 * @param dialogStage the new dialog stage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Gets the supplier order.
	 *
	 * @return the supplier order
	 */
	public SupplierOrder getSupplierOrder() {
		return supplierOrder;
	}

	/**
	 * Sets the supplier order.
	 *
	 * @param supplierOrder the new supplier order
	 */
	public void setSupplierOrder(SupplierOrder supplierOrder) {
		this.supplierOrder = supplierOrder;
		date.setEditable(false);
		if (supplierOrder.getStatus() == Status.ENABLED || supplierOrder.getStatus() == Status.PENDING
				|| supplierOrder.getStatus() == Status.SUCCEEDED) {
			date.setValue(new LocalDateStringConverter().fromString(supplierOrder.getDate()));
			date.setEditable(false);
			date.setDisable(true);
			artNr.setText(String.valueOf(supplierOrder.getItemNumb()));
			artNr.setEditable(false);
			description.setText(supplierOrder.getDescription());
			description.setEditable(false);
			switch (supplierOrder.getStatus().toString()) {
			case "SUCCEEDED":
				paymentStatus.setItems(FXCollections.observableArrayList("bezahlt"));
				paymentStatus.getSelectionModel().select(0);
				break;
			case "PENDING":
				paymentStatus.setItems(FXCollections.observableArrayList("offen", "freigegeben"));
				paymentStatus.getSelectionModel().select(0);
				break;
			case "ENABLED":
				paymentStatus.setItems(FXCollections.observableArrayList("freigegeben", "bezahlt"));
				paymentStatus.getSelectionModel().select(0);
				break;
			}
			singlePrice.setText(String.valueOf(supplierOrder.getUnitPrice()));
			singlePrice.setEditable(false);
			amount.setText(String.valueOf(supplierOrder.getAmount()));
			amount.setEditable(false);
			tax.setText(String.valueOf(supplierOrder.getTax()));
			tax.setEditable(false);
			orderNr.setText(String.valueOf(supplierOrder.getOrderNumber()));
			orderNr.setEditable(false);
		}
	}

	/**
	 * Checks if is ok clicked.
	 *
	 * @return true, if is ok clicked
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
			supplierOrder.setAmount(Integer.parseInt(amount.getText()));
			supplierOrder.setDate(new LocalDateStringConverter().toString(date.getValue()));
			supplierOrder.setDescription(description.getText());
			supplierOrder.setItemNumb(Integer.parseInt(artNr.getText()));
			supplierOrder.setOrderNumber(Integer.parseInt(orderNr.getText()));
			switch (paymentStatus.getSelectionModel().getSelectedItem()) {
			case "offen":
				supplierOrder.setStatus(Status.PENDING);
				break;
			case "bezahlt":
				supplierOrder.setStatus(Status.SUCCEEDED);
				break;
			case "freigegeben":
				supplierOrder.setStatus(Status.ENABLED);
				break;
			}
			supplierOrder.setUnitPrice(Double.parseDouble(singlePrice.getText()));
			supplierOrder.setTax(Double.parseDouble(tax.getText()));
			
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
	 * Checks if is input valid.
	 *
	 * @return true, if is input valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (amount.getText() == null || amount.getText().length() == 0) {
			errorMessage += "Keine gueltige Menge!\n";
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
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}
}
