package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.PostAdress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum PostAdressUtil {
	INSTANCE;
	private static EntityManager em = DatabaseConnectionUtil.getEm();

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

	public static void addPostAdress(PostAdress postAd) {
		em.getTransaction().begin();
		em.persist(postAd);
		em.getTransaction().commit();
	}

	public static void editPostAdress(PostAdress postAd) {
		em.getTransaction().begin();
		em.merge(postAd);
		em.getTransaction().commit();
	}

	public static void deletePostAdress(PostAdress postAd) {
		em.getTransaction().begin();
		em.remove(em.merge(postAd));
		em.getTransaction().commit();
	}
}
