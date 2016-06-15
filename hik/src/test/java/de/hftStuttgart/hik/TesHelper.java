package de.hftStuttgart.hik;

import java.util.ArrayList;
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
	public static PaymentDetails paymentDetails = new PaymentDetails("Hans Hansi", "GENODEM1GLS",
			"DE55430609677027019874", "GLSBank");
	public static Supplier supplier = new Supplier(12345, "RechnerExport", "Benrd", "Bauer", adress, "0711-2657890", "berndderbauer@bauer.de", "0711-2657891", "1.1.2010", paymentDetails);

	public static Customer customer = new Customer(123456, "LisaAG", "Lisa", "Lustig", adress, "0711-53678298",
			"lisaLustig@haha.de", "0711-35672891", "Frau", "1.4.2015");
	public static CustomerOrder cOrder = new CustomerOrder("8.6.2016", 1, statusP, 12, "Rechner", 100.00, 3, 19.00);
	public static SupplierOrder sOrder = new SupplierOrder("1.1.2016", 2, statusE, 123, "Kabel", 2.00, 7, 0.38);
	public static List<Supplier> supList = new ArrayList<Supplier>();
	public static List<Customer> cusList = new ArrayList<Customer>();
	public static List<SupplierOrder> supOList = new ArrayList<SupplierOrder>();
	public static List<CustomerOrder> cusOList = new ArrayList<CustomerOrder>();
	public static List<PaymentDetails> payList = new ArrayList<PaymentDetails>();
	public static List<PostAdress> postList = new ArrayList<PostAdress>();

}
