package com.studentapp.cucumber.steps;

import com.studentapp.studentinfo.StudentSteps;
import com.studentapp.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

public class MyStepdefs {
    static ValidatableResponse response;
    static String email=null;
    @Steps
    StudentSteps studentSteps;
    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response=studentSteps.getAllStudentInfo();
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^I create a new student by providing the information firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iCreateANewStudentByProvidingTheInformationFirstNameLastNameEmailProgrammeCourses(String firstName, String lastName, String _email, String programme, String courses) {
        List<String> courseList = new ArrayList<>();
        courseList.add(courses);
        email = TestUtils.getRandomValue()+_email;
        response=studentSteps.createStudent(firstName,lastName,email,programme,courseList).statusCode(201);
    }

    @Then("^I verify that the student with \"([^\"]*)\" is created$")
    public void iVerifyThatTheStudentWithIsCreated(String _email) {
        HashMap<String, Object> studentMap=studentSteps.getStudentInfoByEmail(email);
        Assert.assertThat(studentMap, hasValue(email));
    }

    @Given("^Student application is running$")
    public void studentApplicationIsRunning() {
    }

    @And("^I update the student with information firstName \"([^\"]*)\" lastName \"([^\"]*)\" email \"([^\"]*)\" programme \"([^\"]*)\" courses \"([^\"]*)\"$")
    public void iUpdateTheStudentWithInformationFirstNameLastNameEmailProgrammeCourses(String firstName, String lastName, String _email, String programme, String courses) {

    }

    @Then("^The student with firstName \"([^\"]*)\" is updated successfully$")
    public void theStudentWithFirstNameIsUpdatedSuccessfully(String arg0) {

    }

    @And("^I delete the student that created with firstName \"([^\"]*)\"$")
    public void iDeleteTheStudentThatCreatedWithFirstName(String arg0) {

    }

    @Then("^The student deleted successfully from the application$")
    public void theStudentDeletedSuccessfullyFromTheApplication() {
    }
}
