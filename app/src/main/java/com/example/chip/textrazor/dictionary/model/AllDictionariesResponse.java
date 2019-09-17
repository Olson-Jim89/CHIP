package com.example.chip.textrazor.dictionary.model;

import com.example.chip.textrazor.net.TextRazorResponse;

import java.util.ArrayList;
import java.util.List;

public class AllDictionariesResponse extends TextRazorResponse {
	private List<Dictionary> dictionaries = new ArrayList<Dictionary>();

	public List<Dictionary> getDictionaries() {
		return dictionaries;
	}	
}
