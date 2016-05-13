package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public enum SupplierUtil {

	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<Supplier> loadAllSuppliers() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<Supplier> supList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from Supplier t");
		List<Supplier> supplierList = q.getResultList();
		for (Supplier sup : supplierList) {
			supList.add(sup);
		}
		em.close();
		return supList;
	}

	public static void addSupplier(Supplier sup) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(sup);
		em.getTransaction().commit();
		em.close();
	}

	public static void editSupplier(Supplier sup) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(sup);
		em.getTransaction().commit();
		em.close();
	}

	
	public static void deleteSupplier(Supplier sup) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(sup));
		em.getTransaction().commit();
		em.close();
	}
}
