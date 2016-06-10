package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.SupplierOrder;

public class PostAdressUtilTest {
	private PostAdress postAdress;

	@Before
	public void setUp() throws Exception {
		postAdress = new PostAdress();
		postAdress.setId(2147483648L);
		postAdress.setStreet("Heimstraﬂe");
		postAdress.setHouseNumber(23);
		postAdress.setPostIndex(70439);
		postAdress.setCity("Stuttgart");
		postAdress.setCountry("Deutschland");

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
		PostAdress addedPostAdress = null;
		PostAdressUtil.addPostAdress(postAdress);
		List<PostAdress> postAdresses = PostAdressUtil.loadAllAdresses();
		for (PostAdress add : postAdresses) {
			if (add.equals(addedPostAdress))
				addedPostAdress = add;
		}
		Assert.assertTrue(postAdress.equals(addedPostAdress));
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
		PostAdress deletedPostAdress = null;
		PostAdressUtil.addPostAdress(postAdress);
		PostAdressUtil.deletePostAdress(postAdress);
		List<PostAdress> postAdresses = PostAdressUtil.loadAllAdresses();
		for (PostAdress add : postAdresses) {
			if (add.equals(postAdress))
				deletedPostAdress = add;
		}
		Assert.assertTrue(postAdress.equals(deletedPostAdress));
	}

}
