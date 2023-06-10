package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//@Concurrent(threads = "8x")
//@UseTestDataFrom("src/test/java/resources/testdata/studentinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBase {
    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course1;
    private String course2;
    @Steps
    StudentSteps studentSteps;
   @Title("Data Driven Test for adding multiple students to the application")
    @Test
    public void createMultipleStudents(){
       List<String> courses = new ArrayList<>();
       courses.add(course1);
       courses.add(course2);
       studentSteps.createStudent(firstName,lastName,email,programme,courses).statusCode(201);
   }
}


