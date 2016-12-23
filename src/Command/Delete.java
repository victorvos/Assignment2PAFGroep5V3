package Command;

import javax.swing.JOptionPane;

import controller.Controller;

public class Delete implements Command{

	private String command;
	public Delete(String cmd){
		this.command = cmd;
		this.Execute(this.command);
	}
	@Override
	public void Execute(String cmd) {
		
		int space = cmd.indexOf(' ');
		if(space < 1){
			JOptionPane.showMessageDialog(null, "Not a valid command", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else{
			String type = cmd.substring(0, space);
			String id = cmd.substring((space+1));
			
			if (type.equalsIgnoreCase("Train")){
				if(Controller.getInstance().checkTrains(id)){
					Controller.getInstance().deleteTrains(id);
				}else{
					JOptionPane.showMessageDialog(null, "Train "+id+" does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else if (type.equalsIgnoreCase("Wagon")){
				if(Controller.getInstance().checkWagons(id)){
					Controller.getInstance().deleteWagon(id);
				}else{
					JOptionPane.showMessageDialog(null, "Wagon "+id+" does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else if (type.equalsIgnoreCase("Type")){
				if(Controller.getInstance().checkTypes(id)){
					Controller.getInstance().deleteType(id);
				}else{
					JOptionPane.showMessageDialog(null, "Type "+id+" does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Not a valid command", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}


