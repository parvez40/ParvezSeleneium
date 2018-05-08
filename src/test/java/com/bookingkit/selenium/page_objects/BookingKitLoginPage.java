package com.bookingkit.selenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingkit.selenium.DriverBase;
import com.bookingkit.selenium.helper.SeleniumHelper;

public class BookingKitLoginPage {

	private final RemoteWebDriver driver = DriverBase.getDriver();
	private By userName = By.id("LoginForm_username");
	private By userPassWord = By.id("LoginForm_password");
	private By loginButton = By.cssSelector("#login-form button.bigBtn");
	private By stayLoggedIn = By.id("LoginForm_rememberMe");
	private By loginFailedMsgContainerDiv = By.id("LoginForm_password_em_");
	private By wrongUserNameMsgContainerDiv = By.id("LoginForm_username_em_");

	public BookingKitLoginPage() throws Exception {
		driver.manage().window().maximize();
	}

	public boolean login(String email, String password, boolean keepLoggedIn) {
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(email);
		driver.findElement(userPassWord).clear();
		driver.findElement(userPassWord).sendKeys(password);

		if (keepLoggedIn) {
			driver.findElement(userPassWord).sendKeys(Keys.TAB);
			waitForToolTipDisplayToBeVanished();
			driver.findElement(stayLoggedIn).click();
		}
		driver.findElement(loginButton).click();
		return checkIfUserInDashBoardPage();

	}

	public boolean checkIfUserInDashBoardPage() {
		try {
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.findElement(By.id("menuTabs")).isDisplayed();
				}
			});
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean invalidLoginMessageDisplayed() throws Exception {
		try {

			SeleniumHelper.waitForAllLoading();
			return driver.findElement(loginFailedMsgContainerDiv).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean invalidUserNameMessageDisplayed() throws Exception {
		try {

			SeleniumHelper.waitForAllLoading();
			return driver.findElement(wrongUserNameMsgContainerDiv).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean waitForToolTipDisplayToBeVanished() {
		try {
			(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return !d.findElement(By.className("ui-tooltip-content")).isDisplayed();
				}
			});
			
		} catch (Exception e) {
			return true;
		}
		return false;
	}
	
}
