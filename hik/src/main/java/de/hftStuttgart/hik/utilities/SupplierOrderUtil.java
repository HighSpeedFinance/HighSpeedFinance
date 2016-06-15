package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Enum SupplierOrderUtil gets the actual connection to the
 * DatabaseConnection and instaciates the EntityManager. The SupplierOrder has a
 * ManyToOne Relationship to Supplier. The Supplier has to be instaciated before
 * adding a Order
 */
public enum SupplierOrderUtil {

	INSTANCE;

	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Load all orders from the Database
	 *
	 * @param sup
	 *            the Supplier
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<SupplierOrder> loadAllOrders(Supplier sup) {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where supplier_id = " + sup.getId());
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Load all orders from thea Database
	 *
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<SupplierOrder> loadAllOrders() {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t");
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Load all orders from the Database where status open.
	 *
	 * @return the observable list
	 */
	public static ObservableList<SupplierOrder> loadAllOrdersWhereStatusOpen() {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where status = 1 OR status=2");
		@SuppressWarnings("unchecked")
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Load all orders from the Database where status succeeded.
	 *
	 * @return the observable list
	 */
	public static ObservableList<SupplierOrder> loadAllOrdersWhereStatusSucceeded() {
		ObservableList<SupplierOrder> ordList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from SupplierOrder t where status = 0");
		@SuppressWarnings("unchecked")
		List<SupplierOrder> orderList = q.getResultList();
		for (SupplierOrder ord : orderList) {
			ordList.add(ord);
		}
		return ordList;
	}

	/**
	 * Adds the order to the Database
	 *
	 * @param ord
	 *            the Order
	 */
	public static void addOrder(SupplierOrder ord) {
		em.getTransaction().begin();
		em.persist(ord);
		em.getTransaction().commit();
	}

	/**
	 * Edits the order in the Database
	 *
	 * @param ord
	 *            the Order
	 */
	public static void editOrder(SupplierOrder ord) {
		em.getTransaction().begin();
		em.merge(ord);
		em.getTransaction().commit();
	}

	/**
	 * Delete order in the Database
	 *
	 * @param ord
	 *            the Order
	 */
	public static void deleteOrder(SupplierOrder ord) {
		em.getTransaction().begin();
		em.remove(em.merge(ord));
		em.getTransaction().commit();
	}

}
