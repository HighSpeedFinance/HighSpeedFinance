package de.hftStuttgart.hik.utilities;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.hftStuttgart.hik.model.PaymentDetails;
import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import junit.framework.Assert;

public class SupplierUtilTest {
	private Supplier supplier;
	private List<SupplierOrder> list;
	private PostAdress adress;
	private PaymentDetails details;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		supplier = new Supplier();
		supplier.setAddedDate("11.11.11");
		supplier.setDate("11.11.11");
		supplier.setId(1234567L);
		supplier.setOrders(list);
		supplier.setSupplierAdress(adress);
		supplier.setSupplierAdressCity("Stuttgart");
		supplier.setSupplierAdressCountry("Deutschland");
		supplier.setSupplierAdressHouseNumber(11);
		supplier.setSupplierAdressPostIndex(12345);
		supplier.setSupplierAdressStreet("Schellingstr");
		supplier.setSupplierCompanyName("RechnerExport");
		supplier.setSupplierContactPersonFirstName("Mari");
		supplier.setSupplierContactPersonLastName("Mayer");
		supplier.setSupplierEmail("dieMail@mail.com");
		supplier.setSupplierFax(111111);
		supplier.setSupplierNumber(1234567);
		supplier.setSupplierPaymentDetails(details);
		supplier.setSupplierPhoneNumber(123456789);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddSupplier() {
		Supplier addedSup = null;
		SupplierUtil.addSupplier(supplier);
		List<Supplier> supList = SupplierUtil.loadAllSuppliers();
		for (Supplier all : supList) {
			if (all.getId().equals(addedSup.getId())) {
				addedSup = all;
			}
		}
		Assert.assertTrue(supplier.equals(addedSup));
	}

	@Test
	public void testEditSupplier() {
		Supplier editedSup = null;
		SupplierUtil.addSupplier(supplier);
		supplier.setSupplierCompanyName("RechnerExport");
		SupplierUtil.editSupplier(supplier);
		List<Supplier> supList = SupplierUtil.loadAllSuppliers();
		for (Supplier sup : supList) {
			if (sup.equals(supplier))
				editedSup = sup;
		}
		Assert.assertTrue(supplier.getSupplierCompanyName() == editedSup.getSupplierCompanyName());

	}

	@Test
	public void testDeleteSupplier() {

		Supplier deletedSup = null;
		SupplierUtil.addSupplier(supplier);
		SupplierUtil.deleteSupplier(supplier);
		List<Supplier> supList = SupplierUtil.loadAllSuppliers();
		for (Supplier sup : supList) {
			if (sup.equals(supplier))
				deletedSup = sup;
		}
		Assert.assertTrue(supplier.equals(deletedSup));

	}
}
