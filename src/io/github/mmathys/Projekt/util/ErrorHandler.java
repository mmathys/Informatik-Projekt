package io.github.mmathys.Projekt.util;

import io.github.mmathys.Projekt.interact.Question;

public interface ErrorHandler {
	public void handleError(Question q, boolean retry);
}
