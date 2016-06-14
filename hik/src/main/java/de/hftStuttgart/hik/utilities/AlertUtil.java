package de.hftStuttgart.hik.utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// TODO: Auto-generated Javadoc
/**
 * The Class AlertUtil.
 */
public class AlertUtil {

	/**
	 * No connection to database.
	 */
	public static void noConnectionToDatabase(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText("");
		alert.setContentText("Keine Verbindung zu Datenbank!");
		alert.showAndWait();
	}
	
	/**
	 * Invalid url.
	 */
	public static void invalidURL(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText("");
		alert.setContentText("URL oder Datei konnte nicht geladen werden!");
		alert.showAndWait();
	}
	public static void programStart(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information!");
		alert.setHeaderText("");
		alert.setContentText("Das Programm ist in Kürze für Sie bereit!");
		alert.show();
	}
}
