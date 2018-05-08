package com.bookingkit.selenium.helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.bookingkit.selenium.DriverBase;

import io.appium.java_client.functions.ExpectedCondition;

public class SeleniumHelper {
	
	
	public static void waitForAllLoading() throws Exception {
		waitForPageToLoad();
		waitForJqueryAnimationToBeFinished();
	}

    private static final int LOAD_TIMEOUT = 20;
    private static final int REFRESH_RATE = 1;
	@SuppressWarnings("deprecation")
	private static void waitForPageToLoad() throws Exception {
        
        Wait<WebDriver> wait;
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                       return (Boolean)((JavascriptExecutor) d).executeScript("return document.readyState ==='complete'");
          }
        };

        wait = new FluentWait<WebDriver>(getDriver())
                .withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);
        wait.until(jsLoad);
        
}

        
    @SuppressWarnings("deprecation")
	private static void waitForJqueryAnimationToBeFinished() throws Exception {
        
        Wait<WebDriver> wait1,wait2,wait3;
        ExpectedCondition<Boolean> animationRunning = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                    try {
                        return ((JavascriptExecutor) d).executeScript("return $( \":animated\" ).length").equals(Long.valueOf(0L));    
                    }catch(org.openqa.selenium.JavascriptException e){
                        return false;
                    }
                   
            }
        };

        ExpectedCondition<Boolean> jQueryLoaded = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {

                    return (Boolean)((JavascriptExecutor) d).executeScript("return (window.jQuery != null && jQuery.active===0);");
            }
        };
        
        ExpectedCondition<Boolean> jQueryLoadDefined = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {

                    return (Boolean) ((JavascriptExecutor) d).executeScript("return typeof jQuery !=='undefined'");
            }
        };
        

        wait1 = new FluentWait<WebDriver>(getDriver())
                .withTimeout(55, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);
        wait2 = new FluentWait<WebDriver>(getDriver())
                .withTimeout(50, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);
        wait3 = new FluentWait<WebDriver>(getDriver())
                .withTimeout(45, TimeUnit.SECONDS)
                .pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);
        wait1.until(jQueryLoadDefined);
        wait2.until(jQueryLoaded);
        wait3.until(animationRunning);
    }
    
    private static RemoteWebDriver getDriver() throws Exception {
    	return DriverBase.getDriver();
    }

}
