package de.hftStuttgart.hik.application;

import java.io.IOException;
import java.time.LocalDate;

import de.hftStuttgart.hik.controller.CustomerOrderController;
import de.hftStuttgart.hik.controller.GraphicsController;
import de.hftStuttgart.hik.controller.IncomeController;
import de.hftStuttgart.hik.controller.MenuBarController;
import de.hftStuttgart.hik.controller.CustomerAndSupplierOrderOverviewController;
import de.hftStuttgart.hik.controller.NavigationBarCustomerController;
import de.hftStuttgart.hik.controller.NavigationBarSupplierController;
import de.hftStuttgart.hik.controller.NewCustomersAndSuppliersController;
import de.hftStuttgart.hik.controller.OpenCustomerAndSupplierOrders;
import de.hftStuttgart.hik.controller.ShowNewOrdersController;
import de.hftStuttgart.hik.controller.TabViewController;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class Main extends Application {

	private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
	private ObservableList<Customer> customerData = FXCollections.observableArrayList();
	private ObservableList<CustomerOrder> customerOrderData = FXCollections.observableArrayList();

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
			this.primaryStage.getIcons().add(new Image("/main/java/de/hftStuttgart/hik/pics/Icon_icon.png"));
			this.primaryStage.setResizable(true);

			FXMLLoader loader = new FXMLLoader(
					Main.class.getResource("/main/java/de/hftStuttgart/hik/view/MenuBar.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);

			MenuBarController controller = loader.getController();
			controller.setMainApp(this);

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
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/TabViewCustomerSupplier.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);
			showNavigationBarCustomer();

			TabViewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTabSelected(0);
		} catch (IOException e) {
			System.out.println("showCustomerAndSupplierOverview: " + e.getMessage());
		}
	}
	
	public void showSupplierOverview() {
		try {
			TabPane myPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/TabViewCustomerSupplier.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);
			showNavigationBarSupplier();

			TabViewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTabSelected(1);
		} catch (IOException e) {
			System.out.println("showCustomerAndSupplierOverview: " + e.getMessage());
		}
	}

	public void showNewCustomersAndSuppliers(int selection) {
		try {
			TabPane myPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/NewCustomersAndSuppliers.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);

			NewCustomersAndSuppliersController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTabSelected(selection);
		} catch (IOException e) {
			System.out.println("showCustomerAndSupplierOverview: " + e.getMessage());
		}
	}

	public void showNewOrders(int selection) {
		try {
			TabPane myPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/ShowNewOrders.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);

			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());

			ShowNewOrdersController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTabSelected(selection);
		} catch (IOException e) {
			System.out.println("showCustomerAndSupplierOverview: " + e.getMessage());
		}
	}

	public void showNavigationBarCustomer() {
		try {
			AnchorPane anchorPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/NavigationBarCustomer.fxml"));
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(anchorPane);

			NavigationBarCustomerController controller = loader.getController();
			controller.setMainApp(this);
		} catch (Exception e) {
			System.out.println("showNavigationBarCustomer: " + e.getMessage());
		}
	}

	public void showNavigationBarSupplier() {
		try {
			AnchorPane anchorPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/NavigationBarSupplier.fxml"));
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(anchorPane);
			
			NavigationBarSupplierController controller = loader.getController();
			controller.setMainApp(this);
		} catch (Exception e) {
			System.out.println("showNavigationBarCustomer: " + e.getMessage());
		}
	}

	public void showCustomerOrder(Customer customer) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/CustomerOrder.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Bestellungen " + customer.getCustomerContactPersonFirstName());
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders(customer));

			CustomerOrderController controller = loader.getController();
			controller.setCustomer(customer);
			controller.setMainApp(this);

			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void showCustomerAndSupplierOrderOverview(int selection) {
		try {
			TabPane myPane;
			FXMLLoader loader = new FXMLLoader(getClass()
					.getResource("/main/java/de/hftStuttgart/hik/view/CustomerAndSupplierOrderOverview.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);

			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());

			CustomerAndSupplierOrderOverviewController controller = loader.getController();
			controller.setMainApp(this);
			controller.setTabSelected(selection);
		} catch (Exception e) {
		}
	}

	public void showOpenCustomerAndSupplierOrders(int selection) {
		try {
			TabPane myPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/OpenCustomerAndSupplierOrders.fxml"));
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);

			customerOrderData.clear();

			OpenCustomerAndSupplierOrders controller = loader.getController();
			controller.setMainApp(this);
			controller.setTabSelected(selection);
		} catch (Exception e) {
		}
	}
	
	public void showIncome(){
		try {
			AnchorPane anchorPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/Income.fxml"));
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(null);
			rootLayout.setCenter(anchorPane);
			
			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());
			
			IncomeController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (Exception e) {
		}
	}
	
	public void showGraphics(){
		try {
			AnchorPane anchorPane;
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/Graphics.fxml"));
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(null);
			rootLayout.setCenter(anchorPane);
			
			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());
			
			GraphicsController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (Exception e) {
		}
	}

	// ToDo:Delete dummy
	public Main() {
		//addDummyCustomers();
		supplierData.addAll(SupplierUtil.loadAllSuppliers());
		customerData.addAll(CustomerUtil.loadAllCustomers());
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void addDummyCustomers() {
		for (int i = 0; i <= 10; i++) {
			CustomerUtil.addCustomer(new Customer(i, "customerCompanyName" + i, "customerContactPersonFirstName",
					"customerContactPersonLastName", "customerStreet", 73779, "customerCity", 0711344455,
					"customerEmail", 16, "customerCountry", 123, "customerTitel",
					new LocalDateStringConverter().toString(LocalDate.now().minusDays(60))));
		}
	}

	// auslagern
	public ObservableList<Supplier> getSupplierData() {
		return supplierData;
	}

	public ObservableList<Customer> getCustomerData() {
		return customerData;
	}

	public ObservableList<CustomerOrder> getCustomerOrderData() {
		return customerOrderData;
	}
}