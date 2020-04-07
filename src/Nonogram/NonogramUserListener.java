package Nonogram;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class NonogramUserListener implements MouseListener, ActionListener{
	Core core;
	public NonogramUserListener(Core core) {
		this.core = core;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point p = e.getPoint();
		Cell cell = convertPointToCell(p.x, p.y);
		if(e.getButton() == MouseEvent.BUTTON1) {
			core.paintCell(cell);
		}
		else if(e.getButton() == MouseEvent.BUTTON3) {
			core.XCell(cell);
		}
		
	}
	private Cell convertPointToCell(int x, int y) {
		int cellx = 0,celly = 0;
		cellx = (int)(x/20);
		celly = (int)(y/20);
		Cell c = core.getCell(cellx,celly);
		return c;
	}
	
}
