package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum SupplierOrderUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<SupplierOrder> loadAllOrders(Supplier sup) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where supplier_id = " + sup.getId());
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	
	@SuppressWarnings("unchecked")
	public static ObservableList<SupplierOrder> loadAllOrders() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t");
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	
	public static ObservableList<SupplierOrder> loadAllOrdersWhereStatusOpen() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where status = 1 OR status=2");
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	
	public static ObservableList<SupplierOrder> loadAllOrdersWhereStatusSucceeded() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where status = 0");
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	

	public static void addOrder(SupplierOrder ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
		em.close();
	}

	public static void editOrder(SupplierOrder ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteOrder(SupplierOrder ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
		em.close();
	}

}
