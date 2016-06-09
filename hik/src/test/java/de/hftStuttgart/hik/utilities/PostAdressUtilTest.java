package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.hftStuttgart.hik.model.PostAdress;

public class PostAdressUtilTest {
	private PostAdress PostAdress;

	@Before
	public void setUp() throws Exception {
		PostAdress.setId(2147483648L);
		PostAdress.setStreet("Heimstraﬂe");
		PostAdress.setHouseNumber(23);
		PostAdress.setPostIndex(70439);
		PostAdress.setCity("Stuttgart");
		PostAdress.setCountry("Deutschland");

	}

	@Ignore
	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void testLoadAllAdresses() {
		fail("Not xet implemented");
	}

	@Test
	public void testAddPostAdress(PostAdress PostAdress) {

	}

	@Test
	public void testEditPostAdress() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePostAdress() {
	}

}
