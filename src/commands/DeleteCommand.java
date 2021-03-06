package commands;

import controller.Controller;

public class DeleteCommand implements Command{

	private String command;
	public DeleteCommand(String cmd){
		this.command = cmd;
		this.execute(this.command);
	}
	@Override
	public void execute(String cmd) {
		
		int space = cmd.indexOf(' ');
		if(space < 1){
			System.out.println("Not a valid commands");
		}else{
			String type = cmd.substring(0, space);
			String id = cmd.substring((space+1));
			
			if (type.equalsIgnoreCase("Train")){
				if(Controller.getInstance().checkTrains(id)){
					Controller.getInstance().deleteTrains(id);
				}else{
					System.out.println("Train "+id+" does not exist");
				}
				
			}
			else if (type.equalsIgnoreCase("Wagon")){
				if(Controller.getInstance().checkWagons(id)){
					Controller.getInstance().deleteWagon(id);
				}else{
					System.out.println("Wagon "+id+" does not exist");
				}
				
			}
			else if (type.equalsIgnoreCase("Type")){
				if(Controller.getInstance().checkTypes(id)){
					Controller.getInstance().deleteType(id);
				}else{
					System.out.println("Type "+id+" does not exist");
				}
				
			}
			else {
				System.out.println("Geen valide command.");
			}
		}
		
	}

}


