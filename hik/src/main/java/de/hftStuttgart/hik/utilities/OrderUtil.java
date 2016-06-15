package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Enum OrderUtil.
 */
public enum OrderUtil {
	
	/** The instance. */
	INSTANCE;
	
	/** The em. */
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Load all orders.
	 *
	 * @param cus the cus
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
	 * Load all orders.
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
	 * Load all orders where status open.
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
	 * Load all orders where status succeeded.
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
	 * Adds the order.
	 *
	 * @param ord the ord
	 */
	public static void addOrder(CustomerOrder ord) {
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
	}

	/**
	 * Edits the order.
	 *
	 * @param ord the ord
	 */
	public static void editOrder(CustomerOrder ord) {
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
	}

	/**
	 * Delete order.
	 *
	 * @param ord the ord
	 */
	public static void deleteOrder(CustomerOrder ord) {
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
	}

}
