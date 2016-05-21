package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GraphicsController {

	@FXML
	private BarChart barChart;
	private Main main;

	@FXML
	private void initialize() {

	}

	public void setMainApp(Main main) {
		this.main = main;
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
		final BarChart.Series<String, Number> series1 = new BarChart.Series<String, Number>();
		series1.setName("Einnahmen");
		for (Customer cus : main.getCustomerData()) {
			if (!series1.getData().contains(cus.getCustomerPostalCode()))

				series1.getData()
						.add(new XYChart.Data<String, Number>(String.valueOf(cus.getCustomerPostalCode()), 222));
		}

		barChartData.add(series1);
		barChart.setData(barChartData);
	}
}
