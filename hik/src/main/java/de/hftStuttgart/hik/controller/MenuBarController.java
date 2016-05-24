package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuBarController {
	
	@FXML
	private ImageView imageView;
	private Main main;
	

	@FXML
	private void initialize() {
		imageView.setImage(new Image("/main/java/de/hftStuttgart/hik/pics/Icon_Hahn.png"));
	}
	
	@FXML
	private void showGraphics(){
		main.showGraphics();
	}
	
	@FXML
	private void showStart(ActionEvent event) {
		main.showCustomerAndSupplierOverview();
	}
	
	@FXML
	private void showCosts(){
		main.showCosts();
	}
	
	@FXML
	private void showIncome(){
		main.showIncome();
	}
	
	public void setMainApp(Main main){
		this.main = main;
	}

}
