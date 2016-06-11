package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.SupplierOrder;

public class PostAdressUtilTest {
	private PostAdress postAdress=new PostAdress();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}
	@Before
	public void setUp() throws Exception {
		postAdress=TesHelper.adress;

	}

	@Ignore
	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void testLoadAllAdresses() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPostAdress(PostAdress PostAdress) {
		PostAdressUtil.addPostAdress(postAdress);
		TesHelper.postList=PostAdressUtil.loadAllAdresses();
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
		TesHelper.postList=PostAdressUtil.loadAllAdresses();
		Assert.assertTrue(!TesHelper.postList.contains(postAdress));
	}

}
