package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.Controller;
import domain.Train;
import domain.Wagon;


@SuppressWarnings("serial")
public class DrawView extends JPanel implements Observer{

	private int thisTrain =0;
	private int OFFSET=120;
	private int thisWagon = 0;
	private int TLENGTH = 100;

	public DrawView(){
		init();
	}
	
	public void init(){
		this.setLayout(new FlowLayout());
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
		this.setVisible(true);
		
	}

	@Override
	public void reDraw() {
		thisTrain = 0;
		Controller s = Controller.getInstance();
		ArrayList<Train> trains = s.getTrains();
		Graphics g = this.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (Train t : trains){
			drawTrain(t.getId());
			thisWagon = 1;
			for (Wagon w: t.getWagons()){
				drawWagon(w.getId());
				thisWagon++;
			}
			thisTrain++;
		}


	}

	private void drawWagon(String id) {
		Graphics wagonGraphics = this.getGraphics();
		wagonGraphics.setColor(Color.LIGHT_GRAY);
		wagonGraphics.fillRect(30+ thisWagon * TLENGTH,80+ thisTrain *OFFSET,80,40);
		wagonGraphics.setColor(Color.BLUE);
		wagonGraphics.fillRoundRect(35+ thisWagon * TLENGTH, 120+ thisTrain *OFFSET, 20, 20, 20, 20);
		wagonGraphics.fillRoundRect(80+ thisWagon * TLENGTH, 120+ thisTrain *OFFSET, 20, 20, 20, 20);
		wagonGraphics.drawString(id,40+ thisWagon * TLENGTH,105+ thisTrain *OFFSET);
		
	}

	public void drawTrain(String t) {
		this.setBackground(Color.WHITE);
		Graphics trainGraphics = this.getGraphics();
		trainGraphics.setColor(Color.LIGHT_GRAY);
		trainGraphics.fillRect(30,80+ thisTrain *OFFSET,80,40);
		trainGraphics.fillRect(80,60+ thisTrain *OFFSET,30,30);
		trainGraphics.drawRoundRect(85, 40+ thisTrain *OFFSET, 20, 20, 20, 20);
		trainGraphics.drawRoundRect(85, thisTrain *OFFSET, 40, 40, 40, 40);
		trainGraphics.setColor(Color.GREEN);
		trainGraphics.fillRoundRect(35, 120+ thisTrain *OFFSET, 20, 20, 20, 20);
		trainGraphics.fillRoundRect(80, 120+ thisTrain *OFFSET, 20, 20, 20, 20);
		trainGraphics.drawString(t, 40, 105 + thisTrain *OFFSET);


	}

}