package Nonogram;

import java.awt.BorderLayout;
import java.sql.NClob;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;





public class View extends JFrame {
	Core core;
	JPanel buttonPanel;
	JPanel mapPanel;
	JButton resetButton;
	JButton finishButton;
	DrawComponent cmp;
	NumberComponent x_numberComponent;
	NumberComponent y_numberComponent;
	int initial_size = 20;
	
	public View(Core core) {
		this.core = core;
		setTitle("Nonogram");
		this.setSize(625,750);
		this.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		mapPanel = new JPanel();
		resetButton = new JButton("restart");
		finishButton = new JButton("finish");
		buttonPanel.add(resetButton);
		buttonPanel.add(finishButton);
		cmp = new DrawComponent();
		resetViewModel(core.getSize());
		mapPanel.add(cmp);
		x_numberComponent = new NumberComponent();
		y_numberComponent = new NumberComponent();
		
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(cmp, BorderLayout.CENTER);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void resetViewModel(int size) {
		Cell[][] viewModel = new Cell[size][size];
		System.out.println("???");
		for(int r = 0; r < size ; r++) {
			for(int c = 0; c < size; c++) {
				viewModel[r][c] = new Cell(r, c, Cell.Blank);
			}
		}
		System.out.println("000");
		LinkedList<Integer>[][] numbers = new LinkedList[size][2];
		for(int i = 0; i<size; i++) {
			for(int j = 0; j<2; j++) {
				numbers[i][j] = new LinkedList<Integer>();
			}
		}
		System.out.println("111");
 		cmp.setViewModel(viewModel);
 		System.out.println("222");
		cmp.setNumbers(numbers);
		
	}

	public void drawMap() {
		cmp.repaint();
		
	}

	public void setNumbers(LinkedList[][] numberArray) {
		cmp.setNumbers(numberArray);
	}
	public void changeViewModel(Cell cell) {
		Cell[][] map = cmp.getViewModel();
		map[cell.getX()][cell.getY()].setProperty(cell.getProperty());
		cmp.setViewModel(map);
	}
	public void addListener(NonogramUserListener listener) {
		resetButton.addActionListener(listener);
		finishButton.addActionListener(listener);
		cmp.addMouseListener(listener);
	}
	public Cell[][] getViewModel() {
		// TODO Auto-generated method stub
		return cmp.getViewModel();
	}
	public void popup(String string) {
		JOptionPane.showMessageDialog(finishButton, string);
		
	}
	public Cell getCell(int cellx, int celly) {
		Cell[][] viewModel = getViewModel();
		return viewModel[cellx][celly];
	}
	

}
