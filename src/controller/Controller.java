package controller;

import domain.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import domain.Train;
import gui.*;

public class Controller implements Observable {
	private static DrawView drawView;
	private static ListView listView;
	private static LogView logView;

	private ArrayList<Train> trains = new ArrayList<>();
	private ArrayList<Wagon> wagons = new ArrayList<>();
	private ArrayList<Type> types = new ArrayList<>();
	ArrayList<String> log = new ArrayList<String>();
	private ArrayList<Observer> observers = new ArrayList<>();
	private static Controller instance;
	String now = LocalTime.now().toString();

	private Controller(DrawView drawView, ListView listView, LogView logView) {
		setDrawView(drawView);
		setListView(listView);
		setLogView(logView);
	}

	public static synchronized Controller getInstance(DrawView drawView, ListView listView,
													  LogView logView) {
		if (instance == null)
			instance = new Controller(drawView, listView, logView);
		return instance;
	}

	public static synchronized Controller getInstance() {
        return instance;
	}

	public void log(String text) throws IOException {
		File file = new File("logboek.txt");

		if (!file.exists()) {
			file.createNewFile();
		}

		BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
		String logLine = now + " " + text;
		output.append(logLine);
		output.newLine();
		output.close();
	}

	// Create Functions
	public void createTrain(String id) {
		Train t = new Train(id);
		t.addViews(drawView);
		t.addViews(listView);
		trains.add(t);
		log.add("Trein " + t.getId() + " aangemaakt");
		try {
			log("Trein " + t.getId() + " aangemaakt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		t.notifyObservers();
	}

	public void createWagon(String id, Type type, int numseats) {
		Wagon w = new Wagon(id, type, numseats);
		w.addViews(drawView);
		w.addViews(listView);
		wagons.add(w);
		log.add("Wagon " + w.getId() + " aangemaakt met " + numseats + " plaatsen");
		try {
			log("Wagon " + w.getId() + " aangemaakt met " + numseats + " plaatsen");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		w.notifyObservers();
	}

	public void createType(String id) {
		Type t = new Type(id);
		t.addViews(listView);
		types.add(t);
		log.add("Type " + t.getId() + " aangemaakt");
		try {
			log("Type " + t.getId() + " aangemaakt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		t.notifyObservers();
	}

	public boolean checkTrains(String name) {
		for (Train t : trains) {
			if (t.getId().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkWagons(String name) {
		for (Wagon w : wagons) {
			if (w.getId().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkTypes(String name) {
		for (Type t : types) {
			if (t.getId().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Train> getTrains() {
		return trains;

	}

	public ArrayList<Wagon> getWagons() {
		return wagons;

	}

	public ArrayList<String> getLog() {
		return log;
	}

	public ArrayList<Type> getTypes() {
		return types;
	}

	public Train getTrain(String name) {
		for (Train t : trains) {
			if (t.getId().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public Wagon getWagon(String name) {
		for (Wagon w : wagons) {
			if (w.getId().equals(name)) {
				return w;
			}
		}
		return null;
	}

	public Type getType(String name) {
		for (Type t : types) {
			if (t.getId().equals(name)) {
				return t;
			}
		}
		return null;
	}

	public void deleteWagon(String id) {
		for (Train t : trains) {
			if (t.checkWagons(id)) {
				Wagon w = getWagon(id);
				t.removeWagon(w);
				log.add(id + " verwijderd van " + t.getId());
				try {
					log("Wagon " + id + " verwijderd van Trein " + t.getId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Wagon wg = getWagon(id);
		wagons.remove(wg);
		log.add(id + " verwijderd");
		try {
			log("Wagon " + id + " verwijderd");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		wg.notifyObservers();
	}

	public void deleteType(String id) {
		Boolean delete = true;
		for (Wagon w : wagons) {
			if (w.getType().equals(id)) {
				JOptionPane.showMessageDialog(null, "Type hoort bij een wagon\n Verwijder deze wagon: " + w.getId() + " eerst",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				delete = false;
			}
		}
		if (delete) {
			Type t = getType(id);
			types.remove(t);
			log.add("Type " + id + " verwijderd");
			try {
				log("Type " + id + " verwijderd");
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.notifyObservers();
			t.notifyObservers();

		}

	}

	public void deleteTrains(String id) {
		Train tr = getTrain(id);
		trains.remove(tr);
		log.add("Train " + id + " deleted");
		try {
			log("Train " + id + " deleted");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		tr.notifyObservers();
	}

	public void addLogItem(String item) {
		log.add(item);
	}

	public void addWagonToTrain(String wgn, String trn) {
		Wagon wagon = getWagon(wgn);
		Train train = getTrain(trn);
		if (train.checkWagons(wgn)) {
			log.add(wgn + " already belongs to " + trn);
		} else {
			train.addWagon(wagon);
			log.add("Wagon "+wgn + " attached to Train " + trn);
			try {
				log("Wagon "+wgn + " attached to Train " + trn);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.notifyObservers();
		train.notifyObservers();

	}

	public void removeWagonFromTrain(String wgn, String trn) {
		Wagon wagon = getWagon(wgn);
		Train train = getTrain(trn);
		if (train.checkWagons(wgn)) {
			train.removeWagon(wagon);
			log.add("Wagon " + wgn + " removed from Train " + trn);
			try {
				log("Wagon "+wgn + " removed to Train" + trn);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.add(wgn + " not attached to " + trn);
		}
		this.notifyObservers();
		train.notifyObservers();
	}
	
	public void numseatsTrain(String name){
		Train t = getTrain(name);
		
		if(Controller.getInstance().checkTrains(name)){
			int numseats = t.getNumseats();
			log.add("Train " + name + " has " + numseats + " seats");
			try {
				log("Train " + name + " has " + numseats + " seats");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.add(" Train does not exist ");
		}
		this.notifyObservers();
		t.notifyObservers();
		}
	
	public void numseatsWagon(String name){
		Wagon w = getWagon(name);
		
		if(Controller.getInstance().checkWagons(name)){
			int numseats = w.getNumSeats();
			log.add("Wagon " + name + " has " + numseats + " seats");
			try {
				log("Wagon " + name + " has " + numseats + " seats");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.add(" Wagon does not exist ");
		}
		this.notifyObservers();
		w.notifyObservers();
		}
	

	public static void setLogView(LogView logView) {
		Controller.logView = logView;
	}

	public static LogView getLogView() {
		return logView;
	}

	public static void setListView(ListView listView) {
		Controller.listView = listView;
	}

	public static ListView getListView() {
		return listView;
	}

	public static void setDrawView(DrawView drawView) {
		Controller.drawView = drawView;
	}

	public static DrawView getDrawView() {
		return drawView;
	}

	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = observers.get(i);
			observer.refreshData();
		}
	}

	public void addViews(Observer obs) {
		observers.add(obs);

	}

	public void deleteViews(Observer obs) {
		observers.remove(obs);
	}

}
