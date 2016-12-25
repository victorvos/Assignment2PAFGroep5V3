package domain;

import java.util.ArrayList;

import gui.Observable;
import gui.Observer;

public class Type implements Observable{
	private String id;
	private int numberOfSeats = 20;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public Type(String id){
		this.id = id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.refreshData();
		}
	}
	@Override
	public void addViews(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void deleteViews(Observer observer) {
		observers.remove(observer);
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

}
