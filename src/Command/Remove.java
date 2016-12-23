package Command;

import controller.Controller;

public class Remove implements Command {

	private String command;
	public Remove(String cmd){
		this.command = cmd;
		this.Execute(this.command);
	}
	@Override
	public void Execute(String cmd) {
		int spaceFirst = cmd.indexOf(' ');
		int spaceLast = cmd.lastIndexOf(' ');
		String removeItem = cmd.substring(0, spaceFirst);
		String fromItem = cmd.substring(spaceLast+1);

		Controller.getInstance().removeWagonFromTrain(removeItem, fromItem);

	}
}
