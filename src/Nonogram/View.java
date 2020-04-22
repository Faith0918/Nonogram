package Nonogram;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class View extends JFrame {
	JPanel buttonPanel;
	JPanel mapPanel;
	JButton resetButton;
	JButton finishButton;
	DrawComponent cmp;
	
	public View() {
		setTitle("MineSweeper");
		this.setSize(425,490);
		this.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		mapPanel = new JPanel();
		resetButton = new JButton("restart");
		finishButton = new JButton("finish");
		buttonPanel.add(resetButton);
		buttonPanel.add(finishButton);
		cmp = new DrawComponent();
		mapPanel.add(cmp);
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
		cmp.setViewModel(viewModel);
		
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

	

}
