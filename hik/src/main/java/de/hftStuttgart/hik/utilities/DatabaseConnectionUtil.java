package de.hftStuttgart.hik.utilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// TODO: Auto-generated Javadoc
/**
 * The Enum DatabaseConnectionUtil.
 */
public enum DatabaseConnectionUtil {
	
	/** The instance. */
	INSTANCE;
	
	/** The Constant PERSISTENCE_UNIT_NAME. */
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	
	/** The factory. */
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;
	
	/** The em. */
	private static EntityManager em = factory.createEntityManager();
	
	/**
	 * Gets the factory.
	 *
	 * @return the factory
	 */
	public static EntityManagerFactory getFactory() {
		return factory;
	}

	/**
	 * Gets the em.
	 *
	 * @return the em
	 */
	public static EntityManager getEm() {
		return em;
	}
}
