package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum SupplierOrderUtil {
	INSTANCE;
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	@SuppressWarnings("unchecked")
	public static ObservableList<SupplierOrder> loadAllOrders(Supplier sup) {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where supplier_id = " + sup.getId());
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}
	
	@SuppressWarnings("unchecked")
	public static ObservableList<SupplierOrder> loadAllOrders() {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t");
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}
	
	public static ObservableList<SupplierOrder> loadAllOrdersWhereStatusOpen() {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where status = 1 OR status=2");
		@SuppressWarnings("unchecked")
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}
	
	public static ObservableList<SupplierOrder> loadAllOrdersWhereStatusSucceeded() {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where status = 0");
		@SuppressWarnings("unchecked")
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}
	

	public static void addOrder(SupplierOrder ord) {
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
	}

	public static void editOrder(SupplierOrder ord) {
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
	}

	public static void deleteOrder(SupplierOrder ord) {
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
	}

}
