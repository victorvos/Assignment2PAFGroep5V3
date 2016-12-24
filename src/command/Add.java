package command;
import javax.swing.JOptionPane;

import controller.Controller;

public class Add implements Command{

	private String command;
	public Add(String cmd){
		this.command = cmd;
		this.Execute(this.command);
	}
	public void Execute(String cmd) {
		int spaceFirst = cmd.indexOf(' ');
		int spaceLast = cmd.lastIndexOf(' ');
		if(spaceLast < 0){
			JOptionPane.showMessageDialog(null, "Not a valid command.\n No train selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else{
			String addItem = cmd.substring(0, spaceFirst);
			String toItem = cmd.substring(spaceLast+1);
			if(!Controller.getInstance().checkWagons(addItem)){
				JOptionPane.showMessageDialog(null, "Wagon "+addItem+" does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			else if(!Controller.getInstance().checkTrains(toItem)){
				JOptionPane.showMessageDialog(null, "Train "+toItem+" does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else{
				Controller.getInstance().addWagonToTrain(addItem, toItem);
			}
			
		}
		
		
	}

}
