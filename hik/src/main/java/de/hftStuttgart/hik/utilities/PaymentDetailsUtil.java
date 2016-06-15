package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.PaymentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Enum PaymentDetailsUtil gets the actual connection to the
 * DatabaseConnection and instanciates the EntityManager
 */
public enum PaymentDetailsUtil {

	INSTANCE;

	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Loads all PaymentDetails from the Database
	 *
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<PaymentDetails> loadAllPayDetails() {
		ObservableList<PaymentDetails> payDList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from PaymentDetails t");
		List<PaymentDetails> payDetailsList = q.getResultList();
		for (PaymentDetails payD : payDetailsList) {
			payDList.add(payD);
		}
		return payDList;
	}

	/**
	 * Adds the PaymentDetails to the Database
	 *
	 * @param payD
	 *            the PaymentDetails
	 */
	public static void addPayDetails(PaymentDetails payD) {
		em.getTransaction().begin();
		em.persist(payD);
		em.getTransaction().commit();
	}

	/**
	 * Edits the PaymentDetails in the Databse
	 *
	 * @param payD
	 *            the PaymentDetails
	 */
	public static void editPayDetails(PaymentDetails payD) {
		em.getTransaction().begin();
		em.merge(payD);
		em.getTransaction().commit();
	}

	/**
	 * Deletes the PaymentDetails from the Database
	 *
	 * @param payD
	 *            the PaymentDetails
	 */
	public static void deletePayDetails(PaymentDetails payD) {
		em.getTransaction().begin();
		em.remove(em.merge(payD));
		em.getTransaction().commit();
	}
}
