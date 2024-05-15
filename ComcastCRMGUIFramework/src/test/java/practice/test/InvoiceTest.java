package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

//@Listeners(com.comcast.crm.listenerutiility.ListenerImpClass.class)
public class InvoiceTest extends BaseClass{

	@Test(retryAnalyzer = com.comcast.crm.listenerutiility.RetryListenerImp.class )
	public void createInvoiceTest() {
		System.out.println("execute createInvoiceTest");
		//String actTitle = driver.getTitle();
		Assert.assertEquals("Login","Login");
		System.out.println("Step -1");
		System.out.println("Step -2");
		System.out.println("Step -3");
		System.out.println("Step -4");

		
	}
/*	@Test
	public void createInvoicewithContactTest() {
		System.out.println("execute createInvoiceWithContactTest");
		System.out.println("execute createInvoiceTest");
		System.out.println("Step -1");
		System.out.println("Step -2");
		System.out.println("Step -3");
		System.out.println("Step -4");
   }*/
}
	