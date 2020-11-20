package apiEngine.model.Requests;

public class RemoveBookRequest {
	
	
	
	public String userId;
	public String isbn;
	
	
	public RemoveBookRequest(String userId,String isbn)
	{
		this.userId= userId;
		this.isbn=isbn;
		
		
	}

}
