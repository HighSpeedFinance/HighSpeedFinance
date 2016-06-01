package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.LocalDateStringConverter;

public class ShowNewOrdersController {
	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	@FXML
	private TableColumn<CustomerOrder, String> orderStatus;
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	@FXML
	private TableColumn<CustomerOrder, String> customerNumber;
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
	private RadioButton radioDays;
	@FXML
	private RadioButton radioDate;
	@FXML
	private ComboBox<String> daysCombobox;
	@FXML
	private ToggleGroup zeitraum;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private RadioButton radioDaysSupplier;
	@FXML
	private RadioButton radioDateSupplier;
	@FXML
	private ComboBox<String> daysComboboxSupplier;
	@FXML
	private ToggleGroup zeitraumSupplier;
	@FXML
	private DatePicker startDateSupplier;
	@FXML
	private DatePicker endDateSupplier;
	@FXML
	private TabPane tabPane;

	private Main main;

	public void setDaysCombobox() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysCombobox.getSelectionModel().select(0);
	}

	public void setDaysComboboxSupplier() {
		daysComboboxSupplier.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysComboboxSupplier.getSelectionModel().select(0);
	}

	@FXML
	private void initialize() {
		setDaysCombobox();

		daysCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrders(t1);
			}
		});

		zeitraum.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysCombobox.getSelectionModel().select(0);
				loadOrders(daysCombobox.getSelectionModel().getSelectedItem());
			}
		});

		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("statusString"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		setDaysComboboxSupplier();
		
		daysComboboxSupplier.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadSupplierOrders(t1);
			}
		});

		zeitraumSupplier.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				daysComboboxSupplier.getSelectionModel().select(0);
				loadSupplierOrders(daysComboboxSupplier.getSelectionModel().getSelectedItem());
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
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public void setMainApp(Main main) {
		this.main = main;
		loadOrders("10 Tage");
	}

	@FXML
	public void loadOrders() {
		loadOrders(daysCombobox.getSelectionModel().getSelectedItem());
	}

	@FXML
	public void loadSupplierOrders() {
		loadSupplierOrders(daysComboboxSupplier.getSelectionModel().getSelectedItem());
	}

	public void loadSupplierOrders(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);

		ObservableList<SupplierOrder> supplierOrderList = main.getSupplierOrderData();
		ObservableList<SupplierOrder> supplierOrderListInTime = FXCollections.observableArrayList();
		if (radioDaysSupplier.isSelected()) {
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
			}
		} else if (radioDateSupplier.isSelected() && startDateSupplier.getValue() != null
				&& endDateSupplier.getValue() != null) {
			for (SupplierOrder supOrder : supplierOrderList) {
				if (new LocalDateStringConverter().fromString(supOrder.getDate()).isAfter(startDateSupplier.getValue())
						&& new LocalDateStringConverter().fromString(supOrder.getDate())
								.isBefore(endDateSupplier.getValue())
						|| new LocalDateStringConverter().fromString(supOrder.getDate())
								.isEqual(endDateSupplier.getValue())
						|| new LocalDateStringConverter().fromString(supOrder.getDate())
								.isEqual(startDateSupplier.getValue())) {
					supplierOrderListInTime.add(supOrder);
				}
			}
		}
		supplierOrderTable.setItems(supplierOrderListInTime);
	}

	public void loadOrders(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);

		ObservableList<CustomerOrder> customerOrderList = main.getCustomerOrderData();
		ObservableList<CustomerOrder> customerOrderListInTime = FXCollections.observableArrayList();
		if (radioDays.isSelected()) {
			switch (comboValue) {
			case "10 Tage":
				for (CustomerOrder cusOrder : customerOrderList) {
					if (new LocalDateStringConverter().fromString(cusOrder.getDate()).isAfter(local.minusDays(10)))
						customerOrderListInTime.add(cusOrder);
				}
				break;
			case "20 Tage":
				for (CustomerOrder cusOrder : customerOrderList) {
					if (new LocalDateStringConverter().fromString(cusOrder.getDate()).isAfter(local.minusDays(20)))
						customerOrderListInTime.add(cusOrder);
				}
				break;
			case "30 Tage":
				for (CustomerOrder cusOrder : customerOrderList) {
					if (new LocalDateStringConverter().fromString(cusOrder.getDate()).isAfter(local.minusDays(30)))
						customerOrderListInTime.add(cusOrder);
				}
				break;
			}
		} else if (radioDate.isSelected() && startDate.getValue() != null && endDate.getValue() != null) {
			for (CustomerOrder cusOrder : customerOrderList) {
				if (new LocalDateStringConverter().fromString(cusOrder.getDate()).isAfter(startDate.getValue())
						&& new LocalDateStringConverter().fromString(cusOrder.getDate()).isBefore(endDate.getValue())
						|| new LocalDateStringConverter().fromString(cusOrder.getDate()).isEqual(endDate.getValue())
						|| new LocalDateStringConverter().fromString(cusOrder.getDate())
								.isEqual(startDate.getValue())) {
					customerOrderListInTime.add(cusOrder);
				}
			}
		}
		customerOrderTable.setItems(customerOrderListInTime);
	}

	@FXML
	public void customerIsSelected() {
		try {
			main.showNavigationBarCustomer();
		} catch (Exception e) {
		}
	}

	@FXML
	public void supplierIsSelected() {
		main.showNavigationBarSupplier();
	}

	public void setTabSelected(int selection) {
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if (selection == 0) {
			selectionModel.select(0);
		} else {
			selectionModel.select(1);
		}
	}
}
