package Command;

import javax.swing.JOptionPane;

import controller.Controller;

public class Get implements Command{

	private String command;
	public Get(String cmd){
		this.command = cmd;
		this.Execute(this.command);
	}
	@Override
	public void Execute(String cmd) {
		int spaceLast = cmd.lastIndexOf(' ');
		if(spaceLast < 0){
			JOptionPane.showMessageDialog(null, "Not a valid command.\n No type selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else{

			String toItem = cmd.substring(spaceLast+1);
			
			Controller.getInstance().getSeatsFromType(toItem);
		}

	}

}