package Nonogram;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public class PictureMap {
	Cell[][] mapArray;
	int[][] numberArray;// numberArray[i][0] : x axis numbers, numberArray[i][1] : y axis numbers
	public void newMap() {
		newArray();
		assignColorCell();
		assignNumbers();
		testMap1();
		
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

	private boolean testMap1() {
		int answer_quantity = 0;
		Cell[][] baseMap = paintInevitables(mapArray);
		Cell[][] candi = baseMap;
		LinkedList<Cell[]>[] cases = new LinkedList[20];
		for(int i = 0; i<20; i++) {
			cases[i] = generateCases(baseMap, i);
		}
		int line_num = 0;
		while(line_num<20) {
			for( Cell[] lineX : cases[line_num]) {
				LinkedList<Cell[]>[] backupCases = cases;
				Cell[][] backup = candi;
				candi[line_num] = lineX;
				
				checkConstraints(candi, cases);
				
 				if(testAbility(candi, cases)) {
					break;
				}
				else {
					candi = backup;
					backupCases[line_num].remove(lineX);
					cases = backupCases;
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
						for(int i = 0; i<20; i++) {
							cases[i].remove(candi[i]);
						}
					}
				}
				else {
					line_num++;
				}
			}
		}
		return false;
	}
	
//	private void testMap2() {
//		int answer_quantity = 0;
//		Cell[][] baseMap = paintInevitables(mapArray);
//		Cell[][] candi = baseMap;
//		LinkedList<Cell[]>[] cases = new LinkedList[20];
//		cases[0] = generateCases(baseMap, 0);
//		Queue<HashMap<Integer,Cell[]>> q = new LinkedList<HashMap<Integer,Cell[]>>();
//		HashMap<Integer, Cell[]> m = new HashMap<>();
//		q.add(new HashMap<Integer,Cell[]>() );
//		while(!q.isEmpty()) {
//			Cell[] line = q.poll();
//			testAbility();
//			
//		}
//		
//	}

	private void checkConstraints(Cell[][] candi, LinkedList<Cell[]>[] cases) {
		// TODO Auto-generated method stub
		
	}

	private void reduceFalseCase(LinkedList<Cell[]>[] cases) {
		// TODO Auto-generated method stub
		
	}

	private boolean testAbility(Cell[][] candi, LinkedList<Cell[]>[] cases) {
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
