package practice.test;
/**
 * test class for Contact Module
 * @author Tushar Gavare
 * 
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

import net.bytebuddy.asm.Advice.AssignReturned.ToAllArguments;

public class SearchContactTest extends BaseClass{
	/**
	 * Scenario : login() navigate to ===> navigate to contact ====>Createcontact()==verify
	 */
	@Test
	public void searchcontactTest() {
		/*Step 1:Login to application*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "ussername", "password");
	}

}
