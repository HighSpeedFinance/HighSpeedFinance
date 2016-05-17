package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class CustomerOrderEditDialogController {

	@FXML
	private TextField artNr;
	@FXML
	private TextField description;
	@FXML
	private TextField leer;
	@FXML
	private ChoiceBox<String> paymentStatus;
	@FXML
	private DatePicker date;
	@FXML
	private TextField singlePrice;
	@FXML
	private TextField amount;
	@FXML
	private TextField sumPrice;
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
			leer.setText(String.valueOf(customerOrder.getSupId()));
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
			sumPrice.setText(String.valueOf(customerOrder.getSumPrice()));
			orderNr.setText(String.valueOf(customerOrder.getOrderNumber()));
		}
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		// ToDo: input testen siehe SupplierEditDialogController
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
		customerOrder.setSum(Double.parseDouble(sumPrice.getText()));
		customerOrder.setSupId(Integer.parseInt(leer.getText()));
		customerOrder.setUnitPrice(Double.parseDouble(singlePrice.getText()));

		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
