package Nonogram;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;



public class DrawComponent extends JComponent{
	Cell[][] cellView;
	
	public void setViewModel(Cell[][] cellView) {
		this.cellView = cellView;
	}
	
	public Cell[][] getViewModel() {
		return cellView;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		System.out.println(cellView[0][0].getProperty());
		for(int r = 0;r < 20; r++) {
			for(int c = 0; c < 20; c++) {
				System.out.println(cellView);
				System.out.println(cellView[0][0].getProperty());
				if(cellView[r][c].getProperty() == Cell.Blank) {
					g.setColor(Color.black);
					g.drawRect(r*20, c*20, 20, 20);
				}
				else if(cellView[r][c].getProperty() == Cell.Colored){
					g.setColor(Color.DARK_GRAY);
					g.fillRect(r*20, c*20, 20, 20);
				}	
				else {
					g.setColor(Color.BLACK);
					g.setFont(new Font("¸¼Àº°íµñ", Font.BOLD,25));
					g.drawString("X", r*20, c*20+20);
				}
				
			}
		}
		
	}
}
