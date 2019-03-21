package tests;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.ApiTestingSteps_Delete;
import steps.ApiTestingSteps_Get;
import steps.ApiTestingSteps_Post;
import steps.ApiTestingSteps_Put;

@RunWith(SerenityRunner.class)
public class ApiTestingTests {
	ApiTestingSteps_Get apiTestingStepsGet;
	ApiTestingSteps_Put apiTestingStepsPut;
	ApiTestingSteps_Post apiTestingStepsPost;
	ApiTestingSteps_Delete apiTestingStepsDelete;

	@BeforeClass
	public static void setBaseUri() {
		RestAssured.baseURI = "http://ec2-54-84-52-184.compute-1.amazonaws.com";
		RestAssured.port = 3000;
		RestAssured.basePath = "/";
	}

	@Before
	public void instantiateApi() {
		this.apiTestingStepsPut = new ApiTestingSteps_Put();
		this.apiTestingStepsGet = new ApiTestingSteps_Get();
		this.apiTestingStepsPost = new ApiTestingSteps_Post();
		this.apiTestingStepsDelete = new ApiTestingSteps_Delete();
	}

	// //To test the GET functionality of the given API
	@Test
	public void validateGetFunctionality() throws Exception {
		apiTestingStepsGet.getApiResponse("posts");
		apiTestingStepsGet.searchIsExecutedSuccesfully();
		apiTestingStepsGet.iShouldSearchAndValidateTheText("title",
				"qui est esse");
	}

	// To test the POST functionality of the given API
	@Test
	public void validatePostFunctionality() throws Exception {
		apiTestingStepsPost.executeValidatePostFunctionality("posts");
	}

  //To test the PUT functionality of the given API
	@Test()
	public void validatePutFunctionality() throws Exception {
		apiTestingStepsPut.executeAndValidatePutFunctionality("users", "1529",
				"name", "Automation Expert");
	}
	
	@Ignore
	public void validateDeleteFunctionality() throws Exception {
		apiTestingStepsDelete.executeAndValidateDeleteFunctionality("posts","2020");
	}
}
