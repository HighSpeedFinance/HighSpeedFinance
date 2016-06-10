package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.model.PaymentDetails;
import junit.framework.Assert;

public class PaymentDetailsUtilTest {
	private PaymentDetails payDet;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		payDet.setAccountOwner("Hansi Hans");
		payDet.setBic(12345);
		payDet.setCreditInstitution("BWBank");
		payDet.setIban(123456789);
		payDet.setId(127654398L);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadAllPayDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPayDetails() {
		PaymentDetails addedDet=null;
		PaymentDetailsUtil.addPayDetails(payDet);
		List<PaymentDetails> payList=PaymentDetailsUtil.loadAllPayDetails();
		for(PaymentDetails payDetails:payList){
			if(payDetails.equals(addedDet))
				addedDet=payDetails;
		}
		Assert.assertTrue(payDet.equals(addedDet));
	}

	@Test
	public void testEditPayDetails() {
		PaymentDetails editedPayDet=null;
		PaymentDetailsUtil.addPayDetails(payDet);
		payDet.setAccountOwner("Hansi Hans");
		PaymentDetailsUtil.editPayDetails(payDet);
		List<PaymentDetails> payList=PaymentDetailsUtil.loadAllPayDetails();
		for(PaymentDetails details:payList){
			if(details.equals(payDet))
				editedPayDet=details;
		}Assert.assertTrue(payDet.getAccountOwner()==editedPayDet.getAccountOwner());
		
	}

	@Test
	public void testDeletePayDetails() {
		PaymentDetails deletedDetails=null;
		PaymentDetailsUtil.addPayDetails(payDet);
		PaymentDetailsUtil.deletePayDetails(payDet);
		List<PaymentDetails> payList=PaymentDetailsUtil.loadAllPayDetails();
		for(PaymentDetails details:payList){
			if(details.equals(payDet))
				deletedDetails=details;
		}Assert.assertTrue(payDet.equals(deletedDetails));
	}

}
