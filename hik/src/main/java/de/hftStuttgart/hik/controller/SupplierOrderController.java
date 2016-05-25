package de.hftStuttgart.hik.controller;

import java.io.IOException;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	private Supplier supplier;
	private Main main;
	
	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("description"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("itemNumb"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("sumPrice"));
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	private void refreshSupplierOrderTable() {
		int selectedIndex = supplierOrderTable.getSelectionModel().getSelectedIndex();
		supplierOrderTable.setItems(null);
		supplierOrderTable.layout();
		supplierOrderTable.setItems(SupplierOrderUtil.loadAllOrders(supplier));
		supplierOrderTable.getSelectionModel().select(selectedIndex);
	}
	
	@FXML
	public void addSupplierOrder() {
		SupplierOrder supplierOrder = new SupplierOrder();
		boolean okClicked = showOrderEditDialog(supplierOrder);
		if (okClicked) {
			SupplierUtil.loadAllSuppliers().get(supplier.getId().intValue()-1).addOrder(supplierOrder);
			supplierOrder.setSupplier(supplier);
			SupplierOrderUtil.addOrder(supplierOrder);
			refreshSupplierOrderTable();
		}
	}
	
	@FXML
	private void editSupplierOrder() {
		SupplierOrder supplierOrder = supplierOrderTable.getSelectionModel().getSelectedItem();
		if (supplierOrder != null) {
			boolean okClicked = showOrderEditDialog(supplierOrder);
			if (okClicked) {
				refreshSupplierOrderTable();
				SupplierOrderUtil.editOrder(supplierOrder);
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!");
			alert.setHeaderText("");
			alert.setContentText("Keine Bestellung ausgewählt!");
			alert.showAndWait();
		}
	}
	
	public boolean showOrderEditDialog(SupplierOrder supplierOrder) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/main/java/de/hftStuttgart/hik/view/SupplierOrderEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Bestellung hinzufuegen");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(main.getPrimaryStage());
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			SupplierOrderEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setSupplierOrder(supplierOrder);

			dialogStage.showAndWait();
			return controller.isOkClicked();

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
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
