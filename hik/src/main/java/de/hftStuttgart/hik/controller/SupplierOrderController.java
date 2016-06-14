package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierOrderController.
 */
public class SupplierOrderController {

	/** The supplier order table. */
	@FXML
	private TableView<SupplierOrder> supplierOrderTable;
	
	/** The order number. */
	@FXML
	private TableColumn<SupplierOrder, String> orderNumber;
	
	/** The order description. */
	@FXML
	private TableColumn<SupplierOrder, String> orderDescription;
	
	/** The order status. */
	@FXML
	private TableColumn<SupplierOrder, String> orderStatus;
	
	/** The order date. */
	@FXML
	private TableColumn<SupplierOrder, String> orderDate;
	
	/** The order single price. */
	@FXML
	private TableColumn<SupplierOrder, String> orderSinglePrice;
	
	/** The order amount. */
	@FXML
	private TableColumn<SupplierOrder, String> orderAmount;
	
	/** The order total price. */
	@FXML
	private TableColumn<SupplierOrder, String> orderTotalPrice;
	
	/** The order art. */
	@FXML
	private TableColumn<SupplierOrder, String> orderArt;
	
	/** The order tax. */
	@FXML
	private TableColumn<SupplierOrder, String> orderTax;
	
	/** The supplier. */
	private Supplier supplier;
	
	/** The main. */
	private Main main;
	
	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("description"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("statusString"));
		orderDate.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("itemNumb"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("sumPrice"));
		orderTax.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("tax"));
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	/**
	 * Refresh supplier order table.
	 */
	private void refreshSupplierOrderTable() {
		int selectedIndex = supplierOrderTable.getSelectionModel().getSelectedIndex();
		supplierOrderTable.setItems(null);
		supplierOrderTable.layout();
		supplierOrderTable.setItems(main.getSupplierOrderData());
		supplierOrderTable.getSelectionModel().select(selectedIndex);
	}
	
	/**
	 * Adds the supplier order.
	 */
	@FXML
	public void addSupplierOrder() {
		SupplierOrder supplierOrder = new SupplierOrder();
		boolean okClicked = main.showSupplierOrderEditDialog(supplierOrder);
		if (okClicked) {
			for(Supplier sup : SupplierUtil.loadAllSuppliers()){
				if(supplier.getId() == sup.getId()){
					sup.addOrder(supplierOrder);
				}
			}
			supplierOrder.setSupplier(supplier);
			SupplierOrderUtil.addOrder(supplierOrder);
			refreshSupplierOrderTable();
			main.addSupplierOrder(supplierOrder);
		}
	}
	
	/**
	 * Edits the supplier order.
	 */
	@FXML
	private void editSupplierOrder() {
		SupplierOrder supplierOrder = supplierOrderTable.getSelectionModel().getSelectedItem();
		if (supplierOrder != null) {
			boolean okClicked = main.showSupplierOrderEditDialog(supplierOrder);
			if (okClicked) {
				SupplierOrderUtil.editOrder(supplierOrder);
				refreshSupplierOrderTable();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Bitte wählen Sie eine Rechnung aus!");
			alert.showAndWait();
		}
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp the new main app
	 */
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		supplierOrderTable.setItems(main.getSupplierOrderData());
	}

	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Sets the supplier.
	 *
	 * @param supplier the new supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
