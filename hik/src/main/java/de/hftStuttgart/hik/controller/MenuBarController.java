package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuBarController {
	
	private Main main;

	@FXML
	private void showStart(ActionEvent event) {
		main.showCustomerAndSupplierOverview();
	}
	
	public void setMainApp(Main main){
		this.main = main;
	}

}
