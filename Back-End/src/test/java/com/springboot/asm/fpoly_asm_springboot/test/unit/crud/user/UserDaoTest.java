package com.springboot.asm.fpoly_asm_springboot.test.unit.crud.user;

import com.springboot.asm.fpoly_asm_springboot.FpolyAsmSpringbootApplication;
import com.springboot.asm.fpoly_asm_springboot.base.ExcelHelper;
import com.springboot.asm.fpoly_asm_springboot.base.FilePathTest;
import com.springboot.asm.fpoly_asm_springboot.base.UserRepoClearHelper;
import com.springboot.asm.fpoly_asm_springboot.entity.User;
import com.springboot.asm.fpoly_asm_springboot.repository.primary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = FpolyAsmSpringbootApplication.class)
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRepoClearHelper userRepoClearHelper;

    private SoftAssert softAssert;
    private ExcelHelper excelHelper;
    private final List<Object[]> excelResults = new ArrayList<>();

    private Map<String, String[]> testData;

    @BeforeClass
    public void beforeClass() throws IOException {
        softAssert = new SoftAssert();
        testData = FilePathTest.readUserTestData("User_TestCases_DAO.xlsx", "Users");

        excelHelper = new ExcelHelper("Results User DAO");
        excelHelper.createHeaderRow(new String[]{
                "Test Case", "Email", "Password", "FullName", "Avatar", "Birthday", "Phone", "Gender", "Role", "Expected", "Actual", "Result"
        });
    }

    @DataProvider(name = "userTestData")
    public Object[][] provideTestData(Method method) {
        String operation = "";
        if (method.getName().contains("Create")) {
            operation = "create";
        } else if (method.getName().contains("Update")) {
            operation = "update";
        } else if (method.getName().contains("Delete")) {
            operation = "delete";
        }

        List<Object[]> filtered = new ArrayList<>();
        for (Map.Entry<String, String[]> entry : testData.entrySet()) {
            String[] values = entry.getValue();
            String operationType = values[0];

            if (operationType.equalsIgnoreCase(operation)) {
                Object[] row = new Object[values.length];
                String testCaseId = entry.getKey();
                row[0] = testCaseId;
                System.arraycopy(values, 1, row, 1, values.length - 1);
                filtered.add(row);
            }
        }

        return filtered.toArray(new Object[0][]);
    }

    @Test(dataProvider = "userTestData")
    public void testCreateUser(String testCaseId, String userId, String email, String password, String fullName,
                               String avatar, String birthday, String phone, String gender,
                               String role, String expected) {
        runTestOperationCreate(testCaseId, userId, email, password, fullName, avatar, birthday, phone, gender, role, expected);
    }

    @Test(dataProvider = "userTestData")
    public void testUpdateUser(String testCaseId, String userId, String email, String password, String fullName,
                               String avatar, String birthday, String phone, String gender,
                               String role, String expected) {
        runTestOperationUpdate(testCaseId, userId, email, password, fullName, avatar, birthday, phone, gender, role, expected);
    }

    @Test(dataProvider = "userTestData" )
    public void testDeleteUser(String testCaseId, String userId, String email, String password, String fullName,
                               String avatar, String birthday, String phone, String gender,
                               String role, String expected) {
        runTestOperationDelete(testCaseId, userId, email, password, fullName, avatar, birthday, phone, gender, role, expected);
    }

    private void runTestOperationDelete(String testCaseId, String userId, String email, String password, String fullName, String avatar, String birthday, String phone, String gender, String role, String expected) {

        String actual;
        boolean passed;

        try {
            Integer id = Integer.parseInt(userId);
            userRepository.deleteById(id);
            actual = "Success";
        } catch (Exception e) {
            actual = e.getMessage();
        }
        passed = actual.contains(expected);
        softAssert.assertTrue(passed, "Test case failed: " + testCaseId);

        excelResults.add(new Object[]{
                testCaseId, email, password, fullName, avatar, birthday, phone, gender, role,
                expected, actual, passed ? "PASSED" : "FAILED"
        });
    }

    private void runTestOperationUpdate(String testCaseId, String userId, String email, String password, String fullName,
                                        String avatar, String birthday, String phone, String gender,
                                        String role, String expected) {
        String actual;
        boolean passed;

        try {
            User user = User.builder()
                    .id(Integer.valueOf(userId))
                    .email(email.isBlank() ? null : email)
                    .password(password.isBlank() ? null : password)
                    .fullName(fullName.isBlank() ? null : fullName)
                    .avatar(avatar.isBlank() ? null : avatar)
                    .birthday(birthday.isBlank() ? null : LocalDate.parse(birthday))
                    .phone(phone.isBlank() ? null : phone)
                    .gender(gender.equalsIgnoreCase("true"))
                    .role(role.equalsIgnoreCase("true"))
                    .build();

            userRepository.save(user);
            actual = "Success";
        } catch (Exception e) {
            actual = e.getMessage();
        }

        passed = actual.contains(expected);
        softAssert.assertTrue(passed, "Test case failed: " + testCaseId);

        excelResults.add(new Object[]{
                testCaseId, email, password, fullName, avatar, birthday, phone, gender, role,
                expected, actual, passed ? "PASSED" : "FAILED"
        });
    }

    private void runTestOperationCreate(String testCaseId, String userId, String email, String password, String fullName,
                                        String avatar, String birthday, String phone, String gender,
                                        String role, String expected) {
        String actual;
        boolean passed;

        try {
            User user = User.builder()
                    .email(email.isBlank() ? null : email)
                    .password(password.isBlank() ? null : password)
                    .fullName(fullName.isBlank() ? null : fullName)
                    .avatar(avatar.isBlank() ? null : avatar)
                    .birthday(birthday.isBlank() ? null : LocalDate.parse(birthday))
                    .phone(phone.isBlank() ? null : phone)
                    .gender(gender.equalsIgnoreCase("true"))
                    .role(role.equalsIgnoreCase("true"))
                    .build();

            userRepository.save(user);
            actual = "Success";
        } catch (Exception e) {
            actual = e.getMessage();
        }

        passed = actual.contains(expected);
        softAssert.assertTrue(passed, "Test case failed: " + testCaseId);

        excelResults.add(new Object[]{
                testCaseId, email, password, fullName, avatar, birthday, phone, gender, role,
                expected, actual, passed ? "PASSED" : "FAILED"
        });
    }

    @AfterClass
    public void afterClass() throws IOException {
        for (Object[] result : excelResults) {
            excelHelper.addRow(result);
        }
        excelHelper.writeToFile(FilePathTest.EXCEL_FILE_PATH);
        userRepoClearHelper.clearAllUsersAndResetAutoIncrement();
        softAssert.assertAll();
    }

}
