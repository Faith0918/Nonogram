package Nonogram;

public class Cell {
	private final int x;
	private final int y;
	private final int property;
	// 0: non-colored, 1: colored, 2: X
	public static final int Blank = 0;
	public static final int Colored = 1;
	public static final int X = 2;
	private int state;
	// 0: non-colored, 1: colored, 2: X
	public Cell(int x, int y, int property) {
		this.x = x;
		this.y = y;
		this.property = property;
		
	}
}
