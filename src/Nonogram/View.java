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
	
	public View(Core core) {
		core = this.core;
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
		
		resetViewModel();
		mapPanel.add(cmp);
		x_numberComponent = new NumberComponent();
		y_numberComponent = new NumberComponent();
		
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(cmp, BorderLayout.CENTER);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void resetViewModel() {
		Cell[][] viewModel = new Cell[20][20];
		for(int r = 0; r < 20 ; r++) {
			for(int c = 0; c < 20; c++) {
				viewModel[r][c] = new Cell(r, c, Cell.Blank);
			}
		}
		LinkedList<Integer>[][] numbers = new LinkedList[20][2];
		for(int i = 0; i<20; i++) {
			for(int j = 0; j<2; j++) {
				numbers[i][j] = new LinkedList<Integer>();
			}
		}
 		cmp.setViewModel(viewModel);
		cmp.setNumbers(numbers);
		
	}

	public void drawMap() {
		cmp.repaint();
		
	}


	public void paintCellView(Cell cell) {
		// TODO Auto-generated method stub
		
	}

	public void XCellView(Cell cell) {
		// TODO Auto-generated method stub
		
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
	public Object getViewModel() {
		// TODO Auto-generated method stub
		return cmp.getViewModel();
	}
	public void popup(String string) {
		JOptionPane.showMessageDialog(finishButton, string);
		
	}
	

}
