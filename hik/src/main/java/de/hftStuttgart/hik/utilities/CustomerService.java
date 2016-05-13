package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class CustomerService {

	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<Customer> loadAllCustomers() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<Customer> custList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from Customer t");
		List<Customer> customerList = q.getResultList();
		for (Customer cust : customerList) {
			custList.add(cust);
		}
		em.close();
		return custList;
	}

	public static void addCustomer(Customer cust) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(cust);
		em.getTransaction().commit();
		em.close();
	}

	public static void editCustomer (Customer cust) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(cust);
		em.getTransaction().commit();
		em.close();
	}

	
	public static void deleteCustomer (Customer cust) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(cust));
		em.getTransaction().commit();
		em.close();
	

}
}