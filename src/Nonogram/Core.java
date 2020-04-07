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
		map.newMap();
		view.resetViewModel();
		view.drawMap();
		
	}
	public Cell getCell(int cellx, int celly) {
		Cell cell = map.get(cellx, celly);
		return cell;
	}
	public void paintCell(Cell cell) {
		map.paintCell(cell);
		view.paintCellView(cell);
		
	}
	public void XCell(Cell cell) {
		map.XCell(cell);
		view.XCellView(cell);
		
	}
}
