package Nonogram;

public class Line {
	private int index;
	private Cell[] cells;
	public Line(int index, Cell[] cells) {
		this.index = index;
		this.cells = cells;
	}
	public int getIndex() {
		return index;
	}
	public Cell[] getCells() {
		return cells;
	}
}
