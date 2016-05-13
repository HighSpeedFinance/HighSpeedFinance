package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import de.hftStuttgart.hik.model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum OrderUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<Order> loadAllOrders() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<Order> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from Order t");
		List<Order> orderList = q.getResultList();
		for (Order ord : orderList) {
			ordList.add(ord);
		}
		em.close();
		return ordList;
	}

	public static void addOrder(Order ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
		em.close();
	}

	public static void editOrder(Order ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteOrder(Order ord) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
		em.close();
	}

}
