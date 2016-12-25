package gui;

public interface Observable {
	public void notifyObservers();

	public void addViews(Observer obs);

	public void unRegister(Observer obs);
}
