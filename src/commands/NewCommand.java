package commands;

import javax.swing.JOptionPane;

import controller.Controller;
public class NewCommand implements Command{


	private String command;
	public NewCommand(String cmd){
		this.command = cmd;
		this.execute(this.command);
	}
	
	public void execute(String cmd) {
		int space = cmd.indexOf(' ');
		if(space > 0){
			String substring1 = cmd.substring(0, space);
			String id = cmd.substring((space+1));
			if(substring1.equalsIgnoreCase("train")){
				if(Controller.getInstance().checkTrains(id)){
					System.out.println("Trein "+id+" bestaat al ");
				}else{
					this.createTrain(id);
				}
				
			}else if(substring1.equalsIgnoreCase("wagon")){
				if(cmd.indexOf("numseats") > 1){
					String numseats = cmd.split(" ")[4];
					int seatsint = Integer.parseInt(numseats);
					this.createWagon(cmd, seatsint);
				}
				else{
					this.createWagon(id, 20);	
				}
				
			}else if(substring1.equalsIgnoreCase("type")){
				if(Controller.getInstance().checkTypes(id)){
					System.out.println("Type "+id+" bestaat al.");
				}else{
					if (cmd.indexOf("numseats") > 1){
						int seatsIndex = cmd.lastIndexOf(' ');
						int endIndexTypeID = cmd.indexOf(" ", space+1);
						String seats = cmd.substring(seatsIndex+1);
						id = cmd.substring(space+1, endIndexTypeID);
						int seatsint = Integer.parseInt(seats);
						this.createType(id, seatsint);
					}else{
						this.createType(id, 0);
					}
					
				}
				
			}else{
				System.out.println("Dit is geen valide command.");
			}
		}else{
			System.out.println("Dit is geen valide command.");
		}
		
	}
	private void createTrain(String id){
		Controller.getInstance().createTrain(id);
	}
	private void createWagon(String cmd, int numseats){
		
		int space = cmd.indexOf(' ');
		if(space < 1){
			System.out.println("Dit is geen valide command.");
		}else{
			String name = cmd.split(" ")[1];
			if(Controller.getInstance().checkWagons(name)){
				System.out.println("Wagon "+name+" bestaat al");
			}else{
				String typeId = cmd.split(" ")[2];
				if(!Controller.getInstance().checkTypes(typeId)){
					System.out.println("Type "+typeId+" bestaat nog niet. Maak deze eerst aan.");
				}else{
					Controller.getInstance().createWagon(name, Controller.getInstance().getType(typeId),numseats);
				}
				
			}
			
		}
		
	}

	private void createType(String id, int numseats){
		Controller.getInstance().createType(id);
	}
}
