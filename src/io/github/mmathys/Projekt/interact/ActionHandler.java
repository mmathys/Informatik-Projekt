package io.github.mmathys.Projekt.interact;

import io.github.mmathys.Projekt.GameParentElement;

public interface ActionHandler {
	public void handle(Question q);
	public void bind(GameParentElement fallback);
}
