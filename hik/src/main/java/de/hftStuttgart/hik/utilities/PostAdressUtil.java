package de.hftStuttgart.hik.utilities;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.hftStuttgart.hik.model.PostAdress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum PostAdressUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	@SuppressWarnings("unchecked")
	public static ObservableList<PostAdress> loadAllAdresses() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		ObservableList<PostAdress> pAdList = FXCollections.observableArrayList();
		Query q = em.createQuery("select t from PostAdress t");
		List<PostAdress> postAdList = q.getResultList();
		for (PostAdress postAd : postAdList) {
			pAdList.add(postAd);
		}
		em.close();
		return pAdList;
	}

	public static void addPostAdress(PostAdress postAd) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(postAd);
		em.getTransaction().commit();
		em.close();
	}

	public static void editPostAdress(PostAdress postAd) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(postAd);
		em.getTransaction().commit();
		em.close();
	}

	public static void deletePostAdress(PostAdress postAd) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(postAd));
		em.getTransaction().commit();
		em.close();

	}
}
