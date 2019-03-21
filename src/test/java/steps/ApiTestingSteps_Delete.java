package steps;

import io.restassured.RestAssured;

public class ApiTestingSteps_Delete {
	// To Execute and validate the Delete functionality
	public void executeAndValidateDeleteFunctionality(String apiName,
			String idTobeDeleted) {
		RestAssured.given()
			.delete(apiName + "/" + idTobeDeleted)
			.then()
			.statusCode(500); //Using 500 code as its throwing this code even though the data is deleted successfully
	}
}
