package commands;

import controller.Controller;

public class RemoveCommand implements Command {

	private String command;
	public RemoveCommand(String cmd){
		this.command = cmd;
		this.execute(this.command);
	}
	@Override
	public void execute(String cmd) {
		int spaceFirst = cmd.indexOf(' ');
		int spaceLast = cmd.lastIndexOf(' ');
		String removeItem = cmd.substring(0, spaceFirst);
		String fromItem = cmd.substring(spaceLast+1);

		Controller.getInstance().removeWagonFromTrain(removeItem, fromItem);

	}
}
