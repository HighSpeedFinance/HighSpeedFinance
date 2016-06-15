package de.hftStuttgart.hik.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * The Enum DatabaseConnectionUtil.
 */
public enum DatabaseConnectionUtil {

	INSTANCE;

	private static final String PERSISTENCE_UNIT_NAME = "jpa";

	/** Creates the entity Manager Factory for JPA */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;

	/** Creates an EntityManager object */
	private static EntityManager em = factory.createEntityManager();

	public static EntityManagerFactory getFactory() {
		return factory;
	}

	public static EntityManager getEm() {
		return em;
	}
}
