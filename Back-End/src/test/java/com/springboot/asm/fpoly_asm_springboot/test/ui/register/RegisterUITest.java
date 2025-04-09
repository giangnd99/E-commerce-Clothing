package com.springboot.asm.fpoly_asm_springboot.test.ui.register;

import com.springboot.asm.fpoly_asm_springboot.FpolyAsmSpringbootApplication;
import com.springboot.asm.fpoly_asm_springboot.base.ExcelHelper;
import com.springboot.asm.fpoly_asm_springboot.base.FilePathTest;
import com.springboot.asm.fpoly_asm_springboot.base.SeleniumHelper;
import com.springboot.asm.fpoly_asm_springboot.base.UserRepoClearHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Map;

@SpringBootTest(classes = FpolyAsmSpringbootApplication.class)
public class RegisterUITest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepoClearHelper userRepoClearHelper;

    private WebDriver driver;
    private SeleniumHelper seleniumHelper;
    private ExcelHelper excelHelper;

    private static final String SHEET_NAME = "Sheet1";
    private static final String EXCEL_FILE_NAME = "RegisterTestCases.xlsx";
    private int rowIndex = 1;

    // Locators
    private static final By FIRSTNAME_INPUT = By.id("ho");
    private static final By LASTNAME_INPUT = By.id("ten");
    private static final By GENDER_RADIO_MALE = By.cssSelector("input[type='radio'][value='true']");
    private static final By GENDER_RADIO_FEMALE = By.cssSelector("input[type='radio'][value='false']");
    private static final By PHONE_INPUT = By.id("phoneNumber");
    private static final By DOB_INPUT = By.id("dob");
    private static final By EMAIL_INPUT = By.id("email");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By REGISTER_BUTTON = By.cssSelector("button[type='submit']");
    private static final By TOAST_MESSAGE = By.className("v-toast__text");

    @BeforeClass
    public void setUp() throws IOException {
        excelHelper = new ExcelHelper(SHEET_NAME);
        excelHelper.createHeaderRow(new String[]{"TestCaseID", "Description", "Email", "Password", "Expected", "Actual", "Result", "ImageError"});
    }

    @BeforeMethod
    public void prepareTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        seleniumHelper = new SeleniumHelper(driver, 10);
        driver.get("http://localhost:5173/register");
        Thread.sleep(300);
    }

    @AfterMethod
    public void cleanupAfterTest() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        userRepoClearHelper.clearAllUsersAndResetAutoIncrement();
    }

    @DataProvider(name = "registerTestData")
    public Object[][] registerTestData() throws IOException {
        Map<String, String[]> testData = FilePathTest.readSignInTestData(EXCEL_FILE_NAME, SHEET_NAME);
        Object[][] data = new Object[testData.size()][];
        int i = 0;
        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            data[i++] = new Object[]{key, values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8]};
        }
        return data;
    }

    @Test(dataProvider = "registerTestData")
    public void testRegister(String key, String description, String email, String password,
                             String firstName, String lastName, String gender, String birthday,
                             String phone, String expected) throws IOException {
        try {
            seleniumHelper.sendKeys(FIRSTNAME_INPUT, firstName);
            seleniumHelper.sendKeys(LASTNAME_INPUT, lastName);

            if ("true".equalsIgnoreCase(gender)) {
                seleniumHelper.click(GENDER_RADIO_MALE);
            } else {
                seleniumHelper.click(GENDER_RADIO_FEMALE);
            }

            seleniumHelper.sendKeys(PHONE_INPUT, phone);
            seleniumHelper.sendKeys(DOB_INPUT, birthday);
            seleniumHelper.sendKeys(EMAIL_INPUT, email);
            seleniumHelper.sendKeys(PASSWORD_INPUT, password);
            seleniumHelper.click(REGISTER_BUTTON);

            Thread.sleep(1000); // chờ thông báo

            String actual;
            try {
                actual = seleniumHelper.getText(TOAST_MESSAGE);
            } catch (Exception e) {
                actual = e.getMessage();
            }

            boolean passed = expected.contains(actual);
            Object[] row = {key, description, email, password, expected, actual, passed ? "PASSED" : "FAILED"};
            excelHelper.addRow(row);

            if (!passed) {
                String imgPath = FilePathTest.IMAGE_DIR + "/register_fail_" + key + ".png";
                seleniumHelper.takeScreenshot(imgPath);
                excelHelper.insertImageWithHyperlink(imgPath, excelHelper.getSheet().getRow(rowIndex), 7);
            }

        } catch (Exception ex) {
            Object[] row = {key, description, email, password, expected, ex.getMessage(), "FAILED"};
            excelHelper.addRow(row);
            String imgPath = FilePathTest.IMAGE_DIR + "/register_exception_" + key + ".png";
            seleniumHelper.takeScreenshot(imgPath);
            excelHelper.insertImageWithHyperlink(imgPath, excelHelper.getSheet().getRow(rowIndex), 7);
        }

        rowIndex++;
    }

    @AfterClass
    public void tearDown() throws IOException {
        excelHelper.writeToFile(FilePathTest.EXCEL_FILE_PATH_UI_REGISTER);
    }
}
