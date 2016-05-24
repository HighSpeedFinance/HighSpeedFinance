package de.hftStuttgart.hik.controller;

import de.hftStuttgart.hik.application.Main;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.OrderUtil;
import de.hftStuttgart.hik.utilities.SupplierOrderUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OpenCustomerAndSupplierOrders {
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
	private TabPane tabPane;

	private Main main;

	public void setMainApp(Main main) {
		this.main = main;
	}
	
	public void loadCustomerOrders(){
		ObservableList<CustomerOrder> customerOrderList = OrderUtil.loadAllOrdersWhereStatusOpen();
		customerOrderTable.setItems(customerOrderList);
	}
	
	public void loadSupplierOrders(){
		ObservableList<SupplierOrder> supplierOrderList = SupplierOrderUtil.loadAllOrdersWhereStatusOpen();
		supplierOrderTable.setItems(supplierOrderList);
	}

	@FXML
	private void initialize() {
		orderNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("orderNumber"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("status"));
		orderDate.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("date"));
		customerNumber.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("customer"));
		orderTotalPrice.setCellValueFactory(new PropertyValueFactory<CustomerOrder, String>("sumPrice"));
		customerOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		loadCustomerOrders();
		
		orderNameSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("supplier"));
		orderDescriptionSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("description"));
		orderStatusSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("status"));
		orderDateSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("date"));
		orderSinglePriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("unitPrice"));
		orderAmountSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("amount"));
		orderArtSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("itemNumb"));
		orderTotalPriceSupplier.setCellValueFactory(new PropertyValueFactory<SupplierOrder, String>("sumPrice"));
		supplierOrderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		loadSupplierOrders();
	}

	@FXML
	public void addSupplierOrder(){
		
	}
	
	@FXML
	public void editSupplierOrder(){
		
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
	
	public void setTabSelected(int selection){
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		if(selection == 0){
			selectionModel.select(0);
		}else{
			selectionModel.select(1);
		}
	}
}
