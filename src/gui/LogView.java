package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Controller;
import java.awt.Dimension;
import java.util.Collections;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class LogView extends JPanel implements Observer{
		private JTextArea text = new JTextArea();
		private JScrollPane jScrollPane = new JScrollPane();
		public LogView(){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		this.setOpaque(true);
		this.setVisible(true);

		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		jScrollPane.setBackground(Color.BLACK);
		jScrollPane.setForeground(Color.WHITE);
		jScrollPane.setPreferredSize(new Dimension(350, 185));
		text.setEditable(false);
		jScrollPane.getViewport().add(text);
		this.add(jScrollPane, BorderLayout.NORTH);
		
		
	}

	@Override
	public void reDraw() {
		text.setText("");
		ArrayList<String> log = Controller.getInstance().getLog();
                Collections.reverse(log);
		for (String s : log){
			text.setText(text.getText()+s.toString()+"\n");
		}
	}
}
