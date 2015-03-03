package com.retroeventbus.pojos;

import lombok.Data;

@Data
public class LoginResponse {
	
	private UserLoginResult UserLoginResult;
	
	@Data
	public class UserLoginResult{
		
		private String AuthenticationType;
		
		private UserDetail [] UserDetail;
		
		private OutputStatus OutputStatus;
		
	}
	
	@Data
	public class UserDetail{
		String UserID;
		String FirstName;
		String LastName;
		String GenderID;
		String Phone;
		String Email;
		String Photo;
		String TotalCompany;
		String CompanyID;
		String CompanyName;
		String StoreID;
		String StoreName;
		
	}
	
	public class OutputStatus{
		
		String Description;
		String OutStatus;
	}

}
