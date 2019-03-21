package steps;

import io.restassured.RestAssured;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiTestingSteps_Put {
	public void executeAndValidatePutFunctionality(String apiName,
			String idTobeUpdated, String attributebeUpdated,
			String attributeNewValue) throws JSONException {
		JSONObject json = new JSONObject();

		json.put(attributebeUpdated, attributeNewValue);

		RestAssured.given().header("Content-Type", "application/json")
				.body(json.toString()).patch(apiName + "/" + idTobeUpdated)
				.then().statusCode(200);

	}

}
