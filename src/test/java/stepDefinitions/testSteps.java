package stepDefinitions;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import apiEngine.model.Book;
import apiEngine.model.Requests.AddBookRequest;
import apiEngine.model.Requests.AuthorizationRequest;
import apiEngine.model.Requests.ISBN;
import apiEngine.model.Requests.RemoveBookRequest;
import apiEngine.model.Response.Token;
import apiEngine.model.Response.UserAccount;
import apiEngine.model.Response.getBooks;
import apiTests.Endpoints;




public class testSteps {

	
	private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
	private static final String USERNAME = "TOOLSQA-Test";
	private static final String PASSWORD = "Test@@123";
	private static final String BASE_URL = "https://bookstore.toolsqa.com";
	
	//private static String bookId;
	//private static Token token;
	
	private static Token tokenResponse;
	private static Book book;
	private static getBooks books;
	private static Response response;
	private static UserAccount userResponse;
	
	
	@Given("I am an athorized user")
	public void i_am_an_athorized_user() {
		
		
//		RestAssured.defaultParser = Parser.JSON;
//		
//		
//		
//		RequestSpecification request =  RestAssured.given();
//		
//		Response response =  request.body("userName"+  USERNAME + "Password"+ PASSWORD ).post("/Account/v1/GenerateToken");
//		
//		String jsonBody =  System.getProperty("user.dir"+"/POJOClass/src/main/java/jsonUtils/AddBooks.json");
//		
//		given().bo
//		
//		
//		
//		 response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
//				 "\"collectionOfIsbns\": [ { \"isbn\": \"" + bookId + "\" } ]}")
//				 .post("/BookStore/v1/Books");
//				 }
//		
//		       String jsonString =  response.asString();
//		       
//		       String token =  JsonPath.from(jsonString).get("token");
		
		AuthorizationRequest authRequest = new AuthorizationRequest(USERNAME, PASSWORD);
		//RestAssured.baseURI=BASE_URL;
		
		//RequestSpecification request =  RestAssured.given();
		
		//request.header("Content-Type","application/json");
		
		 //response =  request.body(authRequest).post("/Account/v1/GenerateToken");
		
		//String jsonString =  response.asString();
		
	//	token =  JsonPath.from(jsonString).get("token");
		
		//tokenResponse =   response.getBody().as(Token.class);
		
		//System.out.println(tokenResponse);
		
		response = Endpoints.generateToken(authRequest);
		
		tokenResponse = response.getBody().as(Token.class);
		
	}
	

	@Given("A list of books is available")
	public void a_list_of_books_is_available() {

				//RestAssured.baseURI=BASE_URL;
		
				//RequestSpecification request =  RestAssured.given();
				
				//Response response =  request.get("/BookStore/v1/Books");
				
				//String jsonString =  response.asString();
				
			//	List<Map<String, String>> books =  JsonPath.from(jsonString).get("books");
				
				//  bookId = books.get(0).get("isbn");
				  
				 // System.out.println(bookId);
				
				 
				
				
				
				//System.out.println(book);
		
				response = Endpoints.getBooks();
				books = response.getBody().as(getBooks.class);
				book =  books.books.get(0);
				
		
	}

	@When("User adds book to reading list")
	
	public void user_adds_book_to_reading_list() {
		
		AddBookRequest addbookRequest = new AddBookRequest(USER_ID, new ISBN(book.isbn));
		//RestAssured.baseURI=BASE_URL;
		
		//RequestSpecification request =  RestAssured.given();
		
		//request.header("Authorization","Bearer" +tokenResponse.token).header("Content-Type","application/json");
		
		
		
		 //response = request.body(addbookRequest).post("/BookStore/v1/Books");
		
		response = Endpoints.addBook(addbookRequest, tokenResponse.token);
	    
	    
	}

	@Then("book is added successfully")
	
	public void book_is_added_successfully() {

				//Getting 401 : 
		 Assert.assertEquals(201, response.getStatusCode());
		 
		 userResponse =  response.getBody().as(UserAccount.class);
		 
		 
		 Assert.assertEquals(USER_ID, userResponse.USerID); 
		 
		 Assert.assertEquals(book.isbn, userResponse.books.get(0).isbn);
		
	}

	@When("user removes book from the list")
	
	public void user_removes_book_from_the_list() {
		
			//RestAssured.baseURI=BASE_URL;
		
		//RequestSpecification request =  RestAssured.given();
		
		RemoveBookRequest removeBooks = new RemoveBookRequest(USER_ID, book.isbn);
		
		//request.header("Authorization","Bearer"+tokenResponse.token).header("Content-Type","application/json");
		
				
		//response =  request.body(removeBooks).delete("/BookStore/v1/Book");
		response = Endpoints.removeBooks(removeBooks, tokenResponse.token);
		
	}

	@Then("book is removed successfully")
	
	public void book_is_removed_successfully() {
		
		Assert.assertEquals(204, response.getStatusCode());
		
			//RestAssured.baseURI=BASE_URL;
		
			//RequestSpecification request =  RestAssured.given();
			
			//request.header("Authorization","Bearer"+tokenResponse.token).header("Content-Type","application/json");
			
			//response =  request.get("/BookStore/v1/Books" +USER_ID);
			// Assert.assertEquals(200, response.getStatusCode());
			
			//String jsonString =  response.asString();
			
			//List<String> books =   JsonPath.from(jsonString).get("books");
			
			//Assert.assertEquals(0, books.size());
		
		
			// userResponse = response.getBody().as(UserAccount.class);
			 
			// Assert.assertEquals(0, userResponse.books.size());
		
		response = Endpoints.getUserAccount(USER_ID, tokenResponse.token);
		
		Assert.assertEquals(200, response.getStatusCode());
		
		
		
	}
}
