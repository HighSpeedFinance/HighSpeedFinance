package de.hftStuttgart.hik.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class AlertUtil holds methods which show different error messages for
 * different failure cases
 */
public class AlertUtil {

	/**
	 * Shows a error message if there is no connection to the database
	 */
	public static void noConnectionToDatabase() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText("");
		alert.setContentText("Keine Verbindung zu Datenbank!");
		alert.showAndWait();
	}

	/**
	 * Shows a error message if the URL or the file could not be loaded or was
	 * not found
	 */
	public static void invalidURL() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText("");
		alert.setContentText("URL oder Datei konnte nicht geladen werden!");
		alert.showAndWait();
	}

	/**
	 * Shows an information message when starting the program while the database
	 * is loading
	 */
	public static void programStart() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information!");
		alert.setHeaderText("");
		alert.setContentText("Das Programm ist in Kürze für Sie bereit!");
		alert.show();
	}
}
