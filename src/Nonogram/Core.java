package Nonogram;

import java.util.LinkedList;

public class Core {
	View view;
	PictureMap map;
	NonogramUserListener listener;
	int size = 15;
	
	public Core() {
		view = new View(this);
		map = new PictureMap();
		listener = new NonogramUserListener(this);
		view.addListener(listener);
		start();
	}
	public static void main(String[] args) {
		Core core = new Core();
	}
	
	public int getSize() {
		System.out.println("!!!");
		return this.size;
	}
	
	private void start() {
		gameStart();
		
	}
	public void gameStart() {
		
		map.newMap(size);
		System.out.println("new game");
		view.resetViewModel(size);
		view.setNumbers(map.getNumbers());
		System.out.println("reset view model");
		view.drawMap();
		
	}
	
	public Cell getCell(int cellx, int celly) {
		Cell cell = view.getCell(cellx, celly);
		return cell;
	}
	public void paintCell(Cell cell) {
		if(cell.getProperty()==Cell.Blank) {
			cell.setProperty(Cell.Colored);
		}
		else {
			cell.setProperty(Cell.Blank);
		}
		view.changeViewModel(cell);
		view.repaint();
		//TODO test every single time the user paints
		
	}
	public void XCell(Cell cell) {
		if(cell.getProperty()==Cell.Blank) {
			cell.setProperty(Cell.X);
		}
		else {
			cell.setProperty(Cell.Blank);
		}
		view.changeViewModel(cell);
		view.repaint();
		
	}
	public void checkAnswer() {
		if(map.checkAnswer(view.getViewModel())) {
			view.popup("correct!");
		}
		else {
			view.popup("incorrect!");
		}
		
	}
	
}
