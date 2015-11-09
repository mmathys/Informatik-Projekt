package io.github.mmathys.Projekt;

import io.github.mmathys.Projekt.actions.Action;
import io.github.mmathys.Projekt.interact.Question;

public interface GameParentElement {
	public void onFallback(Action handler);
	public void ask(Question q);
	public void handleError(Question q, boolean retry);
}
