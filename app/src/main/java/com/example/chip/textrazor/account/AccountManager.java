package com.example.chip.textrazor.account;

import com.example.chip.textrazor.AnalysisException;
import com.example.chip.textrazor.NetworkException;
import com.example.chip.textrazor.account.model.Account;
import com.example.chip.textrazor.account.model.AccountResponse;
import com.example.chip.textrazor.net.TextRazorConnection;

public class AccountManager extends TextRazorConnection {

	public AccountManager(String apiKey) {
		super(apiKey);
	}
	
	public Account getAccount() throws NetworkException, AnalysisException {
		AccountResponse response = sendRequest("account/",
				null, 
				ContentType.JSON,
				"GET",
				AccountResponse.class);
		
		return response.getResponse();
	}

}
