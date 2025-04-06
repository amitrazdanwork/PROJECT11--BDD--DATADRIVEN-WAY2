package stepdefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Home_Page;
import pages.SignIn_Page;
import utils.XLUtils;

public class SignIn_StepDefinitions {

	SignIn_Page spage = new SignIn_Page(DriverFactory.getDriver());
	Home_Page hpage = new Home_Page(DriverFactory.getDriver());
	String title = null;


	@Then("Email label is displayed on sign-in page")
	public void email_label_is_displayed_on_sign_in_page() {
		Assert.assertTrue(spage.isEmailLabelPresent());
	}

	@Then("Email textbox is displayed on sign-in page")
	public void email_textbox_is_displayed_on_sign_in_page() {
		Assert.assertTrue(spage.isEmailTextboxPresent());
	}

	@Then("Password label is displayed on sign-in page")
	public void password_label_is_displayed_on_sign_in_page() {
		Assert.assertTrue(spage.isPasswordLabelPresent());
	}

	@Then("Password textbox is displayed on sign-in page")
	public void password_textbox_is_displayed_on_sign_in_page() {
		Assert.assertTrue(spage.isPasswordTextboxPresent());
	}

	@Then("Forgot Password Link is displayed")
	public void forgot_password_link_is_displayed() {
		Assert.assertTrue(spage.isForgotPasswordLinkPresent());
	}

	@Then("Create An Account button is displayed")
	public void create_an_account_button_is_displayed() {
		Assert.assertTrue(spage.isCreateAnAccountButtonPresent());
	}

	@Then("Sign-In button is displayed")
	public void sign_in_button_is_displayed() {
		Assert.assertTrue(spage.isSignInButtonPresent());
	}

	@When("User enters data from {string}, {string} and {string} in sign-in form")
	public void user_enters_data_from_and_in_sign_in_form(String string, String string2, String string3) throws Exception {

		Integer row = Integer.valueOf(string3);

		List<Map<String, String>> allData = XLUtils.getAllSheetData("C:\\Users\\razda\\git\\PROJECT7_GRIDPROJECT\\src\\test\\resources\\testdata\\TestData.xlsx", string2);

		Map<String, String> FormData = allData.get(row);

		spage.EnterEmail(FormData.get("Email"));
		spage.EnterPassword(FormData.get("Password"));
		
	}
	
	
	@When("User clicks Sign-In button")
	public void user_clicks_sign_in_button() {
	  
		spage.ClickSignInButton();
	
	}
	@Then("User gets page title as {string}")
	public void user_gets_page_title_as(String string) {
	  
	}
	
}
