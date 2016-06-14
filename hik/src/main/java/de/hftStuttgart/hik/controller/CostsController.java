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

// TODO: Auto-generated Javadoc
/**
 * The Class CostsController.
 */
public class CostsController {
	
	/** The supplier order table. */
	@FXML
	private TableView<SupplierOrder> supplierOrderTable;
	
	/** The order name supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderNameSupplier;
	
	/** The order description supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderDescriptionSupplier;
	
	/** The order status supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderStatusSupplier;
	
	/** The order date supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderDateSupplier;
	
	/** The order single price supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderSinglePriceSupplier;
	
	/** The order amount supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderAmountSupplier;
	
	/** The order total price supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderTotalPriceSupplier;
	
	/** The order art supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderArtSupplier;
	
	/** The order tax supplier. */
	@FXML
	private TableColumn<SupplierOrder, String> orderTaxSupplier;
	
	/** The summe. */
	@FXML
	private Label summe;
	
	/** The days combobox. */
	@FXML
	private ComboBox<String> daysCombobox;
	
	/** The supplier combobox. */
	@FXML
	private ComboBox<String> supplierCombobox;

	/** The supplier order list in time. */
	private ObservableList<SupplierOrder> supplierOrderListInTime = FXCollections.observableArrayList();
	
	/** The supplier order list in time and supplier. */
	private ObservableList<SupplierOrder> supplierOrderListInTimeAndSupplier = FXCollections.observableArrayList();
	
	/** The supplier order list. */
	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();
	
	/** The supplier. */
	private ObservableList<String> supplier = FXCollections.observableArrayList();
	
	/** The summe calc. */
	private double summeCalc = 0;

	/**
	 * Initialize.
	 */
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
	
	/**
	 * Sets the days combobox.
	 */
	public void setDaysCombobox(){
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage", "Alle"));
		daysCombobox.getSelectionModel().select(0);
	}

	/**
	 * Load orders supplier.
	 *
	 * @param comboValue the combo value
	 */
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

	/**
	 * Sets the supplier combo box.
	 *
	 * @param index the new supplier combo box
	 */
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

	/**
	 * Load supplier orders.
	 *
	 * @param comboValue the combo value
	 */
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

	/**
	 * Sets the main app.
	 *
	 * @param main the new main app
	 */
	public void setMainApp(Main main) {
		supplierOrderList = SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded();
		loadSupplierOrders("10 Tage");
	}
}
