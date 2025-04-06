package stepdefinitions;

import org.junit.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SignIn_Page;

public class Background_StepDefinitions {

	SignIn_Page sp = new SignIn_Page(DriverFactory.getDriver());
	String title = null;

	@Given("User is on Sign-In page")
	public void user_is_on_sign_in_page() {
		sp.OpenSignInPage();
	}

	@When("User gets the page title")
	public void user_gets_the_page_title() {
		title = DriverFactory.getDriver().getTitle();
		System.out.println("Title :" + title);
	}

	@Then("title of the page is {string}")
	public void title_of_the_page_is(String string) {
		Assert.assertEquals(title, string);
	}

}
