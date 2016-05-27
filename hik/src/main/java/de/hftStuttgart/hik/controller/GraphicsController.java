package de.hftStuttgart.hik.controller;

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

public class GraphicsController {

	@FXML
	private Label resultIncomeCost;
	@FXML
	private ListView<SupplierOrder> cost;
	@FXML
	private ListView<CustomerOrder> income;
	@FXML
	private ChoiceBox<String> plzChoiceBox;

	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart barChart;
	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();
	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();
	private ObservableList<CustomerOrder> customerOrderListPlz = FXCollections.observableArrayList();
	private ObservableList<SupplierOrder> supplierOrderListPlz = FXCollections.observableArrayList();
	private ObservableList<String> plz = FXCollections.observableArrayList();
	private double summeEinnahmen = 0;
	private double summeAusgaben = 0;
	private Main main;

	@FXML
	private void initialize() {
		plzChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				loadOrdersPlz(t1);
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void loadBarChart() {
		new CategoryAxis();
		new NumberAxis();

		for (CustomerOrder customerOrder : customerOrderListPlz) {
			summeEinnahmen += customerOrder.getSumPrice();
		}
		for (SupplierOrder supplierOrder : supplierOrderListPlz) {
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

	public void setResultIncomeLabel() {
		double result = Math.round(100.0 * (summeEinnahmen - summeAusgaben)) / 100.0;
		resultIncomeCost.setText(String.valueOf(result));
		if (result < 0) {
			resultIncomeCost.setTextFill(Color.RED);
		}
	}

	public void setMainApp(Main main) {
		this.main = main;
		customerOrderList.addAll(OrderUtil.loadAllOrdersWhereStatusSucceeded());
		supplierOrderList.addAll(SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded());

		cost.setItems(supplierOrderList);
		income.setItems(customerOrderList);
		loadBarChart();
		setResultIncomeLabel();
		if (plzChoiceBox.getSelectionModel().getSelectedIndex() == -1)
			setPlzComboBox(0);
		else
			setPlzComboBox(plzChoiceBox.getSelectionModel().getSelectedIndex());
		loadOrdersPlz(plzChoiceBox.getSelectionModel().getSelectedItem());
	}

	public void setPlzComboBox(int index) {
		for (CustomerOrder cusOrder : customerOrderList) {
			String plzInt = String
					.valueOf(main.getCustomerData().get((int) cusOrder.getCustomer() - 1).getCustomerAdressPostIndex());
			if (!plz.contains("Alle")) {
				plz.add("Alle");
			}
			if (!plz.contains(plzInt))
				plz.add(plzInt);
		}
		for(SupplierOrder supOrder : supplierOrderList){
			String plzInt = String
					.valueOf(main.getSupplierData().get((int) supOrder.getSupplierId() - 1).getSupplierAdressPostIndex());
			if (!plz.contains("Alle")) {
				plz.add("Alle");
			}
			if (!plz.contains(plzInt))
				plz.add(plzInt);
		}
		plzChoiceBox.setItems(plz);
		plzChoiceBox.getSelectionModel().select(index);
	}

	public void loadOrdersPlz(String comboValue) {
		customerOrderListPlz.clear();
		supplierOrderListPlz.clear();
		summeEinnahmen = 0;
		summeAusgaben = 0;

		for (CustomerOrder cusOder : customerOrderList) {
			if (String.valueOf(cusOder.getCustomerObject().getCustomerAdressPostIndex()).equals(comboValue)
					|| comboValue.equals("Alle")) {
				customerOrderListPlz.add(cusOder);
			}
		}

		for (CustomerOrder cusOrder : customerOrderListPlz) {
			summeEinnahmen += cusOrder.getSumPrice();
		}
		
		for (SupplierOrder supOrder : supplierOrderList) {
			if (String.valueOf(supOrder.getSupplierObject().getSupplierAdressPostIndex()).equals(comboValue)
					|| comboValue.equals("Alle")) {
				supplierOrderListPlz.add(supOrder);
			}
		}

		for (SupplierOrder supOrder : supplierOrderListPlz) {
			summeAusgaben += supOrder.getSumPrice();
		}
		
		income.setItems(customerOrderListPlz);
		cost.setItems(supplierOrderListPlz);
		
		setResultIncomeLabel();
		loadBarChart();
	}
}
