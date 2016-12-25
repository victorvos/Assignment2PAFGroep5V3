package commands;

import javax.swing.JOptionPane;

public class ControllerCommand {
	
	private String command;
	public ControllerCommand(String command){
		this.command = command;
	}
	
	public Command createCommand(){
		int space = command.indexOf(' ');
		
		if(space > 0){
			String trigger = command.substring(0, space);
			String command2 = command.substring((space+1));
			if(trigger.equalsIgnoreCase("new")){
				return new NewCommand(command2);
			}
			else if(trigger.equalsIgnoreCase("add")){
				return new AddCommand(command2);
			}
			else if(trigger.equalsIgnoreCase("get")){
				return new GetCommand(command2);
			}
			else if(trigger.equalsIgnoreCase("delete")){
				return new DeleteCommand(command2);
			}
			else if(trigger.equalsIgnoreCase("remove")){
				return new RemoveCommand(command2);
			}
			
			else{
				JOptionPane.showMessageDialog(null, "Not a valid commands", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Not a valid commands", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
