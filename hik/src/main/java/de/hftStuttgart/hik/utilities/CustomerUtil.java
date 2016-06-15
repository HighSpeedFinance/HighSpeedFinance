package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Enum CustomerUtil.
 */
public enum CustomerUtil {
	
	/** The instance. */
	INSTANCE;
	
	/** The em. */
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Load all customers.
	 *
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<Customer> loadAllCustomers() {
		ObservableList<Customer> custList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from Customer t");
		List<Customer> customerList = q.getResultList();
		for (Customer cust : customerList) {
			custList.add(cust);
		}
		return custList;
	}

	/**
	 * Adds the customer.
	 *
	 * @param cust the cust
	 */
	public static void addCustomer(Customer cust) {
		em.getTransaction().begin();
		em.persist(cust);
		em.getTransaction().commit();
	}

	/**
	 * Edits the customer.
	 *
	 * @param cust the cust
	 */
	public static void editCustomer(Customer cust) {
		em.getTransaction().begin();
		em.merge(cust);
		em.getTransaction().commit();
	}

	/**
	 * Delete customer.
	 *
	 * @param cust the cust
	 */
	public static void deleteCustomer(Customer cust) {
		em.getTransaction().begin();
		em.remove(em.merge(cust));
		em.getTransaction().commit();
	}
}