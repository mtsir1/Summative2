package mazeTest;

import java.awt.*;

import javax.swing.JLabel;

public class MazeCell extends JLabel {
	private static final long serialVersionUID = -5793510411586773322L;
	final Dimension dimSize = new Dimension(20,20);
	boolean wall;
	int x;
	int y;
	
	public MazeCell() {
		super();
		this.wall = false;
		this.setSize(dimSize);
	}
	
	
	public MazeCell(boolean w) {
		super();
		this.wall = w;
		if (w) {
			this.setBackground(Color.black);
		} else {
			this.setBackground(Color.white);
		}
		this.setVisible(true);
		this.setSize(dimSize);
	}
	
	public MazeCell(String s, boolean w) {
		super(s);
		this.wall = w;
		if (w) {
			this.setBackground(Color.black);
		} else {
			this.setBackground(Color.white);
		}
		this.setVisible(true);
		this.setSize(dimSize);
	}

}
