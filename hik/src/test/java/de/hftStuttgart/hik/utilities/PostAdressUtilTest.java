package de.hftStuttgart.hik.utilities;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.PostAdress;

public class PostAdressUtilTest {
	private static PostAdress postAdress = new PostAdress();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@Before
	public void setUp() throws Exception {
		if (!TesHelper.postList.contains(postAdress)) {
			postAdress = new PostAdress();
			postAdress.setHouseNumber(TesHelper.adress.getHouseNumber());
			PostAdressUtil.addPostAdress(postAdress);
			TesHelper.postList.add(postAdress);
		}
	}

	@After
	public void tearDown() {
		if (TesHelper.postList.contains(postAdress)) {
			PostAdressUtil.deletePostAdress(postAdress);
			TesHelper.postList.clear();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() {
	}

	// @Test
	// public void testLoadAllAdresses() {
	// List<PostAdress> postAdresses=null;
	// Assert.assertNull(postAdresses);
	// postAdresses=PostAdressUtil.loadAllAdresses();
	// Assert.assertNotNull(postAdresses);
	// }

	@Test
	public void testAddPostAdress() {
		PostAdressUtil.addPostAdress(postAdress);
		TesHelper.postList = PostAdressUtil.loadAllAdresses();
		Assert.assertTrue(TesHelper.postList.contains(postAdress));
	}

	@Test
	public void testEditPostAdress() {
		PostAdress editedPostAdress = null;
		postAdress.setStreet("Sackgasse");
		PostAdressUtil.editPostAdress(postAdress);
		List<PostAdress> postAdresses = PostAdressUtil.loadAllAdresses();
		for (PostAdress adress : postAdresses) {
			if (adress.getId().equals(postAdress.getId()))
				editedPostAdress = adress;
		}
		Assert.assertTrue(postAdress.getStreet() == editedPostAdress.getStreet());
	}

	@Test
	public void testDeletePostAdress() {
		PostAdressUtil.deletePostAdress(postAdress);
		TesHelper.postList = PostAdressUtil.loadAllAdresses();
		Assert.assertTrue(!TesHelper.postList.contains(postAdress));
	}
}