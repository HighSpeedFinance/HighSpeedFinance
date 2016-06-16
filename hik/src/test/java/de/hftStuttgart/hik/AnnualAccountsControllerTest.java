package de.hftStuttgart.hik;

import static org.junit.Assert.*;
import java.awt.Label;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import de.hftStuttgart.hik.controller.AnnualAccountsController;


@Ignore
public class AnnualAccountsControllerTest {
	Label resultIncomeCost= new Label();
	private double summeEinnahmen;
	private double summeAusgaben;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		summeAusgaben=20;
		summeEinnahmen=10;
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetResultIncomeLabel() {
		
		AnnualAccountsController cont = new AnnualAccountsController();
		cont.setResultIncomeLabel();
		String t=resultIncomeCost.getText();
		String r= String.valueOf(summeAusgaben-summeEinnahmen);
		assertTrue(r==t);	
		
	}
	
	

	@Test
	public void testSetMainApp() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetListInTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetPlzComboBox() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadOrdersPlz() {
		fail("Not yet implemented");
	}

}
