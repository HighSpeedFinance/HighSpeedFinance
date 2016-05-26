package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GraphicsController {

	@FXML
	private Label resultIncomeCost;
	@FXML
	private ListView<SupplierOrder>cost;
	@FXML
	private ListView<CustomerOrder>income;
	
	@SuppressWarnings("rawtypes")
	@FXML
	private BarChart barChart;
	private ObservableList<CustomerOrder> customerOrderList = FXCollections.observableArrayList();
	private ObservableList<SupplierOrder> supplierOrderList = FXCollections.observableArrayList();
	private double summeEinnahmen = 0;
	private double summeAusgaben = 0;


	@FXML
	private void initialize() {
	}

	
	@SuppressWarnings("unchecked")
	private void loadBarChart() {
		new CategoryAxis();
		new NumberAxis();
		
		for(CustomerOrder customerOrder : customerOrderList){
			summeEinnahmen += customerOrder.getSumPrice();
		}
		for(SupplierOrder supplierOrder: supplierOrderList){
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

	public void setMainApp(Main main) {
		customerOrderList.addAll(OrderUtil.loadAllOrdersWhereStatusSucceeded());
		supplierOrderList.addAll(SupplierOrderUtil.loadAllOrdersWhereStatusSucceeded());
		
		cost.setItems(supplierOrderList);
		income.setItems(customerOrderList);
		loadBarChart();
		resultIncomeCost.setText(String.valueOf(Math.round(100.0 * (summeEinnahmen-summeAusgaben)) / 100.0));
	}

}
