package commands;

import javax.swing.JOptionPane;

public class ControllerCommand {
	
	private String command;

	public ControllerCommand(String command){
		this.command = command;
	}
	
	public Command createCommand(){
		int plekken = command.indexOf(' ');
		
		if(plekken > 0){
			String command1 = command.substring(0, plekken);
			String command2 = command.substring((plekken+1));
			if(command1.equalsIgnoreCase("new")){
				return new NewCommand(command2);
			}

			else if(command1.equalsIgnoreCase("add")){
				return new AddCommand(command2);
			}

			else if(command1.equalsIgnoreCase("delete")){
				return new DeleteCommand(command2);
			}

			else if(command1.equalsIgnoreCase("remove")){
				return new RemoveCommand(command2);
			}

			else if(command1.equalsIgnoreCase("get")){
				return new GetCommand(command2);
			}
			
			else{
				System.out.println("Geen valide command.");
			}
		}
		else{
			System.out.println("Geen valide command.");
		}
		return null;
	}
}
