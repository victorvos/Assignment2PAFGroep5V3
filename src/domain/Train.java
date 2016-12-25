package domain;

import java.util.ArrayList;
import gui.Observable;
import gui.Observer;

public class Train implements Observable {
	private ArrayList<Wagon> wagons = new ArrayList<>();
	private String id;
	private ArrayList<Observer> observers = new ArrayList<>();

	public Train(String id) {
		this.id = id;
		this.notifyObservers();
	}

	public void addWagon(Wagon wagon) {
		this.wagons.add(wagon);
		notifyObservers();
	}

	public ArrayList<Wagon> getWagons() {
		return wagons;
	}
	
	public int getNumseats(){
		int numseats = 0;
		for(Wagon wagon : wagons){
			numseats += wagon.getNumSeats();
		}
	
		return numseats;
	}

	public Boolean checkWagons(String id) {
		for (Wagon w : wagons) {
			if (w.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.refreshData();
		}
	}

	@Override
	public void addViews(Observer obs) {
		observers.add(obs);
	}

	@Override
	public void deleteViews(Observer obs) {
		observers.remove(obs);
	}

	public void removeWagon(Wagon wagon) {
		this.wagons.remove(wagon);
	}

}
