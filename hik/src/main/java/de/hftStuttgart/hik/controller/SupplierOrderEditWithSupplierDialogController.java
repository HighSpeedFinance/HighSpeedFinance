package de.hftStuttgart.hik.controller;

import javax.persistence.PersistenceException;

import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.AlertUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierOrderEditWithSupplierDialogController manages the views
 * from SupplierOrderEditWithCustomerDialog and is responsible for handling user
 * input and responses.
 */
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

	/**
	 * Sets the payment status. Selectable Options are "PENDING", "ENABLED" and
	 * "SUCCEEDED"
	 */
	public void setPaymentStatus() {
		paymentStatus.setItems(FXCollections.observableArrayList("offen", "bezahlt", "freigegeben"));
		paymentStatus.getSelectionModel().select(0);
	}

	/**
	 * Sets the supplier choice box.
	 */
	public void setSupplierChoiceBox() {
		try {
			supplierChoiceBox.setItems(SupplierUtil.loadAllSuppliers());
			supplierChoiceBox.getSelectionModel().select(0);
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}
	}

	/**
	 * Initialize.
	 */
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

	/**
	 * Sets the supplier order.
	 *
	 * @param supplierOrder
	 *            the new supplier order
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
	 * Checks if "Ok" is clicked.
	 *
	 * @return true, if "Ok" is clicked
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Handle ok.
	 */
	@FXML
	private void handleOk() {
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
			supplierOrder.setSupplier(supplierChoiceBox.getSelectionModel().getSelectedItem());
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
	 * Checks if input is valid and shows error messages if it is not
	 *
	 * @return true, if input is valid
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
			AlertUtil.isInputValid(errorMessage);
			return false;
		}
	}

}
