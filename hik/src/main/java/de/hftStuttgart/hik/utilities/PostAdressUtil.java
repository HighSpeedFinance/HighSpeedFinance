package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.PostAdress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// TODO: Auto-generated Javadoc
/**
 * The Enum PostAdressUtil.
 */
public enum PostAdressUtil {
	
	/** The instance. */
	INSTANCE;
	
	/** The em. */
	private static EntityManager em = DatabaseConnectionUtil.getEm();

	/**
	 * Load all adresses.
	 *
	 * @return the observable list
	 */
	@SuppressWarnings("unchecked")
	public static ObservableList<PostAdress> loadAllAdresses() {
		ObservableList<PostAdress> pAdList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from PostAdress t");
		List<PostAdress> postAdList = q.getResultList();
		for (PostAdress postAd : postAdList) {
			pAdList.add(postAd);
		}
		return pAdList;
	}

	/**
	 * Adds the post adress.
	 *
	 * @param postAd the post ad
	 */
	public static void addPostAdress(PostAdress postAd) {
		em.getTransaction().begin();
		em.persist(postAd);
		em.getTransaction().commit();
	}

	/**
	 * Edits the post adress.
	 *
	 * @param postAd the post ad
	 */
	public static void editPostAdress(PostAdress postAd) {
		em.getTransaction().begin();
		em.merge(postAd);
		em.getTransaction().commit();
	}

	/**
	 * Delete post adress.
	 *
	 * @param postAd the post ad
	 */
	public static void deletePostAdress(PostAdress postAd) {
		em.getTransaction().begin();
		em.remove(em.merge(postAd));
		em.getTransaction().commit();
	}
}
