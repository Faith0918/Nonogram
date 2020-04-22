package Nonogram;

public class Core {
	View view;
	PictureMap map;
	public Core() {
		view = new View();
		map = new PictureMap();
		start();
	}
	public static void main(String[] args) {
		Core core = new Core();
	}
	private void start() {
		gameStart();
		
	}
	private void gameStart() {
		
//		map.newMap();
		System.out.println("new game");
		view.resetViewModel();
		System.out.println("reset view model");
		view.drawMap();
		
	}
	public Cell getCell(int cellx, int celly) {
		Cell cell = map.getCell(cellx, celly);
		return cell;
	}
	public void paintCell(Cell cell) {
		map.paintCell(cell);
		System.out.println("?????");
		view.paintCellView(cell);
		//TODO test every single time the user paints
		
	}
	public void XCell(Cell cell) {
		map.XCell(cell);
		view.XCellView(cell);
		
	}
}
