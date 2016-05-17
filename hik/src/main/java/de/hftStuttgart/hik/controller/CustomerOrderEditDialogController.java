package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.model.CustomerOrder;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerOrderEditDialogController {
	
	@FXML
	private TextField artNr;
	@FXML
	private TextField description;
	@FXML
	private TextField leer;
	@FXML
	private TextField paymentStatus;
	@FXML
	private TextField date;
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

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		//ToDo: input testen siehe SupplierEditDialogController
		customerOrder.setAmount(Integer.parseInt(amount.getText()));
		customerOrder.setDate(Integer.parseInt(date.getText()));
		customerOrder.setDescription(description.getText());
		customerOrder.setItemNumb(Integer.parseInt(leer.getText()));
		customerOrder.setOrderNumber(Integer.parseInt(orderNr.getText()));
		customerOrder.setStatus(paymentStatus.getText());
		customerOrder.setSum(Double.parseDouble(sumPrice.getText()));
		customerOrder.setSupId(Integer.parseInt("2"));
		customerOrder.setUnitPrice(Double.parseDouble(singlePrice.getText()));
		
		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
}
