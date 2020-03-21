package standalone_ui_tests;

import utilities.Keywords;

public class LoginPageUITest {

	public static void main(String[] args) {
		try {
			
			Keywords.startUItest();
			Keywords.navigate("https://lit-mesa-27064.herokuapp.com/");
			Keywords.click("HomePage.LoginLink");
			Keywords.enter("LoginPage.EmailField", "murad@test.com");
			Keywords.enter("LoginPage.PasswordField", "murad001");
			Keywords.click("LoginPage.LoginButton");
			Keywords.endUITest();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
