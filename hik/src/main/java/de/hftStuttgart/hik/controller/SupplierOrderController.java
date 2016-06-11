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
	
	private void refreshSupplierOrderTable() {
		int selectedIndex = supplierOrderTable.getSelectionModel().getSelectedIndex();
		supplierOrderTable.setItems(null);
		supplierOrderTable.layout();
		supplierOrderTable.setItems(main.getSupplierOrderData());
		supplierOrderTable.getSelectionModel().select(selectedIndex);
	}
	
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
