package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import de.hftStuttgart.hik.utilities.SupplierUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;

public class CostsController {
	@FXML
	private TableView<SupplierOrder> supplierOrderTable;
	@FXML
	private TableColumn<SupplierOrder, String> orderNameSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderDescriptionSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderStatusSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderDateSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderSinglePriceSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderAmountSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderTotalPriceSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderArtSupplier;
	@FXML
	private TableColumn<SupplierOrder, String> orderTaxSupplier;
	@FXML
	private Label summe;
	@FXML
	private ComboBox<String> daysCombobox;
	@FXML
	private ComboBox<String> supplierCombobox;

	private ObservableList<SupplierOrder> supplierOrderListInTime = FXCollections.observableArrayList();
	private ObservableList<SupplierOrder> supplierOrderListInTimeAndSupplier = FXCollections.observableArrayList();
	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();
	private ObservableList<String> supplier = FXCollections.observableArrayList();
	private double summeCalc = 0;

	@FXML
	private void initialize() {
		setDaysCombobox();

		daysCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadSupplierOrders(t1);
			}
		});

		supplierCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrdersSupplier(t1);
			}
		});
		
		orderStatusSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("statusString"));
		orderDateSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("date"));
		orderTotalPriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("sumPrice"));
		orderNameSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("supplier"));
		orderArtSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("itemNumb"));
		orderDescriptionSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("description"));
		orderSinglePriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("unitPrice"));
		orderAmountSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("amount"));
		orderTaxSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("tax"));
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}
	
	public void setDaysCombobox(){
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage", "Alle"));
		daysCombobox.getSelectionModel().select(0);
	}

	public void loadOrdersSupplier(String comboValue) {
		supplierOrderListInTimeAndSupplier.clear();
		summeCalc = 0;

		for (SupplierOrder supOrder : supplierOrderListInTime) {
			if (String.valueOf(supOrder.getSupplierObject().getSupplierCompanyName()).equals(comboValue)
					|| comboValue.equals("Alle")) {
				supplierOrderListInTimeAndSupplier.add(supOrder);
			}
		}

		for (SupplierOrder supOrder : supplierOrderListInTimeAndSupplier) {
			summeCalc += supOrder.getSumPrice();
		}
		summe.setText(String.valueOf(Math.round(100.0 * summeCalc) / 100.0) + " Euro");
		supplierOrderTable.setItems(supplierOrderListInTimeAndSupplier);
	}

	public void setSupplierComboBox(int index) {
		for (SupplierOrder supOrder : supplierOrderListInTime) {
			String supplierName = "";
			for(Supplier sup : SupplierUtil.loadAllSuppliers()){
				if(supOrder.getSupplierObject().getId() == sup.getId()){
					 supplierName = supOrder.getSupplierObject().getSupplierCompanyName();
				}
			}
			if (!supplier.contains("Alle")) {
				supplier.add("Alle");
			}
			if (!supplier.contains(supplierName))
				supplier.add(supplierName);
		}
		supplierCombobox.setItems(supplier);
		supplierCombobox.getSelectionModel().select(index);
	}

	public void loadSupplierOrders(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);

		supplierOrderListInTime.clear();

		switch (comboValue) {
		case "10 Tage":
			for (SupplierOrder supOrder : supplierOrderList) {
				if (new LocalDateStringConverter().fromString(supOrder.getDate()).isAfter(local.minusDays(10)))
					supplierOrderListInTime.add(supOrder);
			}
			break;
		case "20 Tage":
			for (SupplierOrder supOrder : supplierOrderList) {
				if (new LocalDateStringConverter().fromString(supOrder.getDate()).isAfter(local.minusDays(20)))
					supplierOrderListInTime.add(supOrder);
			}
			break;
		case "30 Tage":
			for (SupplierOrder supOrder : supplierOrderList) {
				if (new LocalDateStringConverter().fromString(supOrder.getDate()).isAfter(local.minusDays(30)))
					supplierOrderListInTime.add(supOrder);
			}
			break;
		case "Alle":
			for (SupplierOrder supOrder : supplierOrderList) {
				supplierOrderListInTime.add(supOrder);
			}
			break;
		}
		if (supplierCombobox.getSelectionModel().getSelectedIndex() == -1)
			setSupplierComboBox(0);
		else
			setSupplierComboBox(supplierCombobox.getSelectionModel().getSelectedIndex());
		loadOrdersSupplier(supplierCombobox.getSelectionModel().getSelectedItem());
	}

	public void setMainApp(Main main) {
		supplierOrderList = SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded();
		loadSupplierOrders("10 Tage");
	}
}
