package de.hftStuttgart.hik.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.regex.Pattern;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.converter.LocalDateStringConverter;

/**
 * The Class AnnualAccountsController manages the views from AnnualAccounts and
 * is responsible for handling user input and responses. The Methods load all
 * SupplierOrders and CustomerOrders where the Status is "SUCCEEDED" and
 * calculate the balance of Income and Costs. The results are shown in a graphic
 * and selectable by months.
 */
public class AnnualAccountsController {

	@FXML
	private Label resultIncomeCost;

	@FXML
	private ListView<SupplierOrder> cost;

	@FXML
	private ListView<CustomerOrder> income;

	@FXML
	private ChoiceBox<String> monthChoiceBox;

	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart barChart;

	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();

	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();

	private ObservableList<CustomerOrder> customerOrderListinTime = FXCollections.observableArrayList();

	private ObservableList<SupplierOrder> supplierOrderListinTime = FXCollections.observableArrayList();

	private ObservableList<CustomerOrder> customerOrderListMonth = FXCollections.observableArrayList();

	private ObservableList<SupplierOrder> supplierOrderListMonth = FXCollections.observableArrayList();

	private double sumIncome = 0;

	private double sumCosts = 0;

	/**
	 * Initialize.
	 */
	@FXML
	private void initialize() {
		setMonthChoiceBox();
		monthChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrdersMonth(t1);
			}
		});
	}

	/**
	 * Sets the month choice box. Selectable options are: all month or each
	 * individual month.
	 */
	public void setMonthChoiceBox() {
		monthChoiceBox.setItems(FXCollections.observableArrayList("Alle", "Januar", "Februar", "M�rz", "April", "Mai",
				"Juni", "Juli", "August", "September", "Oktober", "November", "Dezember"));
		monthChoiceBox.getSelectionModel().select(0);
	}

	/**
	 * Loads the bar chart. Sets the bar charts to the amount of the values
	 * Income and Costs
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void loadBarChart() {
		new CategoryAxis();
		new NumberAxis();

		for (CustomerOrder customerOrder : customerOrderListMonth) {
			sumIncome += customerOrder.getSumPrice();
		}
		for (SupplierOrder supplierOrder : supplierOrderListMonth) {
			sumCosts += supplierOrder.getSumPrice();
		}

		ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
		final BarChart.Series<String, Number> series1 = new BarChart.Series<String, Number>();
		series1.setName("Jahresabschluss");
		series1.getData().add(new XYChart.Data<String, Number>("Einnahmen", sumIncome));
		series1.getData().add(new XYChart.Data<String, Number>("Ausgaben", sumCosts));
		barChartData.add(series1);
		barChart.setData(barChartData);
	}

	/**
	 * Sets the result income label. if the balance is a negativ number the
	 * result ist shown in the color red, else it is shown in the color green
	 */
	public void setResultIncomeLabel() {
		double result = Math.round(100.0 * (sumIncome - sumCosts)) / 100.0;
		resultIncomeCost.setText(String.valueOf(result));
		if (result < 0) {
			resultIncomeCost.setTextFill(Color.RED);
		} else {
			resultIncomeCost.setTextFill(Color.GREEN);
		}
	}

	/**
	 * Sets the main app. Loads all SupplierOrders and CustomerOrders where the
	 * Status is "SUCCEEDED"
	 *
	 * @param main
	 *            the new main app
	 */
	public void setMainApp(Main main) {
		customerOrderList.addAll(OrderUtil.loadAllOrdersWhereStatusSucceeded());
		supplierOrderList.addAll(SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded());

		setListInTime();

		loadOrdersMonth(monthChoiceBox.getSelectionModel().getSelectedItem());
	}

	/**
	 * Sets the list in time.
	 * 
	 */
	public void setListInTime() {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		LocalDate local = LocalDate.now(zone1);

		customerOrderListinTime.clear();
		supplierOrderListinTime.clear();

		for (CustomerOrder cusOrder : customerOrderList) {
			if (new LocalDateStringConverter().fromString(cusOrder.getDate())
					.isAfter(LocalDate.of(local.getYear(), 1, 1))
					&& new LocalDateStringConverter().fromString(cusOrder.getDate())
							.isBefore(LocalDate.of(local.getYear(), 12, 31)))
				customerOrderListinTime.add(cusOrder);
		}

		for (SupplierOrder supOrder : supplierOrderList) {
			if (new LocalDateStringConverter().fromString(supOrder.getDate())
					.isAfter(LocalDate.of(local.getYear(), 1, 1))
					&& new LocalDateStringConverter().fromString(supOrder.getDate())
							.isBefore(LocalDate.of(local.getYear(), 12, 31)))
				supplierOrderListinTime.add(supOrder);
		}
	}

	/**
	 * Load orders month. loads the orders for each individual month
	 *
	 * @param comboValue
	 *            the combo value
	 * 
	 * 
	 */
	public void loadOrdersMonth(String comboValue) {
		String month = "";
		customerOrderListMonth.clear();
		supplierOrderListMonth.clear();
		sumIncome = 0;
		sumCosts = 0;

		switch (comboValue) {
		case "Januar":
			month = "01";
			break;
		case "Februar":
			month = "02";
			break;
		case "M�rz":
			month = "03";
			break;
		case "April":
			month = "04";
			break;
		case "Mai":
			month = "05";
			break;
		case "Juni":
			month = "06";
			break;
		case "Juli":
			month = "07";
			break;
		case "August":
			month = "08";
			break;
		case "September":
			month = "09";
			break;
		case "Oktober":
			month = "10";
			break;
		case "November":
			month = "11";
			break;
		case "Dezember":
			month = "12";
			break;
		}

		for (CustomerOrder cusOder : customerOrderListinTime) {
			if (cusOder.getDate().split(Pattern.quote("."))[1].equals(month) || comboValue.equals("Alle")) {
				customerOrderListMonth.add(cusOder);
			}
		}

		for (CustomerOrder cusOrder : customerOrderListMonth) {
			sumIncome += cusOrder.getSumPrice();
		}

		for (SupplierOrder supOrder : supplierOrderListinTime) {
			if (supOrder.getDate().split(Pattern.quote("."))[1].equals(month) || comboValue.equals("Alle")) {
				supplierOrderListMonth.add(supOrder);
			}
		}

		for (SupplierOrder supOrder : supplierOrderListMonth) {
			sumCosts += supOrder.getSumPrice();
		}

		income.setItems(customerOrderListMonth);
		cost.setItems(supplierOrderListMonth);

		setResultIncomeLabel();
		loadBarChart();
	}
}
