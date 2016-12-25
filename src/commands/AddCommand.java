package commands;
import javax.swing.JOptionPane;

import controller.Controller;

public class AddCommand implements Command{

	private String command;
	public AddCommand(String cmd){
		this.command = cmd;
		this.execute(this.command);
	}
	public void execute(String cmd) {
		int spaceFirst = cmd.indexOf(' ');
		int spaceLast = cmd.lastIndexOf(' ');
		if(spaceLast < 0){
			System.out.println("Not a valid commands.\n No train selected.");
		}else{
			String addItem = cmd.substring(0, spaceFirst);
			String toItem = cmd.substring(spaceLast+1);
			if(!Controller.getInstance().checkWagons(addItem)){
				System.out.println("Wagon "+addItem+" does not exist");
			}
			else if(!Controller.getInstance().checkTrains(toItem)){
				System.out.println("Train "+toItem+" does not exist");
			}else{
				Controller.getInstance().addWagonToTrain(addItem, toItem);
			}
			
		}
		
		
	}

}
