package Nonogram;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public class PictureMap {
	Cell[][] mapArray;
	LinkedList<Integer>[][] numberArray;// numberArray[i][0] : x axis numbers, numberArray[i][1] : y axis numbers
	public void newMap() {
		newArray();
		assignColorCell();
		assignNumbers();
		testMap();
		
	}

	private void assignNumbers() {
		numberArray = new LinkedList[20][2];
		for(int i = 0; i<20; i++) {
			numberArray[i][0] = new LinkedList<Integer>();
			numberArray[i][1] = new LinkedList<Integer>();
			int x = 0;
			int y = 0;
			
			for(int j = 0; j<20; j++) {
				if(mapArray[i][j].getProperty() == Cell.Colored) {
					x++;
				}
				else {
					if(x != 0) {
						numberArray[i][0].add(x);
						x = 0;
					}
					
				}
				if(mapArray[j][i].getProperty() == Cell.Colored) {
					y++;
				}
				else {
					if(y != 0) {
						numberArray[i][1].add(y);
						y = 0;
					}
				}
				
				
			}
			
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
		LinkedList<Cell[]>[] cases = new LinkedList[20];
		for(int i = 0; i<20;i++) {
			cases[i] = generateCases(numberArray, i);
		}
		Cell[][] baseMap = paintInevitables(cases);
		Cell[][] candi = baseMap;
		
		int line_index = 0;
		while(line_index<20) {
			for(Cell[] cells: cases[line_index]) {
				Line line = new Line(line_index, cells);
				if(lineAvailable(line)) {
					candi[line_index] = cells;
					paintInevitables(cases, candi);
					break;
				}
				else {
					cases[line_index].remove(cells);
				}
			}
			if(cases[line_index].isEmpty() && line_index>0) {
				line_index--;
			}
			else if(cases[line_index].isEmpty()&& line_index ==0) {
				return true;
			}
			else {
				if(line_index == 19) {
					answer_quantity++;
					if(answer_quantity>2) {
						return false;
					}
					else {
						for(int i = 0; i<20; i++) {
							cases[i].remove(candi[i]);
						}
					}
				}
				else {
					line_index++;
				}
			}
		}
		return false;		
	}

	private boolean lineAvailable(Line line) {
		for(int i = 0; i<20; i++) {
			if(mapArray[line.getIndex()][i] != line.getCells()[i]) {
				return false;
			}
		}
		return true;
	}

	private LinkedList<Cell[]> generateCases(LinkedList<Integer>[][] numberArray, Cell[][] baseMap, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public LinkedList<Cell[]> generateCases(LinkedList[][] numberArray, int i){
		// TODO dfs로 전부 case 찾기.
		LinkedList<Cell[]> list = new LinkedList<Cell[]>();
		LinkedList<int[]> numbers = new LinkedList<int[]>();
		
		int c = 0;
		int index = 0;
		
		while(c<20) {
			if(c+(int)numberArray[i][0].get(index)>20) {
				
			}
			for(int idx = 0; idx<numberArray[i][0].size(); idx++) {
				int[] pair = new int[2];
				pair[0] = c;
				pair[1] = (int) numberArray[i][0].get(idx);
				numbers.add(pair);
					
			}
		}
//		for(int idx = 0; idx<numberArray[i][0].size(); idx++) {
//			for(int c = 0; c<20; c++) {
//				int[] pair = new int[2];
//				pair[0] = c;
//				pair[1] = (int) numberArray[i][0].get(idx);
//				numbers.add(pair);
//				
//			}
//		}
////		for(int num : numbers) {
//			
//		}
		return null;
	}

	

	private Cell[][] paintInevitables(LinkedList<Cell[]>[] cases) {
		Cell[][] returnMap = new Cell[20][20];
		
		for(int i = 0; i < 20; i++) {
			Cell[] paintedCells = new Cell[20];
			for(int idx = 0; idx<20; idx++) {
				paintedCells[idx] = new Cell(i, idx, Cell.Blank);
			}
			for(int c = 0; c<20; c++) {
				boolean all_painted = true;
				boolean never_painted = true;
				for(int j = 0; j<cases[i].size(); j++) {
					Cell[] cells = cases[i].get(j);
					if(cells[c].getProperty() == 0) {
						all_painted = false;
					}
					else if(cells[c].getProperty() == 1) {
						never_painted = false;
					}
				}
				if(all_painted) {
					paintedCells[c].setProperty(Cell.Colored);
				}
				else if(never_painted) {
					paintedCells[c].setProperty(Cell.X);
				}
				
			}
			returnMap[i] = paintedCells;
		}
		return returnMap;
	}
	
	private Cell[][] paintInevitables(LinkedList<Cell[]>[] cases, Cell[][] baseMap) {
		Cell[][] returnMap = baseMap;
		for(int i = 0; i < 20; i++) {
			Cell[] paintedCells = baseMap[i];
			for(int c = 0; c<20; c++) {
				if(paintedCells[c].getProperty() == Cell.Blank) {
					boolean all_painted = true;
					boolean never_painted = true;
					for(int j = 0; j<cases[i].size(); j++) {
						Cell[] cells = cases[i].get(j);
						if(cells[c].getProperty() == 0) {
							all_painted = false;
						}
						else if(cells[c].getProperty() == 1) {
							never_painted = false;
						}
					}
					if(all_painted) {
						paintedCells[c].setProperty(Cell.Colored);
					}
					else if(never_painted) {
						paintedCells[c].setProperty(Cell.X);
					}
				}
			}
			returnMap[i] = paintedCells;
		}
		return returnMap;
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
		mapArray[cell.getX()][cell.getY()].setProperty(Cell.Colored);
		
	}

	public void XCell(Cell cell) {
		mapArray[cell.getX()][cell.getY()].setProperty(Cell.X);
		
	}

}
