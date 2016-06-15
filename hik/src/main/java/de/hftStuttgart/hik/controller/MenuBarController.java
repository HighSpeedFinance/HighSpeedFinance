package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class MenuBarController manages the views from the MenuBar and is
 * responsible for handling user input and responses. The methods show the
 * MenuBar with its components: Start, Income, Costs, AnnualAccount and Help
 */
public class MenuBarController {

	@FXML
	private ImageView imageView;

	private Main main;

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		imageView.setImage(new Image("/de/hftStuttgart/hik/pics/Icon_Hahn.png"));
	}

	/**
	 * Shows annual account
	 */
	@FXML
	private void showGraphics() {
		main.showGraphics();
	}

	/**
	 * Show start.
	 *
	 * @param event
	 *            the event
	 */
	@FXML
	private void showStart(ActionEvent event) {
		main.showCustomerAndSupplierOverview();
	}

	/**
	 * Show costs.
	 */
	@FXML
	private void showCosts() {
		main.showCosts();
	}

	/**
	 * Show income.
	 */
	@FXML
	private void showIncome() {
		main.showIncome();
	}

	/**
	 * Show help.
	 */
	@FXML
	private void showHelp() {
		main.showHelp();
	}

	/**
	 * Sets the main app.
	 *
	 * @param main
	 *            the new main app
	 */
	public void setMainApp(Main main) {
		this.main = main;
	}

}
