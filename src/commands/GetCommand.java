package commands;


import javax.swing.JOptionPane;
import controller.Controller;
import domain.Train;
import domain.Wagon;

public class GetCommand implements Command{

	private String command;

	public GetCommand(String cmd){
		this.command = cmd;
		this.execute(this.command);
	}
	@Override
	public void execute(String cmd) {
		int lastIndex = cmd.lastIndexOf(' ');
		if(lastIndex < 0){
			System.out.println("Geen valide Command, er is geen Wagon of Trein geselecteerd.");
		}else{
			String split1 = cmd.split(" ")[0];
			String split2 = cmd.split(" ")[1];
			if(split1.equals("train")){
				Controller.getInstance().numseatsTrain(split2);
			}
			else if(split1.equals("wagon")){
				Controller.getInstance().numseatsWagon(split2);
			}
			else{
				System.out.println("Kies alsjeblieft een trein of wagon.");
			}
		}

	}

}