package com.springboot.asm.fpoly_asm_springboot.test.ui.login;

import com.springboot.asm.fpoly_asm_springboot.base.ExcelHelper;
import com.springboot.asm.fpoly_asm_springboot.base.FilePathTest;
import com.springboot.asm.fpoly_asm_springboot.base.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

public class LoginTest {

    private WebDriver driver;
    private SeleniumHelper seleniumHelper;
    private ExcelHelper excelHelper;

    private static final String SHEET_NAME = "Sheet1";
    private static final String EXCEL_FILE_NAME = "LoginTestCases.xlsx";

    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By ERROR_MESSAGE = By.cssSelector(".error-message");

    private int rowIndex = 1;

    @BeforeClass
    public void setUp() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:5173/login");

        seleniumHelper = new SeleniumHelper(driver, 10);
        excelHelper = new ExcelHelper(SHEET_NAME);
        excelHelper.createHeaderRow(new String[]{"TestCaseID", "Description", "Email", "Password", "Expedition", "Actual", "Result", "ImageError"});
    }

    @DataProvider(name = "loginTestData")
    public Object[][] loginTestData() throws IOException {
        Map<String, String[]> testData = FilePathTest.readLoginTestData(EXCEL_FILE_NAME, SHEET_NAME);
        Object[][] data = new Object[testData.size()][];
        int i = 0;
        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            data[i++] = new Object[]{key, values[0], values[1], values[2], values[3]};
        }
        return data;
    }

    @Test(dataProvider = "loginTestData")
    public void testLogin(String key, String description, String email, String password, String expected) throws IOException {
        try {
            driver.navigate().refresh();
            seleniumHelper.sendKeys(EMAIL_INPUT, email);
            seleniumHelper.sendKeys(PASSWORD_INPUT, password);
            seleniumHelper.click(LOGIN_BUTTON);
            Thread.sleep(500);

            String actual = "";
            try {
                actual = seleniumHelper.getText(ERROR_MESSAGE);
            } catch (Exception e) {
                actual = "Success";
            }

            boolean passed = expected.equalsIgnoreCase(actual);
            Object[] row = {key, description, email, password, expected, actual, passed ? "PASSED" : "FAILED"};
            excelHelper.addRow(row);

            if (!passed) {
                String imgPath = FilePathTest.IMAGE_DIR + "/login_fail_" + key + ".png";
                seleniumHelper.takeScreenshot(imgPath);
                excelHelper.insertImageWithHyperlink(imgPath, excelHelper.getSheet().getRow(rowIndex), 7);
            }

        } catch (Exception ex) {
            Object[] row = {key, description, email, password, expected, "Exception xảy ra", "FAILED"};
            excelHelper.addRow(row);
            String imgPath = FilePathTest.IMAGE_DIR + "/login_exception_" + key + ".png";
            seleniumHelper.takeScreenshot(imgPath);
            excelHelper.insertImageWithHyperlink(imgPath, excelHelper.getSheet().getRow(rowIndex), 7);
        }
        rowIndex++;
    }

    @AfterClass
    public void tearDown() throws IOException {
        excelHelper.writeToFile(FilePathTest.EXCEL_FILE_PATH_UI_LOGIN);
        if (driver != null) {
            driver.quit();
        }
    }
}
