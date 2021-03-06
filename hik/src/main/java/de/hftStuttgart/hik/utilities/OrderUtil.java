package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Enum OrderUtil gets the actual connection to the DatabaseConnection and
 * instaciates the EntityManager. The CustomerOrder has a ManyToOne Relationship
 * to Customer. The Customer has to be instaciated before adding a Order
 */
public enum OrderUtil {

	INSTANCE;

	/** The em. */
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Load all orders from the Database
	 *
	 * @param cus
	 *            the Customer
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<CustomerOrder> loadAllOrders(Customer cus) {
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t where customer_id = " + cus.getId());
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Load all orders from the Database
	 *
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<CustomerOrder> loadAllOrders() {
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t");
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Load all orders from the Database where status open.
	 *
	 * @return the observable list
	 */
	public static ObservableList<CustomerOrder> loadAllOrdersWhereStatusOpen() {
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t where status = 1 OR status=2");
		@SuppressWarnings("unchecked")
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Load all orders from the Database where status succeeded.
	 *
	 * @return the observable list
	 */
	public static ObservableList<CustomerOrder> loadAllOrdersWhereStatusSucceeded() {
		ObservableList<CustomerOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from CustomerOrder t where status = 0");
		@SuppressWarnings("unchecked")
		List<CustomerOrder> orderList = q.getResultList();
		for (CustomerOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Adds the order to the Database
	 *
	 * @param ord
	 *            the Order
	 */
	public static void addOrder(CustomerOrder ord) {
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
	}

	/**
	 * Edits the order in the Database
	 *
	 * @param ord
	 *            the Order
	 */
	public static void editOrder(CustomerOrder ord) {
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
	}

	/**
	 * Delete order in the Database
	 *
	 * @param ord
	 *            the Order
	 */
	public static void deleteOrder(CustomerOrder ord) {
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
	}

}
