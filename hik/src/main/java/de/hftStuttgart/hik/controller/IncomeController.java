package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
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

// TODO: Auto-generated Javadoc
/**
 * The Class IncomeController.
 */
public class IncomeController {
	
	/** The customer order table. */
	@FXML
	private TableView<CustomerOrder> customerOrderTable;
	
	/** The order number. */
	@FXML
	private TableColumn<CustomerOrder, String> orderNumber;
	
	/** The order description. */
	@FXML
	private TableColumn<CustomerOrder, String> orderDescription;
	
	/** The order date. */
	@FXML
	private TableColumn<CustomerOrder, String> orderDate;
	
	/** The order single price. */
	@FXML
	private TableColumn<CustomerOrder, String> orderSinglePrice;
	
	/** The order amount. */
	@FXML
	private TableColumn<CustomerOrder, String> orderAmount;
	
	/** The order total price. */
	@FXML
	private TableColumn<CustomerOrder, String> orderTotalPrice;
	
	/** The order art. */
	@FXML
	private TableColumn<CustomerOrder, String> orderArt;
	
	/** The customer number. */
	@FXML
	private TableColumn<CustomerOrder, String> customerNumber;
	
	/** The order tax. */
	@FXML
	private TableColumn<CustomerOrder, String> orderTax;
	
	/** The summe. */
	@FXML
	private Label summe;
	
	/** The days combobox. */
	@FXML
	private ComboBox<String> daysCombobox;
	
	/** The plz combobox. */
	@FXML
	private ComboBox<String> plzCombobox;

	/** The customer order list in time. */
	private ObservableList<CustomerOrder> customerOrderListInTime = FXCollections.observableArrayList();
	
	/** The customer order list in time and plz. */
	private ObservableList<CustomerOrder> customerOrderListInTimeAndPlz = FXCollections.observableArrayList();
	
	/** The customer order list. */
	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();
	
	/** The plz. */
	private ObservableList<String> plz = FXCollections.observableArrayList();
	
	/** The summe calc. */
	private double summeCalc = 0;

	/**
	 * Sets the plz combo box.
	 *
	 * @param index the new plz combo box
	 */
	public void setPlzComboBox(int index) {
		for (CustomerOrder cusOrder : customerOrderListInTime) {
			String plzInt = "";
			for(Customer cus : CustomerUtil.loadAllCustomers()){
				if(cusOrder.getCustomerObject().getId() == cus.getId()){
					 plzInt = String.valueOf(cusOrder.getCustomerObject().getCustomerAdressPostIndex());
				}
			}
			if (!plz.contains("Alle")) {
				plz.add("Alle");
			}
			if (!plz.contains(plzInt))
				plz.add(plzInt);
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
	 * Sets the days combobox.
	 */
	public void setDaysCombobox(){
		daysCombobox.setItems(FXCollections.observableArrayList("10 Tage", "20 Tage", "30 Tage", "Alle"));
		daysCombobox.getSelectionModel().select(0);
	}

	/**
	 * Sets the main app.
	 *
	 * @param main the new main app
	 */
	public void setMainApp(Main main) {
		customerOrderList = OrderUtil.loadAllOrdersWhereStatusSucceeded();
		loadOrders("10 Tage");
	}

	/**
	 * Load orders plz.
	 *
	 * @param comboValue the combo value
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
		summe.setText(String.valueOf(summeCalc) + " Euro");
		customerOrderTable.setItems(customerOrderListInTimeAndPlz);
	}

	/**
	 * Load orders.
	 *
	 * @param comboValue the combo value
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

	/**
	 * Gets the summe calc.
	 *
	 * @return the summe calc
	 */
	public double getSummeCalc() {
		return summeCalc;
	}

	/**
	 * Sets the summe calc.
	 *
	 * @param summeCalc the new summe calc
	 */
	public void setSummeCalc(double summeCalc) {
		this.summeCalc = summeCalc;
	}
}
