package gui;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import commands.ControllerCommand;
import controller.Controller;




@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener{

	private DrawView drawView = new DrawView();
	private LogView logView = new LogView();
	private ListView listView = new ListView();
	private JPanel commandPanel = new JPanel();
	private TextField command_text_box = new TextField("", 40);
	private JButton command_button = new JButton("execute");
	private JPanel headPanel = new JPanel();

	public static void main(String[] args) 
	{

		Main inst = new Main();
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
		

	}

	public Main(){
		initGUI();   
		Controller.getInstance(drawView, listView, logView);
		Controller.getInstance().addViews(logView);
	}

	public void initGUI(){

		headPanel.setLayout(new BorderLayout());
		headPanel.setSize(new Dimension(600, 600));
		
		drawView.setPreferredSize(new Dimension(600, 300));
		logView.setPreferredSize(new Dimension(400, 200));
		listView.setPreferredSize(new Dimension(300, 200));
		commandPanel.setPreferredSize(new Dimension(300, 100));
		
		commandPanel.add(command_text_box);
		commandPanel.add(command_button);
		
		command_button.addActionListener(this);
		
		headPanel.add(drawView, BorderLayout.NORTH);
		headPanel.add(logView, BorderLayout.EAST);
		headPanel.add(listView, BorderLayout.WEST);
		headPanel.add(commandPanel, BorderLayout.SOUTH);
		
		this.getContentPane().add(headPanel);
		
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		



	}

	public void actionPerformed(ActionEvent e){
		if(!command_text_box.equals("")){
			String command = command_text_box.getText();
			ControllerCommand f = new ControllerCommand(command);
			f.createCommand();
			
		}
	}
}
