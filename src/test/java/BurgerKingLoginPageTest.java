import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BurgerKingLoginPageTest {

    WebDriver browser;
    WebElement signUpSignInClick;
    WebElement signUpSignInAcceptionClick;
    WebElement frameInsertLogin;

    @BeforeEach
    public void beforeTest() {
        browser = new ChromeDriver();
        browser.get("https://www.bk.com/");
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {browser.quit();}

    @Test
    public void testEnterTest(){
        signUpSignInClick = browser.findElement(By.xpath
                ("//div[@data-testid=\"mobile-nav-signup-link\"]"));
        signUpSignInClick.click();

        frameInsertLogin = browser.findElement(By.xpath("//input[@placeholder = 'Email Address']"));
        frameInsertLogin.click();
        frameInsertLogin.sendKeys("Test");

        signUpSignInAcceptionClick = browser.findElement(By.xpath("//div[@data-testid=\"signin-button\"]"));
        signUpSignInAcceptionClick.click();

        WebElement errorMassage = browser.findElement(By.xpath
                ("//div[@class=\"css-1rynq56 r-17aj29q r-anxyqk r-1gkfh8e r-oxtfae r-dhbnww\"]"));
        String actual = errorMassage.getText();
        String expexted = "That doesn't look like a valid email.";
        Assertions.assertEquals(expexted, actual);

    }

    @Test
    public void testEnterTestEmail() {
        signUpSignInClick = browser.findElement(By.xpath
                ("//*[@id=\"root\"]/div/div/div/div/div/div/div[1]/div/div/div[1]/div[5]/div[1]/div/div/div"));
        signUpSignInClick.click();

        frameInsertLogin = browser.findElement(By.xpath("//input[@placeholder = 'Email Address']"));
        frameInsertLogin.click();
        frameInsertLogin.sendKeys("test@test.com");

        signUpSignInAcceptionClick = browser.findElement(By.xpath
                ("//*[@id=\"scroll-container\"]/div/div/div/div/div/div/div[3]/div/div/div[2]/div/div"));
        signUpSignInAcceptionClick.click();

        WebElement errorMassage = browser.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div/div[2]/div/div/div[1]/h2"));
        String actual = errorMassage.getText();
        String expexted = "Something went wrong";
        Assertions.assertEquals(expexted, actual);
    }

    @Test
    public void testEnterNothing(){
        signUpSignInClick = browser.findElement(By.xpath
                ("//*[@id=\"root\"]/div/div/div/div/div/div/div[1]/div/div/div[1]/div[5]/div[1]/div/div/div"));
        signUpSignInClick.click();

        frameInsertLogin = browser.findElement(By.xpath("//input[@placeholder = 'Email Address']"));
        frameInsertLogin.click();
        frameInsertLogin.sendKeys(" ");

        signUpSignInAcceptionClick = browser.findElement(By.xpath
                ("//*[@id=\"scroll-container\"]/div/div/div/div/div/div/div[3]/div/div/div[2]/div/div"));
        signUpSignInAcceptionClick.click();

        WebElement errorMassage = browser.findElement(By.xpath("//*[@id=\"field-react-aria-1\"]/div/div/div"));
        String actual = errorMassage.getText();
        String expexted = "Email is a required field.";
        Assertions.assertEquals(expexted, actual);
    }


}
