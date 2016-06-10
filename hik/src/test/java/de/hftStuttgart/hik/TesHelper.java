package de.hftStuttgart.hik;

import java.util.List;

import de.hftStuttgart.hik.model.Customer;
import de.hftStuttgart.hik.model.CustomerOrder;
import de.hftStuttgart.hik.model.PaymentDetails;
import de.hftStuttgart.hik.model.PostAdress;
import de.hftStuttgart.hik.model.Status;
import de.hftStuttgart.hik.model.Supplier;
import de.hftStuttgart.hik.model.SupplierOrder;
import de.hftStuttgart.hik.utilities.DatabaseConnectionUtil;
import de.hftStuttgart.hik.utilities.PaymentDetailsUtil;

//Alle Daten die h�ufiger gebraucht werden hier rein
public class TesHelper {

	public static Status statusE = Status.ENABLED;
	public static Status statusP = Status.PENDING;
	public static Status statusS = Status.SUCCEEDED;
	public static PostAdress adress = new PostAdress("Berndstrasse", 2, 70199, "Stuttgart", "Deutschland");
	public static PaymentDetails paymentDetails = new PaymentDetails("Bernd Bauer	", 12345, 23456, "BWBank");
	public static Supplier supplier = new Supplier(12345, "RechnerExport", "Bauer", "Bernd", adress, 1256488273,
			"berndBauer@berndisttoll.com", 112657891, "1.2.2016", paymentDetails);

	public static Customer customer = new Customer(123456, "LisaAG", "Lisa", "Lustig", adress, 123456,
			"lisaLustig@haha.de", 123432, "Frau", "1.4.2015");
	public static CustomerOrder cOrder = new CustomerOrder("8.6.2016", 1, statusP, 12, "Rechner", 100.00, 3, 19.00);
	public static SupplierOrder sOrder = new SupplierOrder("1.1.2016", 2, statusE, 123, "Kabel", 2.00, 7, 0.38);
	public static List<Supplier> supList;
	public static List<Customer> cusList;
	public static List<SupplierOrder> supOList;
	public static List<CustomerOrder> cusOList;

}
