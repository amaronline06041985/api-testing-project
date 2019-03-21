package steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import junit.framework.Assert;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import org.json.JSONException;
import org.json.JSONObject;
public class ApiTestingSteps {
	
	private String baseUrl="http://ec2-54-84-52-184.compute-1.amazonaws.com:3000/";
	private Response response;
	  
	   //To execute and validate the GET functionality
		   @Step
		   public void getApiResponse(String code){
		       response = SerenityRest.when().get(baseUrl + code);
		   }

		   @Step
		   public void searchIsExecutedSuccesfully(){
		       response.then().statusCode(200);
		   }

		   @Step
		   /*searchObject: id,title, body ,userId.
		   /expectedTextValue: any string value to be validated in the given searchObject result. */
		   
			   public void iShouldSearchAndValidateTheText(String searchObject,String expectedTextValue){
			   List<String> jsonResponse = response.jsonPath().getList("$");
		        //System.out.println("Total size of the Array: "+jsonResponse.size());
		        System.out.println("------Printing "+searchObject+"-----");
		        for(int index=0;index<jsonResponse.size();index++ ) {
		        	//String actualTextValue=response.jsonPath().getString("title["+index+"]");
		        	String actualTextValue=response.jsonPath().getString(searchObject+"["+index+"]");
		        	boolean isFound=actualTextValue.contains(expectedTextValue);
				       // System.out.println(actualTextValue);
				        	if (isFound ){
				        		System.out.println("The expected string value found : "+expectedTextValue);
				        		break;
				        	}
		        	
		        }
		   }
		   
		   //To Execute and validate the POST functionality
		  @Step
		   public void executeValidatePostFunctionality(String apiName) {
		   RequestSpecification request=RestAssured.given();
	    	   request.header("Content-Type", "application/json");
	    	  
	    	  JSONObject json=new JSONObject();
	    	  JSONObject address = new JSONObject();
	    	  if (apiName.equals("posts")){
	    	  try{
	    	  json.put("userId", "1");
	    	  json.put("id", "1636");
	    	  json.put("title", "Chateau Club1");
	    	  json.put("body", "Residence Area1");
	    	  }catch(Exception e){
	    		  e.printStackTrace();
	    	  	}
	    	  }
	    	  else if (apiName.equals("comments")){
		    	  try{
		    	  json.put("postId", "1");
		    	  json.put("id", "1990");
		    	  json.put("name", "Soaring Eagles90");
		    	  json.put("email", "comment89@testmail.com");
		    	  json.put("body", "smartcity89");
		    	  }catch(Exception e){
		    		  e.printStackTrace();
		    	  }
		    	}
//	    	
	    	  else if(apiName.equals("users")){
		    	  try{
		    	  json.put("id", "1199");
		    	  json.put("name", "name139");
		    	  json.put("username", "user139");
		    	  json.put("email", "users139@testmail.com");
		    	  json.put("phone", "9999999139");
		    	  json.put("website", "www.website139.com");
		    	  address.put("street", "Johnsons");
		    	  address.put("suite", "1139");
		    	  address.put("city", "Alpharreta");
		    	  json.put("address", address);
		    	  
		    	  }catch(Exception e){
		    		  e.printStackTrace();
		    	  }
		    	}
	    	 request.body(json.toString());
	    	 Response response=request.post(baseUrl+apiName);
		int code=response.getStatusCode();
		System.out.println("Status Code: "+code);
		Assert.assertEquals(code, 201);
		   } 
		  //To Execute and validate the Delete functionality
		 public void executeAndValidateDeleteFunctionality(String apiName,String idTobeDeleted){
			 RequestSpecification request=RestAssured.given();
			 Response response=request.delete(baseUrl+apiName+"/"+idTobeDeleted);
			 int code=response.getStatusCode();
			 System.out.println("Status Code: "+code);
				Assert.assertEquals(code, 200);
		 }
		 
		 public void executeAndValidatePutFunctionality(String apiName,String idTobeUpdated,String attributebeUpdated, String attributeNewValue){
			 RequestSpecification request=RestAssured.given();
			 JSONObject json=new JSONObject();
			 request.header("Content-Type", "application/json");
			 try {
				json.put(attributebeUpdated, attributeNewValue);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			 request.body(json.toString());
			 Response response=request.patch(baseUrl+apiName+"/"+idTobeUpdated);
			 int code=response.getStatusCode();
			 System.out.println("Status Code: "+code);
				Assert.assertEquals(code, 200);			
				
		 }
		 
}

