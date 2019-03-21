package steps;

import io.restassured.RestAssured;
import net.thucydides.core.annotations.Step;

import org.json.JSONObject;

public class ApiTestingSteps_Post {
	// To Execute and validate the POST functionality
	@Step
	public void executeValidatePostFunctionality(String apiName) {
		JSONObject json = new JSONObject();
		JSONObject address = new JSONObject();
		long id = System.currentTimeMillis();
		
		if (apiName.equals("posts")) {
			try {
				json.put("userId", "1");
				json.put("id", id);
				json.put("title", "Cheesecake30");
				json.put("body", "Perimeter Area30");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (apiName.equals("comments")) {
			try {
				json.put("postId", "1");
				json.put("id", id);
				json.put("name", "Soaring Eagles90");
				json.put("email", "comment89@testmail.com");
				json.put("body", "smartcity89");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		else if (apiName.equals("users")) {
			try {
				json.put("id", id);
				json.put("name", "name139");
				json.put("username", "user139");
				json.put("email", "users139@testmail.com");
				json.put("phone", "9999999139");
				json.put("website", "www.website139.com");
				address.put("street", "Johnsons");
				address.put("suite", "1139");
				address.put("city", "Alpharreta");
				json.put("address", address);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RestAssured.given().header("Content-Type", "application/json")
				.body(json.toString())
				.post(apiName)
				.then()
				.statusCode(201);
	}
}
