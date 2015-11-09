package io.github.mmathys.Projekt.interact;

public class OpenQuestion extends Question {

	public OpenQuestion(String question) {
		super(question);
	}

	@Override
	public void onResponse() {
		getHandlers().get(0).handle(this);
	}

}
