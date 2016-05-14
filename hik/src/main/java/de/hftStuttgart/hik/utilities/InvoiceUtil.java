package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum InvoiceUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<Invoice> loadAllInvoices() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<Invoice> invList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from Invoice t");
		List<Invoice> invoiceList = q.getResultList();
		for (Invoice invoice : invoiceList) {
			invList.add(invoice);
		}
		em.close();
		return invList;
	}

	public static void addInvoice(Invoice invoice) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(invoice);
		em.getTransaction().commit();
		em.close();
	}

	public static void editInvoice(Invoice invoice) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(invoice);
		em.getTransaction().commit();
		em.close();
	}

	public static void deleteInvoice(Invoice invoice) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(invoice));
		em.getTransaction().commit();
		em.close();

	}
}
