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
	private static Img_display img_display;
	private static List_display list_display;
	private static Log_display log_display;

	private ArrayList<Train> trains = new ArrayList<Train>();
	private ArrayList<Wagon> wagons = new ArrayList<Wagon>();
	private ArrayList<Type> types = new ArrayList<Type>();
	ArrayList<String> log = new ArrayList<String>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private static Controller instance;
	String now = LocalTime.now().toString();

	private Controller(Img_display imgdsply, List_display lstdsply, Log_display lgdsply) {
		setImg_display(imgdsply);
		setList_display(lstdsply);
		setLog_display(lgdsply);
	}

	public static synchronized Controller getInstance(Img_display imgdsply, List_display lstdsply,
			Log_display lgdsply) {
		if (instance == null)
			instance = new Controller(imgdsply, lstdsply, lgdsply);
		return instance;
	}

	public static synchronized Controller getInstance() {
		return instance;
	}

	// Log Funtion
	public void addToLogFile(String text) throws IOException {
		File file = new File("C:\\Users\\Kevin\\git\\Assignment2PAFGroep5V3\\logbook.txt");

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
		t.register(img_display);
		t.register(list_display);
		trains.add(t);
		log.add("Train " + t.getId() + " created");
		try {
			addToLogFile("Train " + t.getId() + " created");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		t.notifyObservers();
	}

	public void createWagon(String id, Type type, int numseats) {
		Wagon w = new Wagon(id, type, numseats);
		w.register(img_display);
		w.register(list_display);
		wagons.add(w);
		log.add("Wagon " + w.getId() + " created with " + numseats + " seats");
		try {
			addToLogFile("Wagon " + w.getId() + " created with " + numseats + " seats");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		w.notifyObservers();
	}

	public void createType(String id) {
		Type t = new Type(id);
		t.register(list_display);
		types.add(t);
		log.add("Type " + t.getId() + " created");
		try {
			addToLogFile("Type " + t.getId() + " created");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.notifyObservers();
		t.notifyObservers();
	}

	// check whether item exists
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

	// Get functions
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

	// Delete functions
	public void deleteWagon(String id) {
		for (Train t : trains) {
			if (t.checkWagons(id)) {
				Wagon w = getWagon(id);
				t.removeWagon(w);
				log.add(id + " removed from " + t.getId());
				try {
					addToLogFile("Wagon " + id + " removed from Train " + t.getId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Wagon wg = getWagon(id);
		wagons.remove(wg);
		log.add(id + " deleted");
		try {
			addToLogFile("Wagon " + id + " deleted");
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
				JOptionPane.showMessageDialog(null, "Type is connected to a wagon\n Delete " + w.getId() + " first",
						"ERROR", JOptionPane.ERROR_MESSAGE);
				delete = false;
			}
		}
		if (delete) {
			Type t = getType(id);
			types.remove(t);
			log.add("Type: " + id + " deleted");
			try {
				addToLogFile("Type " + id + " deleted");
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
		log.add("Train: " + id + " deleted");
		try {
			addToLogFile("Train " + id + " deleted");
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
			log.add(wgn + " already attached to " + trn);
		} else {
			train.addWagon(wagon);
			log.add("Wagon "+wgn + " attached to Train " + trn);
			try {
				addToLogFile("Wagon "+wgn + " attached to Train " + trn);
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
				addToLogFile("Wagon "+wgn + " removed to Train" + trn);
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
				addToLogFile("Train " + name + " has " + numseats + " seats");
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
				addToLogFile("Wagon " + name + " has " + numseats + " seats");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.add(" Wagon does not exist ");
		}
		this.notifyObservers();
		w.notifyObservers();
		}
	

	// Display getters and setters
	public static void setLog_display(Log_display log_display) {
		Controller.log_display = log_display;
	}

	public static Log_display getLog_display() {
		return log_display;
	}

	public static void setList_display(List_display list_display) {
		Controller.list_display = list_display;
	}

	public static List_display getList_display() {
		return list_display;
	}

	public static void setImg_display(Img_display img_display) {
		Controller.img_display = img_display;
	}

	public static Img_display getImg_display() {
		return img_display;
	}

	// Observerable functions
	public void notifyObservers() {
		// Send notify to all Observers
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.refreshData();
		}
	}

	public void register(Observer obs) {
		observers.add(obs);

	}

	public void unRegister(Observer obs) {
		observers.remove(obs);
	}

}
