package de.hftStuttgart.hik.application;

import java.io.IOException;

import javax.persistence.PersistenceException;

import de.hftStuttgart.hik.controller.CustomerOrderController;
import de.hftStuttgart.hik.controller.CustomerOrderEditDialogController;
import de.hftStuttgart.hik.controller.CustomerOrderEditWithCustomerDialogController;
import de.hftStuttgart.hik.controller.HelpController;
import de.hftStuttgart.hik.controller.AnnualAccountsController;
import de.hftStuttgart.hik.controller.IncomeController;
import de.hftStuttgart.hik.controller.MenuBarController;
import de.hftStuttgart.hik.controller.CostsController;
import de.hftStuttgart.hik.controller.CustomerAndSupplierOrderOverviewController;
import de.hftStuttgart.hik.controller.NavigationBarCustomerController;
import de.hftStuttgart.hik.controller.NavigationBarSupplierController;
import de.hftStuttgart.hik.controller.NewCustomersAndSuppliersController;
import de.hftStuttgart.hik.controller.OpenCustomerAndSupplierOrders;
import de.hftStuttgart.hik.controller.ShowNewOrdersController;
import de.hftStuttgart.hik.controller.SupplierOrderController;
import de.hftStuttgart.hik.controller.SupplierOrderEditDialogController;
import de.hftStuttgart.hik.controller.SupplierOrderEditWithSupplierDialogController;
import de.hftStuttgart.hik.controller.TabViewController;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.AlertUtil;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
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

public class Main extends Application {

	private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
	private ObservableList<Customer> customerData = FXCollections.observableArrayList();
	private ObservableList<CustomerOrder> customerOrderData = FXCollections.observableArrayList();
	private ObservableList<SupplierOrder> supplierOrderData = FXCollections.observableArrayList();

