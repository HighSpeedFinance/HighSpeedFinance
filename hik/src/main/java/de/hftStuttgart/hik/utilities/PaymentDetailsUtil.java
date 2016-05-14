package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.PaymentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum PaymentDetailsUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<PaymentDetails> loadAllPayDetails() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<PaymentDetails> payDList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from PaymentDetails t");
		List<PaymentDetails> payDetailsList = q.getResultList();
		for (PaymentDetails payD : payDetailsList) {
			payDList.add(payD);
		}
		em.close();
		return payDList;
	}

	public static void addPayDetails(PaymentDetails payD) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(payD);
		em.getTransaction().commit();
		em.close();
	}

	public static void editPayDetails(PaymentDetails payD) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(payD);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletePayDetails(PaymentDetails payD) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(payD));
		em.getTransaction().commit();
		em.close();

	}
}
