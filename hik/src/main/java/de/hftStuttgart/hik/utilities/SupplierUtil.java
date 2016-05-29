package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public enum SupplierUtil {

	INSTANCE;
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	@SuppressWarnings("unchecked")
	public static ObservableList<Supplier> loadAllSuppliers() {
		ObservableList<Supplier> supList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from Supplier t");
		List<Supplier> supplierList = q.getResultList();
		for (Supplier sup : supplierList) {
			supList.add(sup);
		}
		return supList;
	}

	public static void addSupplier(Supplier sup) {
		em.getTransaction().begin();
		em.persist(sup);
		em.getTransaction().commit();
	}

	public static void editSupplier(Supplier sup) {
		em.getTransaction().begin();
		em.merge(sup);
		em.getTransaction().commit();
	}

	
	public static void deleteSupplier(Supplier sup) {
		em.getTransaction().begin();
		em.remove(em.merge(sup));
		em.getTransaction().commit();
	}
}
