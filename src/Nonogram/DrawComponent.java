package Nonogram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;



public class DrawComponent extends JComponent{
	Cell[][] cellView;
	LinkedList<Integer>[][] numbers;
	
	public void setViewModel(Cell[][] cellView) {
		this.cellView = cellView;
	}
	
	public Cell[][] getViewModel() {
		return cellView;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int R = 200;
		int C = 200;
		
		for(int x = 0; x < 20; x++) {
			if(!numbers[x][0].isEmpty()) {
				for(int i = 0; i<numbers[x][0].size(); i++) {
					g.setColor(Color.gray);
					g.drawRect(R+x*20, i*20, 20, 20);
					g.setColor(Color.BLACK);
					g.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN,17));
					g.drawString(String.valueOf(numbers[x][0].get(i)), R+x*20, i*20+20);
					
				}
			}
			if(!numbers[x][1].isEmpty()) {
				for(int i = 0; i<numbers[x][1].size(); i++) {
					g.setColor(Color.gray);
					g.drawRect(i*20, C+x*20, 20, 20);
					g.setColor(Color.BLACK);
					g.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN,17));
					g.drawString(String.valueOf(numbers[x][1].get(i)),i*20, C+x*20+20);
					
				}
			}
		}
		for(int r = 0;r < 20; r++) {
			for(int c = 0; c < 20; c++) {
				if(cellView[r][c].getProperty() == Cell.Blank) {
					g.setColor(Color.black);
					g.drawRect(R+r*20, C+c*20, 20, 20);
				}
				else if(cellView[r][c].getProperty() == Cell.Colored){
					g.setColor(Color.DARK_GRAY);
					g.fillRect(R+r*20, C+c*20, 20, 20);
				}	
				else {
					g.setColor(Color.BLACK);
					g.setFont(new Font("¸¼Àº°íµñ", Font.BOLD,25));
					g.drawString("X", R+r*20, C+c*20+20);
				}
				
			}
		}
		
	}

	public void setNumbers(LinkedList<Integer>[][] linkedLists) {
		numbers = linkedLists;
		
	}
}
