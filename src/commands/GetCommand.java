package commands;


import javax.swing.JOptionPane;
import controller.Controller;
import domain.Train;
import domain.Wagon;

public class GetCommand implements Command{

	private String command;
	Train t;
	Wagon w;
	public GetCommand(String cmd){
		this.command = cmd;
		this.execute(this.command);
	}
	@Override
	public void execute(String cmd) {
		int spaceLast = cmd.lastIndexOf(' ');
		if(spaceLast < 0){
			JOptionPane.showMessageDialog(null, "Not a valid commands.\nNo train of wagon is selected.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}else{
			String type = cmd.split(" ")[0];
			String name = cmd.split(" ")[1];
			if(type.equals("train")){
				Controller.getInstance().numseatsTrain(name);
			}
			else if(type.equals("wagon")){
				Controller.getInstance().numseatsWagon(name);
			}
			else{
				JOptionPane.showMessageDialog(null, "Please choose train or wagon", "ERROR", JOptionPane.ERROR_MESSAGE);	
			}
		}

	}

}