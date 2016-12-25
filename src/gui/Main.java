package gui;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import commands.CommandFactory;
import controller.Controller;





public class Main extends JFrame implements ActionListener{

	private DrawView drawView = new DrawView();
	private LogView logView = new LogView();
	private ListView listView = new ListView();
	private JPanel commandPanel = new JPanel();
	private TextField commandTextField = new TextField("", 40);
	private JButton commandButton = new JButton("execute");
	private JPanel headPanel = new JPanel();

	public static void main(String[] args) 
	{
		Main inst = new Main();
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
	}

	public Main(){
		init();
		Controller.getInstance(drawView, listView, logView);
		Controller.getInstance().addViews(logView);
	}

	public void init(){

		headPanel.setLayout(new BorderLayout());
		headPanel.setSize(new Dimension(800, 600));
		
		drawView.setPreferredSize(new Dimension(600, 300));
		logView.setPreferredSize(new Dimension(400, 300));
		listView.setPreferredSize(new Dimension(300, 300));
		commandPanel.setPreferredSize(new Dimension(300, 100));
		
		commandPanel.add(commandTextField);
		commandPanel.add(commandButton);
		
		commandButton.addActionListener(this);
		
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
		if(!commandTextField.equals("")){
			String command = commandTextField.getText();
			CommandFactory controllerCommand = new CommandFactory(command);
			controllerCommand.createCommand();
		}
	}
}
