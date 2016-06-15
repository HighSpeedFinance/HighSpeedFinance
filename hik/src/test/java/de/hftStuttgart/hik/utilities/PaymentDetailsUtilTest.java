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

	@Before
	public void setUp() throws Exception {
		if (!TesHelper.payList.contains(payDet)){
		payDet= new PaymentDetails();
		payDet.setIban(TesHelper.paymentDetails.getIban());
		PaymentDetailsUtil.addPayDetails(payDet);
		TesHelper.payList.add(payDet);
		}
	}

	@After
	public void tearDown() {
		if(TesHelper.payList.contains(payDet)){
			PaymentDetailsUtil.deletePayDetails(payDet);
			TesHelper.payList.clear();
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
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
		payDet.setAccountOwner("Berta Beh");
		PaymentDetailsUtil.editPayDetails(payDet);
		List<PaymentDetails> payList=PaymentDetailsUtil.loadAllPayDetails();
		for(PaymentDetails details:payList){
			if(details.getId().equals(payDet.getId()))
				editedPayDet=details;
		}
		Assert.assertTrue(payDet.getAccountOwner()==editedPayDet.getAccountOwner());	
	}
   
	@Test
	public void testDeletePayDetails() {
		PaymentDetailsUtil.deletePayDetails(payDet);
		TesHelper.payList=PaymentDetailsUtil.loadAllPayDetails();
		Assert.assertTrue(!TesHelper.payList.contains(payDet));
	}
}