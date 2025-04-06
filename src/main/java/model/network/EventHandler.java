package model.network;

@FunctionalInterface
public interface EventHandler {
	public void handle(Event event);
}
