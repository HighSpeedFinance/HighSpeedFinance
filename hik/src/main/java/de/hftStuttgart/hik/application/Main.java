package de.hftStuttgart.hik.application;

import java.io.IOException;

import de.hftStuttgart.hik.controller.TabViewController;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.utilities.CustomerService;
import de.hftStuttgart.hik.utilities.SupplierService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	//hhhhh
	private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
	private ObservableList<Customer> customerData = FXCollections.observableArrayList();
	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}

	// LoadViews
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("HighSpeed-Finance");
			this.primaryStage.getIcons().add(new Image("/main/java/de/hftStuttgart/hik/pics/hik_icon.png"));
			this.primaryStage.setResizable(true);

			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/main/java/de/hftStuttgart/hik/view/MenuBar.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			System.out.println("start: " + e.getMessage());
		}
		showCustomerAndSupplierOverview();
		showNavigationBarCustomer();
	}

	public void showCustomerAndSupplierOverview() {
		try {
			TabPane myPane;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/de/hftStuttgart/hik/view/TabViewCustomerSupplier.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);

			TabViewController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			System.out.println("showCustomerAndSupplierOverview: " + e.getMessage());
		}
	}

	public void showNavigationBarCustomer() {
		try {
			AnchorPane anchorPane;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/de/hftStuttgart/hik/view/NavigationBarCustomer.fxml"));
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(anchorPane);
		} catch (Exception e) {
			System.out.println("showNavigationBarCustomer: " + e.getMessage());
		}
	}

	public void showNavigationBarSupplier() {
		try {
			AnchorPane anchorPane;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/de/hftStuttgart/hik/view/NavigationBarSupplier.fxml"));
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(anchorPane);
		} catch (Exception e) {
			System.out.println("showNavigationBarCustomer: " + e.getMessage());
		}
	}

	//Delete dummy 
	public Main() {
		//addDummyCustomers();
		supplierData.addAll(SupplierService.loadAllSuppliers());
		customerData.addAll(CustomerService.loadAllCustomers());
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public void addDummyCustomers(){
		for(int i=0;i<10;i++){
			CustomerService.addCustomer(new Customer(i,"customerCompanyName" + i,"customerContactPersonFirstName","customerContactPersonLastName","customerStreet"
					,73770,"customerCity",0711344455,"customerEmail",16,"customerCountry",123,"customerTitel"));
		}
	}

	// auslagern
	public ObservableList<Supplier> getSupplierData(Supplier sup) {
		if (sup != null)
			SupplierService.addSupplier(sup);
		return supplierData;
	}
	
	public ObservableList<Customer> getCustomerData(Customer cust) {
		return customerData;
	}
}