package domain;

import java.util.ArrayList;
import gui.Observable;
import gui.Observer;

public class Wagon implements Observable {
	private String id;
	private Type type;
	public int numseats;
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public Wagon(String id, Type tp, int numseats){
		this.id = id;
		this.type = tp;
		this.numseats = numseats;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setNumseats(int numseats){

		this.numseats = numseats;
	}
	
	public String getId() {
		return id;
	}
	
	public void setType(Type type) {

		this.type = type;
	}
	
	public Type getType() {
		return type;
	}
	
	public void notifyObservers() {
		// Send notify to all Observers
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.refreshData();
		}
	}
	
	@Override
	public void addViews(Observer obs) {
		observers.add(obs);

	}

	public int getNumSeats(){
		return numseats;
	}
	
	@Override
	public void unRegister(Observer obs) {
		observers.remove(obs);	
	}
	
	
}
