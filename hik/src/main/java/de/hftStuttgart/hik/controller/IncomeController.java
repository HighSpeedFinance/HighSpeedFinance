package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.utilities.CustomerUtil;
import de.hftStuttgart.hik.utilities.OrderUtil;
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

public class IncomeController {
	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	@FXML
	private TableColumn<CustomerOrder, String> orderDescription;
	@FXML
	private TableColumn<CustomerOrder, String> leer;
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	@FXML
	private TableColumn<CustomerOrder, String> orderSinglePrice;
	@FXML
	private TableColumn<CustomerOrder, String> orderAmount;
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	@FXML
	private TableColumn<CustomerOrder, String> orderArt;
	@FXML
	private TableColumn<CustomerOrder, String> customerNumber;
	@FXML
	private Label summe;
	@FXML
	private ComboBox<String> daysCombobox;
	@FXML
	private ComboBox<Integer> plzCombobox;

	private Main main;
	private ObservableList<CustomerOrder> customerOrderListInTime = FXCollections.observableArrayList();
	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();
	private ObservableList<Integer> plz = FXCollections.observableArrayList();

	public void setPlzComboBox(){
		for(CustomerOrder cusOrder : customerOrderList){
			int plzInt = CustomerUtil.loadAllCustomers().get((int) cusOrder.getCustomer()).getCustomerPostalCode();
			if(!plz.contains(plzInt))
			plz.add(plzInt);
		}
		plzCombobox.setItems(plz);
		plzCombobox.getSelectionModel().select(0);
	}
	
	@FXML
	private void initialize() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage", "Alle"));
		daysCombobox.getSelectionModel().select(0);
		
		daysCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrders(t1);
			}
		});

		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		leer.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("supId"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("itemNumb"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public void setMainApp(Main main) {
		this.main = main;
		loadOrders("10 Tage");
	}

	public void loadOrders(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);
		double summeCalc = 0;

		customerOrderListInTime.clear();
		customerOrderList = OrderUtil.loadAllOrdersWhereStatusSucceeded();
		setPlzComboBox();
		
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
		case "Alle":
			for (CustomerOrder cusOrder : customerOrderList) {
				customerOrderListInTime.add(cusOrder);
			}
			break;
		}
		for (CustomerOrder cusOrder : customerOrderListInTime) {
			summeCalc += cusOrder.getSumPrice();
		}
		summe.setText(String.valueOf(summeCalc) + " €");
		customerOrderTable.setItems(customerOrderListInTime);
	}
}
