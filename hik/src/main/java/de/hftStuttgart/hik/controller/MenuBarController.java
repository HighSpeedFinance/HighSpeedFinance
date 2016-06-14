package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuBarController.
 */
public class MenuBarController {
	
	/** The image view. */
	@FXML
	private ImageView imageView;
	
	/** The main. */
	private Main main;
	

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		imageView.setImage(new Image("/de/hftStuttgart/hik/pics/Icon_Hahn.png"));
	}
	
	/**
	 * Show graphics.
	 */
	@FXML
	private void showGraphics(){
		main.showGraphics();
	}
	
	/**
	 * Show start.
	 *
	 * @param event the event
	 */
	@FXML
	private void showStart(ActionEvent event) {
		main.showCustomerAndSupplierOverview();
	}
	
	/**
	 * Show costs.
	 */
	@FXML
	private void showCosts(){
		main.showCosts();
	}
	
	/**
	 * Show income.
	 */
	@FXML
	private void showIncome(){
		main.showIncome();
	}
	
	/**
	 * Show help.
	 */
	@FXML
	private void showHelp(){
		main.showHelp();
	}
	
	/**
	 * Sets the main app.
	 *
	 * @param main the new main app
	 */
	public void setMainApp(Main main){
		this.main = main;
	}

}
