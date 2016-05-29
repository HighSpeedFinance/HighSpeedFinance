package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.PaymentDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum PaymentDetailsUtil {
	INSTANCE;
	private static EntityManager em = DatabaseConnectionUtil.getEm();

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

	public static void addPayDetails(PaymentDetails payD) {
		em.getTransaction().begin();
		em.persist(payD);
		em.getTransaction().commit();
	}

	public static void editPayDetails(PaymentDetails payD) {
		em.getTransaction().begin();
		em.merge(payD);
		em.getTransaction().commit();
	}

	public static void deletePayDetails(PaymentDetails payD) {
		em.getTransaction().begin();
		em.remove(em.merge(payD));
		em.getTransaction().commit();
	}
}
