//package de.hftStuttgart.hik;
//
//import java.awt.TextField;
//
//import org.junit.Test;
//
//import org.hibernate.annotations.Parent;
//import org.junit.BeforeClass;
//import org.loadui.testfx.GuiTest;
//import org.loadui.testfx.utils.FXTestUtils;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import de.hftStuttgart.hik.application.Main;
//import de.hftStuttgart.hik.controller.TabViewController;
//
//public class TabViewControllerTest {
//	private static GuiTest controller;
//
//	@BeforeClass
//	public static void setUpClass() {
//		FXTestUtils.launchApp(Main.class);
//
//		controller = new GuiTest() {
//			@Override
//			protected Parent getRootNode() {
//				return Main.showCustomerAndSupplierOverview().getScene().getRoot();
//			}
//		};
//
//		System.out.println(System.getProperty("user.dir"));
//		// ((TextField)GuiTest.find("ncw_f_tf_File")).setText(System.getProperty("user.dir")+"blabla");
//		controller.click("#main_button");
//	}
//
//	@Test
//	public void hierTesten() {
//		System.out.println("nur was testen...");
//	}
//
//	@InjectMocks
//	TabViewController tabView;
//
//	public void testShowBestellung() {
//		tabView.searchCustomer();
//	}
//}
