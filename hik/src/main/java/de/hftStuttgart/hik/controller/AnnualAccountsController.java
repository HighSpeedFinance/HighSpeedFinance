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

// TODO: Auto-generated Javadoc
/**
 * The Class AnnualAccountsController.
 */
public class AnnualAccountsController {

	/** The result income cost. */
	@FXML
	private Label resultIncomeCost;
	
	/** The cost. */
	@FXML
	private ListView<SupplierOrder> cost;
	
	/** The income. */
	@FXML
	private ListView<CustomerOrder> income;
	
	/** The month choice box. */
	@FXML
	private ChoiceBox<String> monthChoiceBox;

	/** The bar chart. */
	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart barChart;
	
	/** The customer order list. */
	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();
	
	/** The supplier order list. */
	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();
	
	/** The customer order listin time. */
	private ObservableList<CustomerOrder> customerOrderListinTime = FXCollections.observableArrayList();
	
	/** The supplier order listin time. */
	private ObservableList<SupplierOrder> supplierOrderListinTime = FXCollections.observableArrayList();
	
	/** The customer order list month. */
	private ObservableList<CustomerOrder> customerOrderListMonth = FXCollections.observableArrayList();
	
	/** The supplier order list month. */
	private ObservableList<SupplierOrder> supplierOrderListMonth = FXCollections.observableArrayList();
	
	/** The summe einnahmen. */
	private double summeEinnahmen = 0;
	
	/** The summe ausgaben. */
	private double summeAusgaben = 0;
	
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
	 * Sets the month choice box.
	 */
	public void setMonthChoiceBox(){
		monthChoiceBox.setItems(FXCollections.observableArrayList("Alle", "Januar", "Februar", "März", "April", "Mai", "Juni","Juli","August","September", "Oktober", "November", "Dezember"));
		monthChoiceBox.getSelectionModel().select(0);
	}
	
	/**
	 * Load bar chart.
	 */
	@SuppressWarnings("unchecked")
	private void loadBarChart() {
		new CategoryAxis();
		new NumberAxis();

		for (CustomerOrder customerOrder : customerOrderListMonth) {
			summeEinnahmen += customerOrder.getSumPrice();
		}
		for (SupplierOrder supplierOrder : supplierOrderListMonth) {
			summeAusgaben += supplierOrder.getSumPrice();
		}

		ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
		final BarChart.Series<String, Number> series1 = new BarChart.Series<String, Number>();
		series1.setName("Jahresabschluss");
		series1.getData().add(new XYChart.Data<String, Number>("Einnahmen", summeEinnahmen));
		series1.getData().add(new XYChart.Data<String, Number>("Ausgaben", summeAusgaben));
		barChartData.add(series1);
		barChart.setData(barChartData);
	}

	/**
	 * Sets the result income label.
	 */
	public void setResultIncomeLabel() {
		double result = Math.round(100.0 * (summeEinnahmen - summeAusgaben)) / 100.0;
		resultIncomeCost.setText(String.valueOf(result));
		if (result < 0) {
			resultIncomeCost.setTextFill(Color.RED);
		}else{
			resultIncomeCost.setTextFill(Color.GREEN);
		}
	}

	/**
	 * Sets the main app.
	 *
	 * @param main the new main app
	 */
	public void setMainApp(Main main) {
		customerOrderList.addAll(OrderUtil.loadAllOrdersWhereStatusSucceeded());
		supplierOrderList.addAll(SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded());

		setListInTime();

		loadOrdersMonth(monthChoiceBox.getSelectionModel().getSelectedItem());
	}

	/**
	 * Sets the list in time.
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
	 * Load orders month.
	 *
	 * @param comboValue the combo value
	 */
	public void loadOrdersMonth(String comboValue) {
		String month = "";
		customerOrderListMonth.clear();
		supplierOrderListMonth.clear();
		summeEinnahmen = 0;
		summeAusgaben = 0;

		switch(comboValue){
		case "Januar": month = "01";
		break;
		case "Februar": month = "02";
		break;
		case "März": month = "03";
		break;
		case "April": month = "04";
		break;
		case "Mai": month = "05";
		break;
		case "Juni": month = "06";
		break;
		case "Juli": month = "07";
		break;
		case "August": month = "08";
		break;
		case "September": month = "09";
		break;
		case "Oktober": month = "10";
		break;
		case "November": month = "11";
		break;
		case "Dezember": month = "12";
		break;
		}
		
		for (CustomerOrder cusOder : customerOrderListinTime) {
			if (cusOder.getDate().split(Pattern.quote("."))[1].equals(month)
					|| comboValue.equals("Alle")) {
				customerOrderListMonth.add(cusOder);
			}
		}

		for (CustomerOrder cusOrder : customerOrderListMonth) {
			summeEinnahmen += cusOrder.getSumPrice();
		}

		for (SupplierOrder supOrder : supplierOrderListinTime) {
			if (supOrder.getDate().split(Pattern.quote("."))[1].equals(month)
					|| comboValue.equals("Alle")) {
				supplierOrderListMonth.add(supOrder);
			}
		}

		for (SupplierOrder supOrder : supplierOrderListMonth) {
			summeAusgaben += supOrder.getSumPrice();
		}

		income.setItems(customerOrderListMonth);
		cost.setItems(supplierOrderListMonth);

		setResultIncomeLabel();
		loadBarChart();
	}
}
