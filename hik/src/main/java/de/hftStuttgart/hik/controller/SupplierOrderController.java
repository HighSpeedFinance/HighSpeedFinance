package de.hftStuttgart.hik.controller;

import javax.persistence.PersistenceException;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.AlertUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierOrderController manages the views from SupplierOrder and is
 * responsible for handling user input and responses. The Methods load all
 * orders from the database, add new orders and edit orders.
 * 
 */
public class SupplierOrderController {

	@FXML
	private TableView<SupplierOrder> supplierOrderTable;

	@FXML
	private TableColumn<SupplierOrder, String> orderNumber;

	@FXML
	private TableColumn<SupplierOrder, String> orderDescription;

	@FXML
	private TableColumn<SupplierOrder, String> orderStatus;

	@FXML
	private TableColumn<SupplierOrder, String> orderDate;

	@FXML
	private TableColumn<SupplierOrder, String> orderSinglePrice;

	@FXML
	private TableColumn<SupplierOrder, String> orderAmount;

	@FXML
	private TableColumn<SupplierOrder, String> orderTotalPrice;

	@FXML
	private TableColumn<SupplierOrder, String> orderArt;

	@FXML
	private TableColumn<SupplierOrder, String> orderTax;

	private Supplier supplier;

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
	 * Adds the supplier order to the table
	 */
	@FXML
	public void addSupplierOrder() {
		SupplierOrder supplierOrder = new SupplierOrder();
		boolean okClicked = main.showSupplierOrderEditDialog(supplierOrder);
		if (okClicked) {
			try {
				for (Supplier sup : SupplierUtil.loadAllSuppliers()) {
					if (supplier.getId() == sup.getId()) {
						sup.addOrder(supplierOrder);
					}
				}
			} catch (PersistenceException e) {
				AlertUtil.noConnectionToDatabase();
			}
			supplierOrder.setSupplier(supplier);
			try {
				SupplierOrderUtil.addOrder(supplierOrder);
			} catch (PersistenceException e) {
				AlertUtil.noConnectionToDatabase();
			}
			refreshSupplierOrderTable();
			main.addSupplierOrder(supplierOrder);
		}
	}

	/**
	 * Edits the supplier order in the database
	 */
	@FXML
	private void editSupplierOrder() {
		SupplierOrder supplierOrder = supplierOrderTable.getSelectionModel().getSelectedItem();
		if (supplierOrder != null) {
			boolean okClicked = main.showSupplierOrderEditDialog(supplierOrder);
			if (okClicked) {
				try {
					SupplierOrderUtil.editOrder(supplierOrder);
				} catch (PersistenceException e) {
					AlertUtil.noConnectionToDatabase();
				}
				refreshSupplierOrderTable();
			}
		} else {
			AlertUtil.noSupplierOrderSelected();
		}
	}

	/**
	 * Sets the main app.
	 *
	 * @param mainApp
	 *            the new main app
	 */
	public void setMainApp(Main mainApp) {
		this.main = mainApp;
		supplierOrderTable.setItems(main.getSupplierOrderData());
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
