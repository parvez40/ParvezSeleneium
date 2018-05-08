package com.bookingkit.selenium.tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.bookingkit.selenium.DriverBase;
import com.bookingkit.selenium.helper.SeleniumHelper;
import com.bookingkit.selenium.page_objects.BookingKitLandingPage;
import com.bookingkit.selenium.page_objects.BookingKitLoginPage;

public class BookingKitSuiteIT extends DriverBase {
	@Test
	public void BookingKitChangeLanguageFR() throws Exception {

		WebDriver driver = getDriver();
		driver.get("https://releasetest.bookingkit.de/site/login");

		BookingKitLoginPage loginPage = new BookingKitLoginPage();
		assertTrue(!loginPage.login("dantis+test@bookingkXXXX", "SDSDDSome@2018",false));//checking for invalid login
		assertTrue(loginPage.invalidLoginMessageDisplayed());//checking for error message
		assertTrue(loginPage.login("dantis+test@bookingkit.de", "welcome@2018",false));//valid login
		SeleniumHelper.waitForAllLoading();

		BookingKitLandingPage landingPage = new BookingKitLandingPage();
		landingPage.clickDropdown(BookingKitLandingPage.CHANGE_PROFILE);
		landingPage.changeLanguageByLocale("fr");//changing language to French
		landingPage.goToDashBoard();
		landingPage.clickDropdown(BookingKitLandingPage.LOGOUT);
	}

	@Test
	public void BookingKitChangeLanguageDE() throws Exception {

		WebDriver driver = getDriver();
		driver.get("https://releasetest.bookingkit.de/site/login");

		BookingKitLoginPage loginPage = new BookingKitLoginPage();
		assertTrue(!loginPage.login("", "sfsdf6965",false));//checking for login validation for blank email
		assertTrue(loginPage.invalidUserNameMessageDisplayed());//checking error message

		SeleniumHelper.waitForAllLoading();
		assertTrue(loginPage.login("dantis+test@bookingkit.de", "welcome@2018",false));//valid login
		SeleniumHelper.waitForAllLoading();

		BookingKitLandingPage landingPage = new BookingKitLandingPage();
		landingPage.clickDropdown(BookingKitLandingPage.CHANGE_PROFILE);
		landingPage.changeLanguageByLocale("de");//changing language to German
		landingPage.goToDashBoard();
		landingPage.clickDropdown(BookingKitLandingPage.LOGOUT);
	}
	
	@Test
	public void testStayLoggedIn() throws Exception{
		//testing stay logged in checkbox is functional or not
		WebDriver driver = getDriver();
		driver.get("https://releasetest.bookingkit.de/site/login");

		BookingKitLoginPage loginPage = new BookingKitLoginPage();
		assertTrue(loginPage.login("dantis+test@bookingkit.de", "welcome@2018",true));
		
		
		driver.get("https://releasetest.bookingkit.de/");
		loginPage.checkIfUserInDashBoardPage();
		
	}
}
