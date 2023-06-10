package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.collection.IsMapContaining.hasValue;

//@RunWith(SerenityRunner.class)
public class StudentCURDTestWithSteps extends TestBase {
    @Steps
    StudentSteps studentSteps;
    static String firstName = "PrimUser" + TestUtils.getRandomValue();
    static String lastName = "PrimeUser" + TestUtils.getRandomValue();
    static String programme = "Api Testing";
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int studentId;
    @Title("This will create a new student")
    @Test
    public void test001(){
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        ValidatableResponse response= studentSteps.createStudent(firstName,lastName,email,programme,courseList);
        response.log().all().statusCode(201);
    }
    @Title("Verify if the student is added to application")
    @Test
    public void test002() {
        HashMap<String, Object> studentMap = studentSteps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));
        studentId = (int) studentMap.get("id");
    }
    @Title("Update the student information and verify the updated information")
    @Test
    public void test003(){
        firstName=firstName+"_updated";
        List<String> courseList = new ArrayList<>();
        courseList.add("Java");
        courseList.add("Rest Assured");

        studentSteps.updateStudent(studentId,firstName,lastName,email,programme,courseList)
                .statusCode(200);

        HashMap<String, Object> studentMap = studentSteps.getStudentInfoByFirstName(firstName);
        Assert.assertThat(studentMap, hasValue(firstName));
    }
    @Title("This will delete student and verify that student is deleted")
    @Test
    public void test004(){
        studentSteps.deleteStudent(studentId)
                .statusCode(204);

        studentSteps.getStudentById(studentId)
                .statusCode(404);
    }
}
