package Nonogram;

import java.util.Random;

public class PictureMap {
	Cell[][] mapArray;
	public void newMap() {
		newArray();
		assignColorCell();
		assginBlankCell();
		testMap();
		
	}

	private void assginBlankCell() {
		for(int i =0; i<20; i++) {
			for(int j=0; j<20; j++) {
				if(mapArray[i][j] == null) {
					mapArray[i][j] = new Cell(i, j, Cell.Blank);
				}
			}
		}
		
	}

	private void testMap() {
		
	}

	private void assignColorCell() {
		int num = 0;
		Random random= new Random();
		while(num<30) {
			int x = random.nextInt(20);
			int y = random.nextInt(20);
			if(mapArray[x][y] != null){
				continue;
			}
			else {
				mapArray[x][y] = new Cell(x, y, Cell.Colored); 
				num++;
			}
		}
	}

	private void newArray() {
		mapArray = new Cell[20][20];
	}

	public Cell get(int cellx, int celly) {
		return mapArray[cellx][celly];
	}

	public void paintCell(Cell cell) {
		// TODO Auto-generated method stub
		
	}

	public void XCell(Cell cell) {
		// TODO Auto-generated method stub
		
	}

}
