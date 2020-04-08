package Nonogram;

import java.util.LinkedList;
import java.util.Random;

public class PictureMap {
	Cell[][] mapArray;
	int[][] numberArray;// numberArray[i][0] : x axis numbers, numberArray[i][1] : y axis numbers
	public void newMap() {
		newArray();
		assignColorCell();
		assignNumbers();
		testMap();
		
	}

	private void assignNumbers() {
		numberArray = new int[20][2];
		for(int i = 0; i<20; i++) {
			int x = 0;
			int y = 0;
			
			for(int j = 0; j<20; j++) {
				if(mapArray[i][j].getProperty() == Cell.Colored) {
					x++;
				}
				if(mapArray[j][i].getProperty() == Cell.Colored) {
					y++;
				}
			}
			numberArray[i][0] = x;
			numberArray[i][1] = y;
		}
		
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

	private boolean testMap() {
		int answer_quantity = 0;
		Cell[][] answer = new Cell[20][20];
		Cell[][] baseMap = paintInevitables(mapArray);
		Cell[][] candi = baseMap;
		LinkedList<Cell[]>[] cases = new LinkedList[20];
		for(int i = 0; i<20; i++) {
			cases[i] = generateCases(baseMap, i);
		}
		int line_num = 0;
		while(line_num<20) {
			for( Cell[] lineX : cases[line_num]) {
				candi[line_num] = lineX;
 				if(testAbility(candi)) {
 					answer[line_num] = lineX;
					break;
				}
				else {
					cases[line_num].remove(lineX);
				}
			}
			if(cases[line_num].isEmpty()&&line_num >0) {
				line_num --;
			}
			else if(cases[line_num].isEmpty()&& line_num == 0) {
				return true;
			}
			else {
				if(line_num == 19) {
					answer_quantity++;
					if(answer_quantity>2) {
						return false;
					}
					else {
						//TODO 현재 모든 경우의 수 삭제
					}
				}
				else {
					line_num++;
				}
			}
		}
		return false;
	}

	private boolean testAbility(Cell[][] candi) {
		// TODO Auto-generated method stub
		return false;
	}

	private LinkedList<Cell[]> generateCases(Cell[][] baseMap, int i) {
		// TODO Auto-generated method stub
		return null;
	}



	

	private Cell[][] paintInevitables(Cell[][] mapArray) {
		// TODO Auto-generated method stub
		return null;
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
		assginBlankCell();
	}

	private void newArray() {
		mapArray = new Cell[20][20];
	}

	public Cell getCell(int cellx, int celly) {
		return mapArray[cellx][celly];
	}

	public void paintCell(Cell cell) {
		// TODO Auto-generated method stub
		
	}

	public void XCell(Cell cell) {
		// TODO Auto-generated method stub
		
	}

}
