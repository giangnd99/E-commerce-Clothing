package com.springboot.asm.fpoly_asm_springboot.test.ui.login;

import com.springboot.asm.fpoly_asm_springboot.base.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LoginCaseStudy {
    private WebDriver driver;
    private SeleniumHelper seleniumHelper;

    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By INVENTORY_CONTAINER = By.id("inventory_container");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']:not(button)");

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        seleniumHelper = new SeleniumHelper(driver, 10);
    }

    @BeforeMethod
    public void login() {
        driver.get(BASE_URL);
    }

    @DataProvider(name = "sauceLoginData")
    public Object[][] loginTestData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "sauceLoginData")
    public void testLogin(String username, String password) {

        seleniumHelper.sendKeys(USERNAME_INPUT, username);
        seleniumHelper.sendKeys(PASSWORD_INPUT, password);
        seleniumHelper.click(LOGIN_BUTTON);

        if (seleniumHelper.isElementVisible(INVENTORY_CONTAINER, 1)) {
            System.out.println(" Đăng nhập thành công với user: " + username);
        } else if (seleniumHelper.isElementVisible(ERROR_MESSAGE, 1)) {
            String error = seleniumHelper.getText(ERROR_MESSAGE);
            System.out.println(" Đăng nhập thất bại với user: " + username + " | Lý do: " + error);
        } else {
            System.out.println("Không xác định trạng thái với user: " + username);
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
