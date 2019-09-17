package com.example.chip.textrazor.dictionary.model;

import com.example.chip.textrazor.net.TextRazorResponse;

public class GetDictionaryEntryResponse extends TextRazorResponse {

	private DictionaryEntry response;

	public DictionaryEntry getResponse() {
		return response;
	}
}
