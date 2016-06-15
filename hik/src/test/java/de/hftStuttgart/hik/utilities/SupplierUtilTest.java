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

import de.hftStuttgart.hik.model.Supplier;

public class SupplierUtilTest {
	
	private static Supplier supplier= new Supplier();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionUtil.getEm();
	}

	@Before
	public void setUp() throws Exception {
		if(!TesHelper.supList.contains(supplier)){
			supplier = new Supplier();
			supplier.setSupplierNumber(TesHelper.supplier.getSupplierNumber());
			SupplierUtil.addSupplier(supplier);
			TesHelper.supList.add(supplier);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		if (TesHelper.supList.contains(supplier))
			SupplierUtil.deleteSupplier(supplier);
		TesHelper.supList.clear();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	@Test
	public void testAddSupplier() {
		SupplierUtil.addSupplier(supplier);
		TesHelper.supList = SupplierUtil.loadAllSuppliers();
		Assert.assertTrue(TesHelper.supList.contains(supplier));
	}

	@Test
	public void testEditSupplier() {
		Supplier editedSup = null;
		supplier.setSupplierCompanyName("RechnerExport");
		SupplierUtil.editSupplier(supplier);
		List<Supplier> supList = SupplierUtil.loadAllSuppliers();
		for (Supplier sup : supList) {
			if (sup.getId().equals(supplier.getId()))
				editedSup = sup;
		}
		Assert.assertTrue(supplier.getSupplierCompanyName() == editedSup.getSupplierCompanyName());
	}

	@Test
	public void testDeleteSupplier() {
		SupplierUtil.deleteSupplier(supplier);
		TesHelper.supList = SupplierUtil.loadAllSuppliers();
		Assert.assertTrue(!TesHelper.supList.contains(supplier));
	}
}
