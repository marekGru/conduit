package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.RandomStringUtils;

public class conduitTests {
    private WebDriver driver;
    String BASE_URL = "https://react-redux.realworld.io/";
    String loginName = "tech_task@qats.sk";
    String password = "124lkjAF89as";
    String tag = RandomStringUtils.randomNumeric(5);
    static String articleName = RandomStringUtils.randomAlphabetic(10).toUpperCase();
    public StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/test/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
    }
    @Test
    public void newArticle(){
        driver.get(BASE_URL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#login']")));
        driver.findElement(By.xpath("//a[@href='#login']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(loginName);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#editor']")));
        driver.findElement(By.xpath("//a[@href='#editor']")).click();
        driver.findElement(By.xpath("//Form//input[@placeholder='Article Title']")).sendKeys(articleName);
        driver.findElement(By.xpath("//input[contains(@placeholder,'this article about?')]")).sendKeys("Testing my article");
        driver.findElement(By.xpath("//textarea")).sendKeys(RandomStringUtils.randomAlphabetic(800).toUpperCase());
        driver.findElement(By.xpath("//input[@placeholder='Enter tags']")).sendKeys(tag);
        driver.findElement(By.xpath("//button['Publish Article']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete Article')]")));
        Assert.assertTrue(driver.findElement(By.xpath("//h1")).getText().contains(articleName));
        driver.findElement(By.xpath("//a[@href='#settings']")).click();
        driver.findElement(By.xpath("//button[text()='Or click here to logout.']")).click();
        driver.quit();
    }
    @Test
    public void findArticle() {
        driver.get(BASE_URL);
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#login']")));
        driver.findElement(By.xpath("//a[@href='#login']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(loginName);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#editor']")));
        driver.findElement(By.xpath("//a[text()='Global Feed']")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='article-preview']//a[@href='#@Technical Task']")));
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='#article/" + articleName + "-35746']")).getText().contains(articleName));
        driver.findElement(By.xpath("//h1[1]")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Delete Article')]")));
        driver.findElement(By.xpath("//button[contains(text(),'Delete Article')]")).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#settings']")));
        driver.findElement(By.xpath("//a[@href='#settings']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Or click here to logout.')]")).click();
    }
    @After
    public void tearDown(){
        driver.quit();
        System.out.println("Closed all the browsers");
    }
}
