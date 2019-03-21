package steps;

import io.restassured.response.Response;

import java.util.List;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ApiTestingSteps_Get {
	private Response response;

	// To execute and validate the GET functionality
	@Step
	public void getApiResponse(String apiName) {
		response = SerenityRest.when().get(apiName);
	}

	@Step
	public void searchIsExecutedSuccesfully() {
		response.then().statusCode(200);
	}

	@Step
	/*
	 * searchObject: id,title, body ,userId. /expectedTextValue: any string
	 * value to be validated in the given searchObject result.
	 */
	public void iShouldSearchAndValidateTheText(String searchObject,
			String expectedTextValue) {

		List<String> jsonResponse = response.jsonPath().getList("$");
		System.out.println("------Printing " + searchObject + "-----");
		for (int index = 0; index < jsonResponse.size(); index++) {
			String actualTextValue = response.jsonPath().getString(
					searchObject + "[" + index + "]");
			boolean isFound = actualTextValue.contains(expectedTextValue);
			// System.out.println(actualTextValue);
			if (isFound) {
				System.out.println("The expected string value found : "
						+ expectedTextValue);
				break;
			}

		}
	}
}
