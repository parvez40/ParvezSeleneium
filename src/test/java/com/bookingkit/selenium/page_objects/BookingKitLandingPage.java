package com.bookingkit.selenium.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bookingkit.selenium.DriverBase;
import com.bookingkit.selenium.helper.SeleniumHelper;

public class BookingKitLandingPage {
	private final RemoteWebDriver driver = DriverBase.getDriver();
	private By dropdownName=By.className("usrDropdownArrow");
	private By saveButton=By.name("yt0");
	private By dashboard=By.className("dashboard-btn");
	
	public static By CHANGE_PROFILE=By.xpath("//a[@href='/setting/user']");
	public static By LOGOUT=By.xpath("//a[@href='/site/logout']");
	 
	
	public BookingKitLandingPage() throws Exception {
		waitForAccountSetUpBaner();
	}
	
	public void clickDropdown(By by) throws Exception{
		Actions actions = new Actions(driver);
		WebElement menuHoverLink = driver.findElement(dropdownName);
		SeleniumHelper.waitForAllLoading();
		actions.moveToElement(menuHoverLink).clickAndHold().perform();
		WebElement item = driver.findElement(by);
		actions.moveToElement(item).click().perform();
		SeleniumHelper.waitForAllLoading();
		
		
	}
	
	public void changeLanguageByLocale(String locale) throws Exception{
		
		waitForAccountSetUpBaner();
		WebElement inputLangRadiobox =driver.findElement(By.cssSelector("input[value='"+locale+"']"));
		int x=inputLangRadiobox.getLocation().x;
		int y=inputLangRadiobox.getLocation().y-30;
		((JavascriptExecutor)driver).executeScript("window.scrollTo("+x+","+y+")");
		SeleniumHelper.waitForAllLoading();
		inputLangRadiobox.click();
		driver.findElement(saveButton).click();
		SeleniumHelper.waitForAllLoading();
	}
	
	public void goToDashBoard() throws Exception {
		waitForAccountSetUpBaner();
		WebElement dashboardLink =driver.findElement(dashboard);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", dashboardLink);
		SeleniumHelper.waitForAllLoading();
		dashboardLink.click();
		SeleniumHelper.waitForAllLoading();
	}
	
	private void waitForAccountSetUpBaner()  throws Exception{
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className("layoutWidth")).isDisplayed();
            }
        });
		Thread.sleep(1500);
	}
	


}
