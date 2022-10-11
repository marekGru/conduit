package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class conduit {
    private WebDriver driver;
    private final String BASE_URL = "https://react-redux.realworld.io/";
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/drivers/chromedriver.exe");
        this.driver = new FirefoxDriver();
    }

    @Test
    public void test(){

    }

    @After
    public void tearDown(){

    }
}
