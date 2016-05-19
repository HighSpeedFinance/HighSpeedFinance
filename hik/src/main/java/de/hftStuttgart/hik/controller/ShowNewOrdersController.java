package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
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

	private Main main;

	@FXML
	private void initialize() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage"));
		daysCombobox.getSelectionModel().select(0);

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
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public void setMainApp(Main main) {
		this.main = main;
		loadOrders("10 Tage");
	}

	@FXML
	public void loadOrders() {
		loadOrders(daysCombobox.getSelectionModel().getSelectedItem());
	}

	public void loadOrders(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);

		ObservableList<CustomerOrder> customerOrderList = main.getCustomerOrderData(null);
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
						|| new LocalDateStringConverter().fromString(cusOrder.getDate()).isEqual(startDate.getValue())) {
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
}
