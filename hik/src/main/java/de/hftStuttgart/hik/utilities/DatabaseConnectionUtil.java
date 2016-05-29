package de.hftStuttgart.hik.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DatabaseConnectionUtil {
	INSTANCE;
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;
	private static EntityManager em = factory.createEntityManager();
	
	public static EntityManagerFactory getFactory() {
		return factory;
	}

	public static EntityManager getEm() {
		return em;
	}
}
