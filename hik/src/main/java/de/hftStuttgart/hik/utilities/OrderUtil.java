package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum OrderUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<CustomerOrder> loadAllOrders(Customer cus) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t where customer_id = " + cus.getId());
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	
	@SuppressWarnings("unchecked")
	public static ObservableList<CustomerOrder> loadAllOrders() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t");
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	
	public static ObservableList<CustomerOrder> loadAllOrdersWhereStatusOpen() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t where status = 1 OR status=2");
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}
	

	public static void addOrder(CustomerOrder ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
		em.close();
	}

	public static void editOrder(CustomerOrder ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteOrder(CustomerOrder ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
		em.close();
	}

}