	private Stage primaryStage;
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("HighSpeed-Finance");
		this.primaryStage.setResizable(false);
		try {
			this.primaryStage.getIcons().add(new Image("/de/hftStuttgart/hik/pics/Icon_icon.png"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Main.class.getResource("/view/MenuBar.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException | IllegalStateException e) {
			System.out.println(e.getMessage());
		}
		Scene scene = new Scene(rootLayout);		

		MenuBarController controller = loader.getController();
		controller.setMainApp(this);

		primaryStage.setScene(scene);
		primaryStage.show();
		showCustomerAndSupplierOverview();
		showNavigationBarCustomer();
	}

	public void showCustomerAndSupplierOverview() {
		TabPane myPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/TabViewCustomerSupplier.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		showNavigationBarCustomer();

		TabViewController controller = loader.getController();
		controller.setMainApp(this);
		controller.setTabSelected(0);
	}

	public void showSupplierOverview() {
		TabPane myPane;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/TabViewCustomerSupplier.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		showNavigationBarSupplier();

		TabViewController controller = loader.getController();
		controller.setMainApp(this);
		controller.setTabSelected(1);
	}

	public void showNewCustomersAndSuppliers(int selection) {
		TabPane myPane;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/NewCustomersAndSuppliers.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		NewCustomersAndSuppliersController controller = loader.getController();
		controller.setMainApp(this);
		controller.setTabSelected(selection);
	}

	public void showNewOrders(int selection) {
		TabPane myPane;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/ShowNewOrders.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			myPane = (TabPane) loader.load();
			rootLayout.setCenter(myPane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try {
			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());
			supplierOrderData.clear();
			supplierOrderData.addAll(SupplierOrderUtil.loadAllOrders());
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}

		ShowNewOrdersController controller = loader.getController();
		controller.setMainApp(this);
		controller.setTabSelected(selection);
	}

	public void showNavigationBarCustomer() {
		AnchorPane anchorPane;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/NavigationBarCustomer.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			anchorPane = (AnchorPane) loader.load();
			
			rootLayout.setLeft(anchorPane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		rootLayout.autosize();

		NavigationBarCustomerController controller = loader.getController();
		controller.setMainApp(this);

	}

	public void showNavigationBarSupplier() {
		AnchorPane anchorPane;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/NavigationBarSupplier.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			anchorPane = (AnchorPane) loader.load();
			rootLayout.setLeft(anchorPane);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		NavigationBarSupplierController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void showCustomerOrder(Customer customer) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/CustomerOrder.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		AnchorPane page = null;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Rechnungen: " + customer.getCustomerContactPersonFirstName() + " " + customer.getCustomerContactPersonLastName());
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		try {
			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders(customer));
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}

		CustomerOrderController controller = loader.getController();
		controller.setCustomer(customer);
		controller.setMainApp(this);

		dialogStage.showAndWait();
	}

	public void showSupplierOrder(Supplier supplier) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/SupplierOrder.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		AnchorPane page = null;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Bestellungen: " + supplier.getSupplierCompanyName());
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		try {
			supplierOrderData.clear();
			supplierOrderData.addAll(SupplierOrderUtil.loadAllOrders(supplier));
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}

		SupplierOrderController controller = loader.getController();
		controller.setSupplier(supplier);
		controller.setMainApp(this);

		dialogStage.showAndWait();
	}

	public void showCustomerAndSupplierOrderOverview(int selection) {
		TabPane myPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass()
					.getResource("/view/CustomerAndSupplierOrderOverview.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			myPane = (TabPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		rootLayout.setCenter(myPane);

		try {
			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());
			supplierOrderData.clear();
			supplierOrderData.addAll(SupplierOrderUtil.loadAllOrders());
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}

		CustomerAndSupplierOrderOverviewController controller = loader.getController();
		controller.setMainApp(this);
		controller.setTabSelected(selection);
	}

	public void showOpenCustomerAndSupplierOrders(int selection) {
		TabPane myPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/OpenCustomerAndSupplierOrders.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			myPane = (TabPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		rootLayout.setCenter(myPane);

		customerOrderData.clear();

		OpenCustomerAndSupplierOrders controller = loader.getController();
		controller.setMainApp(this);
		controller.setTabSelected(selection);
	}

	public void showIncome() {
		AnchorPane anchorPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/Income.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		rootLayout.setLeft(null);
		rootLayout.setCenter(anchorPane);

		IncomeController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void showCosts() {
		AnchorPane anchorPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/Costs.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		rootLayout.setLeft(null);
		rootLayout.setCenter(anchorPane);

		CostsController controller = loader.getController();
		controller.setMainApp(this);
	}

	public void showGraphics() {
		AnchorPane anchorPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/AnnualAccounts.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		rootLayout.setLeft(null);
		rootLayout.setCenter(anchorPane);

		try {
			customerOrderData.clear();
			customerOrderData.addAll(OrderUtil.loadAllOrders());
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}

		AnnualAccountsController controller = loader.getController();
		controller.setMainApp(this);
	}
	
	public void showHelp() {
		AnchorPane anchorPane = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/Help.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		try {
			anchorPane = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		rootLayout.setLeft(null);
		rootLayout.setCenter(anchorPane);

		HelpController controller = loader.getController();
		controller.setMainApp(this);
	}

	public boolean showSupplierOrderEditWithSupplierDialog(SupplierOrder supplierOrder) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass()
					.getResource("/view/SupplierOrderEditWithSupplierDialog.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Bestellung hinzufuegen");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		SupplierOrderEditWithSupplierDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setSupplierOrder(supplierOrder);

		dialogStage.showAndWait();
		return controller.isOkClicked();
	}

	public boolean showSupplierOrderEditDialog(SupplierOrder supplierOrder) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/SupplierOrderEditDialog.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Bestellung Hinzufuegen");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		SupplierOrderEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setSupplierOrder(supplierOrder);

		dialogStage.showAndWait();
		return controller.isOkClicked();
	}

	public boolean showOrderEditDialog(CustomerOrder customerOrder) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(getClass().getResource("/view/OrderEditDialog.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Rechnung hinzufuegen");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		CustomerOrderEditDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setCustomerOrder(customerOrder);

		dialogStage.showAndWait();
		return controller.isOkClicked();
	}

	public boolean showOrderEditWithCustomerDialog(CustomerOrder customerOrder) {
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(
					getClass().getResource("/view/OrderEditWithCustomerDialog.fxml"));
		} catch (IllegalArgumentException e) {
			AlertUtil.invalidURL();
		}
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Rechnung hinzufuegen");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(getPrimaryStage());
		Scene scene = new Scene(page);
		dialogStage.setScene(scene);

		CustomerOrderEditWithCustomerDialogController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setCustomerOrder(customerOrder);

		dialogStage.showAndWait();
		return controller.isOkClicked();
	}

	public Main() {
		try {
			supplierData.addAll(SupplierUtil.loadAllSuppliers());
			customerData.addAll(CustomerUtil.loadAllCustomers());
			customerOrderData.addAll(OrderUtil.loadAllOrders());
			supplierOrderData.addAll(SupplierOrderUtil.loadAllOrders());
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<Supplier> getSupplierData() {
		return supplierData;
	}

	public ObservableList<Customer> getCustomerData() {
		return customerData;
	}

	public ObservableList<CustomerOrder> getCustomerOrderData() {
		return customerOrderData;
	}

	public ObservableList<SupplierOrder> getSupplierOrderData() {
		return supplierOrderData;
	}

	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderData.add(customerOrder);
	}

	public void addSupplierOrder(SupplierOrder supplierOrder) {
		supplierOrderData.add(supplierOrder);
	}
}