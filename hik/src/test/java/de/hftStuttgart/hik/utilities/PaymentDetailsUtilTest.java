package de.hftStuttgart.hik.utilities;



import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import de.hftStuttgart.hik.TesHelper;
import de.hftStuttgart.hik.model.PaymentDetails;


public class PaymentDetailsUtilTest {
	private static PaymentDetails payDet=new PaymentDetails();
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if(TesHelper.payList.contains(payDet))
				PaymentDetailsUtil.deletePayDetails(payDet);
		
	}

	@Before
	public void setUp() throws Exception {
		payDet=TesHelper.paymentDetails;

	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testAddPayDetails() {
		PaymentDetailsUtil.addPayDetails(payDet);
		TesHelper.payList=PaymentDetailsUtil.loadAllPayDetails();
		Assert.assertTrue(TesHelper.payList.contains(payDet));
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
   
	@Ignore
	@Test
	public void testDeletePayDetails() {
		PaymentDetailsUtil.deletePayDetails(payDet);
		TesHelper.payList=PaymentDetailsUtil.loadAllPayDetails();
		Assert.assertTrue(!TesHelper.payList.contains(payDet));
	}

}
