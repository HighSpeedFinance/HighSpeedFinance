package de.hftStuttgart.hik;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import de.hftStuttgart.hik.controller.TabViewController;

public class TabViewControllerTest {
	
	@InjectMocks
	TabViewController tabView;
	
	public void testShowBestellung(){
		tabView.searchCustomer();
	}
}
