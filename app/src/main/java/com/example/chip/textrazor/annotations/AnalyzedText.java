package com.example.chip.textrazor.annotations;

import com.example.chip.textrazor.net.TextRazorResponse;

public class AnalyzedText extends TextRazorResponse {
	
	private Response response;
	
	/**
	 * @return The {@link Response} containing extracted TextRazor metadata.
	 */
	public Response getResponse() {
		return response;
	}
	
	public void createAnnotationLinks() {
		response.createAnnotationLinks();
	}
}
