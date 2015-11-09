package io.github.mmathys.Projekt.interact;

import java.util.ArrayList;

import io.github.mmathys.Projekt.util.ErrorHandler;

public abstract class Question implements Answerable {
	private String question;

	private ArrayList<ActionHandler> handlers = new ArrayList<ActionHandler>();

	private String response;
	private ErrorHandler errorHandler;
	private String errorMessage = "Fehler.";

	public Question(String question) {
		this.setQuestion(question);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void throwError(String errorMessage) {
		throwError(errorMessage, true);
	}

	public void throwError(String errorMessage, boolean retry) {
		this.setErrorMessage(errorMessage);
		errorHandler.handleError(this, retry);
	}

	public void addErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ArrayList<ActionHandler> getHandlers() {
		return handlers;
	}

	public void addHandler(ActionHandler handler) {
		this.handlers.add(handler);
	}

	public void setHandlers(ArrayList<ActionHandler> handlers) {
		this.handlers = handlers;
	}
}
