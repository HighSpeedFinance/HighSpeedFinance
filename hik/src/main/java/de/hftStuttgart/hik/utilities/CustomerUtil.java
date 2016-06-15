package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * The Enum CustomerUtil gets the actual connection to the DatabaseConnection and instanciates the
 * EntityManager
 * 
 */
public enum CustomerUtil {
	
	
	INSTANCE;
	
	
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Loads all Customers from the Databse
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
	 * Adds the customer to the Database
	 *
	 * @param cust the Customer
	 */
	public static void addCustomer(Customer cust) {
		em.getTransaction().begin();
		em.persist(cust);
		em.getTransaction().commit();
	}

	/**
	 * Edits the customer in the Database
	 *
	 * @param cust the Customer
	 */
	public static void editCustomer(Customer cust) {
		em.getTransaction().begin();
		em.merge(cust);
		em.getTransaction().commit();
	}

	/**
	 * Deletes the customer from the Database
	 *
	 * @param cust the Customer
	 */
	public static void deleteCustomer(Customer cust) {
		em.getTransaction().begin();
		em.remove(em.merge(cust));
		em.getTransaction().commit();
	}
}