package Nonogram;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;

public class PictureMap {
	int size;
	Cell[][] mapArray;
	LinkedList<Integer>[][] numberArray;// numberArray[i][0] : x axis numbers, numberArray[i][1] : y axis numbers
	public void newMap(int size) {
		newArray(size);
		assignColorCell();
		assignNumbers();
		if(!testMap()) {
			newMap(size);
		}
		System.out.println("tested");
	}
	public Cell[][] getMap(){
		System.out.println("get map");
		return mapArray;
	}
	public LinkedList<Integer>[][] getNumbers(){
		return numberArray;
	}

	private void assignNumbers() {
		numberArray = new LinkedList[size][2];
		for(int i = 0; i<size; i++) {
			numberArray[i][0] = new LinkedList<Integer>();
			numberArray[i][1] = new LinkedList<Integer>();
			int x = 0;
			int y = 0;
			
			for(int j = 0; j<size; j++) {
				if(mapArray[i][j].getProperty() == Cell.Colored) {
					x++;
					if(j == size-1){
						numberArray[i][0].add(x);
						x = 0;
					}
				}
				else {
					if(x != 0) {
						numberArray[i][0].add(x);
						x = 0;
					}
					
				}
				if(mapArray[j][i].getProperty() == Cell.Colored) {
					y++;
					if(j==size-1) {
						numberArray[i][1].add(y);
						y = 0;
					}
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
		for(int i =0; i<size; i++) {
			for(int j=0; j<size; j++) {
				if(mapArray[i][j] == null) {
					mapArray[i][j] = new Cell(i, j, Cell.Blank);
				}
			}
		}
		
	}


	
	private boolean testMap() {
		int answer_quantity = 0;
		LinkedList<Cell[]>[] cases = new LinkedList[size];
		for(int i = 0; i<size;i++) {
			cases[i] = generateCases(numberArray, i);
			
		}
		Cell[][] baseMap = paintInevitables(cases);
		Cell[][] candi = baseMap;
		int line_index = 0;
		while(line_index<size) {
			if(!cases[line_index].isEmpty()) {
				int i = 0;
				while(cases[line_index].size()<=i) {
					Cell[] cells = cases[line_index].get(i);
					Line line = new Line(line_index, cells);
					if(lineAvailable(line)) {
						candi[line_index] = cells;
						paintInevitables(cases, candi);
						break;
					}
					else {
						cases[line_index].remove(cells);
						continue;
					}
				}
			}
			else {
				line_index++;
				continue;
			}
			
			if(cases[line_index].isEmpty() && line_index>0) {
				line_index--;
			}
			else if(cases[line_index].isEmpty()&& line_index ==0) {
				return true;
			}
			else {
				if(line_index == size-1) {
					answer_quantity++;
					if(answer_quantity>=2) {
						return false;
					}
					else {
						for(int i = 0; i<size; i++) {
							if(!cases[i].isEmpty()) {
								for(int j= 0; j<cases[i].size(); j++) {
									if(compareLines(cases[i].get(j), candi[i])) {
										cases[i].remove(j);	
										break;
									}
								}//cases를 line으로 관리해야함.(지울때 제대로 안지워짐) index로 지워야 확실히 지워질듯
							}
							
						}
						line_index = 0;
					}
				}
				else {
					line_index++;
				}
			}
		}
		
		return true;		
	}

	private boolean lineAvailable(Line line) {
		System.out.println("line : "+line.getCells()[0].getX()+" "+line.getCells()[0].getY()+line.getCells()[0].getProperty());
		for(int i = 0; i<size; i++) {
			if(mapArray[line.getIndex()][i].getProperty() != line.getCells()[i].getProperty()) {
				return false;
			}
		}
		return true;
	}

	

	public LinkedList<Cell[]> generateCases(LinkedList[][] numberArray, int i){
		// TODO dfs로 전부 case 찾기.
		LinkedList<Cell[]> cases = new LinkedList<Cell[]>();
		LinkedList<int[]> numbers = new LinkedList<int[]>();
		
		if(numberArray[i][0].size() == 0) {
			return cases;
		}
		int count = 0;
		int index = 0;
		Cell[] line = new Cell[size];
		for(int idx = 0; idx<size; idx++) {
			line[idx] = new Cell(i, idx, Cell.Blank);
			
		}
		dfs(numberArray[i][0], cases, line, index, count);
		return cases;
	}

	boolean compareLines(Cell[] line1, Cell[] line2) {
		boolean same = true;
		for(int i = 0; i<size; i++) {
			
			if(line1[i].getProperty() + line2[i].getProperty() %2 == 1) {
				same = false;
				return false;
			}
		}
		if(same) {
			return true;
		}
		else {
			return false;
		}
	}

	private void dfs(LinkedList numbers, LinkedList<Cell[]> cases, Cell[] line, int index, int count) {
		if(index==numbers.size()) {
			for(int i = 0; i< cases.size(); i++) {
				
				if(compareLines(cases.get(i), line)) {
					return;
				}
			}
			cases.add(line);
			return;
		}
		for(int j = 1; j<size; j++) {
			int n = (int)numbers.get(index);
			if(count + n <size) {
				for(int i = count; i<count+n; i++) {
					line[i].setProperty(Cell.Colored);
				}
				count += n + j;
				index++;
				dfs(numbers, cases, line, index, count);
				count -= (n+j);
				index--;
				for(int i = count; i<count+n; i++) {
					line[i].setProperty(Cell.Blank);
				}
			}			
		}
	}

	private Cell[][] paintInevitables(LinkedList<Cell[]>[] cases) {
		Cell[][] returnMap = new Cell[size][size];
		
		for(int i = 0; i < size; i++) {
			Cell[] paintedCells = new Cell[size];
			for(int idx = 0; idx<size; idx++) {
				paintedCells[idx] = new Cell(i, idx, Cell.Blank);
			}
			for(int c = 0; c<size; c++) {
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
		for(int i = 0; i < size; i++) {
			Cell[] paintedCells = baseMap[i];
			for(int c = 0; c<size; c++) {
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
		while(num<200) {
			int x = random.nextInt(size);
			int y = random.nextInt(size);
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

	private void newArray(int size) {
		mapArray = new Cell[size][size];
		this.size = size;
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
	public boolean checkAnswer(Cell[][] viewModel) {
		boolean isCorrect = true;
		for(int i = 0; i<size; i++) {
			if(!compareLines(mapArray[i], viewModel[i])) return false;
		}
		return isCorrect;
		
	}


}
