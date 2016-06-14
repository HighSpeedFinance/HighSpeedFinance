package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
		postAdress = TesHelper.adress;

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (TesHelper.postList.contains(postAdress))
			PostAdressUtil.deletePostAdress(postAdress);
	}

	@Ignore
	@Test
	public void testLoadAllAdresses() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPostAdress(PostAdress PostAdress) {
		PostAdressUtil.addPostAdress(postAdress);
		TesHelper.postList = PostAdressUtil.loadAllAdresses();
		Assert.assertTrue(TesHelper.postList.contains(postAdress));
	}

	@Test
	public void testEditPostAdress() {
		PostAdress editedPostAdress = null;
		PostAdressUtil.addPostAdress(postAdress);
		postAdress.setStreet("Sackgasse");
		PostAdressUtil.editPostAdress(postAdress);
		List<PostAdress> postAdresses = PostAdressUtil.loadAllAdresses();
		for (PostAdress add : postAdresses) {
			if (add.equals(postAdress))
				editedPostAdress = add;
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
