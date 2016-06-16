package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.PersistenceException;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.utilities.AlertUtil;
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

/**
 * The Class IncomeController manages the views from Income and is responsible
 * for handling user input and responses.
 * 
 */
public class IncomeController {

	@FXML
	private TableView<CustomerOrder> customerOrderTable;

	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;

	@FXML
	private TableColumn<CustomerOrder, String> orderDescription;

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
	private TableColumn<CustomerOrder, String> orderTax;

	@FXML
	private Label sumIncome;

	@FXML
	private ComboBox<String> daysCombobox;

	@FXML
	private ComboBox<String> plzCombobox;

	private ObservableList<CustomerOrder> customerOrderListInTime = FXCollections.observableArrayList();

	private ObservableList<CustomerOrder> customerOrderListInTimeAndPlz = FXCollections.observableArrayList();

	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();

	private ObservableList<String> plz = FXCollections.observableArrayList();

	private double summeCalc = 0;

	/**
	 * Sets the plz combo box. Selectable options are all orders or orders by an
	 * individul zip-code
	 *
	 * @param index
	 *            the new plz combo box
	 */
	public void setPlzComboBox(int index) {
		for (CustomerOrder cusOrder : customerOrderListInTime) {
			String plzInt = "";
			try {
				for (Customer cus : CustomerUtil.loadAllCustomers()) {
					if (cusOrder.getCustomerObject().getId() == cus.getId()) {
						plzInt = String.valueOf(cusOrder.getCustomerObject().getCustomerAdressPostIndex());
					}
				}
				if (!plz.contains("Alle")) {
					plz.add("Alle");
				}
				if (!plz.contains(plzInt))
					plz.add(plzInt);
			} catch (PersistenceException e) {
				AlertUtil.noConnectionToDatabase();
			}
		}
		plzCombobox.setItems(plz);
		plzCombobox.getSelectionModel().select(index);
	}

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		setDaysCombobox();

		daysCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrders(t1);
			}
		});

		plzCombobox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrdersPlz(t1);
			}
		});

		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderDescription.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("description"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		orderSinglePrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("unitPrice"));
		orderAmount.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("amount"));
		orderArt.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("itemNumb"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customerNumber"));
		orderTax.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("tax"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	/**
	 * Sets the days combobox.selectable options are 10 days, 20 days, 30 days
	 * or all
	 */
	public void setDaysCombobox() {
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage", "Alle"));
		daysCombobox.getSelectionModel().select(0);
	}

	/**
	 * Sets the main app.
	 *
	 * @param main
	 *            the new main app
	 */
	public void setMainApp(Main main) {
		try{
		customerOrderList = OrderUtil.loadAllOrdersWhereStatusSucceeded();
		} catch (PersistenceException e) {
			AlertUtil.noConnectionToDatabase();
		}
		loadOrders("10 Tage");
	}

	/**
	 * Loads all orders from an indiviual zip-code and calculates the total sum
	 *
	 * @param comboValue
	 *            the combo value
	 */
	public void loadOrdersPlz(String comboValue) {
		customerOrderListInTimeAndPlz.clear();
		summeCalc = 0;

		for (CustomerOrder cusOder : customerOrderListInTime) {
			if (String.valueOf(cusOder.getCustomerObject().getCustomerAdressPostIndex()).equals(comboValue)
					|| comboValue.equals("Alle")) {
				customerOrderListInTimeAndPlz.add(cusOder);
			}
		}

		for (CustomerOrder cusOrder : customerOrderListInTimeAndPlz) {
			summeCalc += cusOrder.getSumPrice();
		}
		sumIncome.setText(String.valueOf(Math.round(100.0 * summeCalc) / 100.0) + " Euro");
		customerOrderTable.setItems(customerOrderListInTimeAndPlz);
	}

	/**
	 * Loads all orders. selectable options are 10 days, 20 days, 30 days or all
	 * orders
	 *
	 * @param comboValue
	 *            the combo value
	 */
	public void loadOrders(String comboValue) {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);

		customerOrderListInTime.clear();

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
		if (plzCombobox.getSelectionModel().getSelectedIndex() == -1)
			setPlzComboBox(0);
		else
			setPlzComboBox(plzCombobox.getSelectionModel().getSelectedIndex());
		loadOrdersPlz(plzCombobox.getSelectionModel().getSelectedItem());
	}

	public double getSummeCalc() {
		return summeCalc;
	}

	public void setSummeCalc(double summeCalc) {
		this.summeCalc = summeCalc;
	}
}
