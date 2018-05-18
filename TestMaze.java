package mazeTest;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
public class TestMaze implements MouseListener{
	int width = 6;
	int height = 5;
	String tag = "";
	int[][] maze = {	{1,1,1,1,2},
							{1,2,2,2,2},
							{1,1,1,1,2},
							{2,1,2,1,2},
							{2,1,1,1,2},
							{2,2,2,1,1}};
	static int[][] defaultMaze = {	{1,1,1,1,2},
									{1,2,2,2,2},
									{1,1,1,1,2},
									{2,1,2,1,2},
									{2,1,1,1,2},
									{2,2,2,1,1}};
	boolean[][] wasHere = new boolean[width][height];
	boolean[][] correctPath = new boolean[width][height]; // The solution to the maze
	int startX, startY; // Starting X and Y values of maze
	int endX = width - 1, endY = height - 1;
	public MazeCell[][] mazeArray;
	public static void main(String[] args) throws FileNotFoundException {
		//TestMaze newMaze = new TestMaze(new File("P:/Mazes/testMaze1.txt"));
		//TestMaze newMaze = new TestMaze(new File("P:/Mazes/TestMaze2.txt"));
		TestMaze newMaze = new TestMaze(30,20);
		//TestMaze newMaze = new TestMaze();
		//RandomMaze newMaze = new RandomMaze();
		newMaze.solveMaze();
		//newMaze.tag = "TestMaze2";
		/*for (int row = 0; row < newMaze.maze.length; row++)  {
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < newMaze.maze[row].length; col++){
	        	if (newMaze.correctPath[row][col]) {
	        		System.out.print("X ");
	        	} else {
	        		System.out.print("- ");
	        	}
	           
	        }
	        System.out.println();
		}*/
		 
		
	}
	
	public TestMaze() {
		this.width = 6;
		this.height = 5;
		this.tag = "defaultMaze";
		this.maze = TestMaze.defaultMaze;
		this.wasHere = new boolean[width][height];
		this.correctPath = new boolean[width][height];
		this.startX = 0;
		this.startY = 0;
		this.endX = width - 1;
		this.endY = height - 1;
	}
	
	public TestMaze(int w, int h, int[][] m) {
		this.width = w;
		this.height = h;
		this.maze = m;
		this.wasHere = new boolean[width][height];
		this.correctPath = new boolean[width][height];
		this.startX = 0;
		this.startY = 0;
		this.endX = width - 1;
		this.endY = height - 1;
	}
	
	public TestMaze(int w, int h) {
		this.width = w;
		this.height = h;
		this.maze = this.setNewMaze(w, h);
		this.wasHere = new boolean[width][height];
		this.correctPath = new boolean[width][height];
		this.startX = 0;
		this.startY = 0;
		this.endX = width - 1;
		this.endY = height - 1;
	}
	
	public TestMaze(File file) throws FileNotFoundException {
		Scanner in = new Scanner(new FileReader(file));
		this.width = in.nextInt();
		this.height = in.nextInt();
		int[][] fileMaze = new int[width][height];
		
		for (int i = 0; i < fileMaze.length; i++) {
			for (int j = 0; j < fileMaze[i].length; j++) {
				fileMaze[i][j] = in.nextInt();
			}
		}
		in.close();
		this.maze = fileMaze;
		this.wasHere = new boolean[width][height];
		this.correctPath = new boolean[width][height];
		this.startX = 0;
		this.startY = 0;
		this.endX = width - 1;
		this.endY = height - 1;
	}
	
	public void solveMaze() {
	    // Create Maze (1 = path, 2 = wall)
		//System.out.printlnmaze.length);
	    for (int row = 0; row < maze.length; row++) { 
	        // Sets boolean Arrays to default values
	        for (int col = 0; col < maze[row].length; col++){
	            wasHere[row][col] = false;
	            correctPath[row][col] = false;
	        }
	    }
	    boolean b = recursiveSolve(0, 0);
	    //System.out.printlnb);
	    buildMaze();
	    // Will leave you with a boolean array (correctPath) 
	    // with the path indicated by true values.
	    // If b is false, there is no solution to the maze
	}
	public boolean recursiveSolve(int x, int y) {
	    if (x == endX && y == endY) return true; // If you reached the end
	    if (maze[x][y] == 2 || wasHere[x][y]) return false;  
	    // If you are on a wall or already were here
	    wasHere[x][y] = true;
	    if (x != 0) // Checks if not on left edge
	        if (recursiveSolve(x-1, y)) { // Recalls method one to the left
	            correctPath[x][y] = true; // Sets that path value to true;
	            return true;
	        }
	    if (x != width - 1) // Checks if not on right edge
	        if (recursiveSolve(x+1, y)) { // Recalls method one to the right
	            correctPath[x][y] = true;
	            return true;
	        }
	    if (y != 0)  // Checks if not on top edge
	        if (recursiveSolve(x, y-1)) { // Recalls method one up
	            correctPath[x][y] = true;
	            return true;
	        }
	    if (y != height - 1) // Checks if not on bottom edge
	        if (recursiveSolve(x, y+1)) { // Recalls method one down
	            correctPath[x][y] = true;
	            return true;
	        }
	    return false;
	}
	
	public void buildMaze() {
		JFrame frame = new JFrame("Maze");
		JPanel myPanel = new JPanel();
		mazeArray = new MazeCell[maze.length][maze[1].length];
		for (int row = 0; row < maze.length; row++) { 
	        for (int col = 0; col < maze[row].length; col++){
	           mazeArray[row][col] = new MazeCell(maze[row][col] == 2);
	           mazeArray[row][col].x = row;
	           mazeArray[row][col].y = col;
	           if (width > 50 || height > 50) {
	        		mazeArray[row][col].setSize(new Dimension(10, 10));
	        	}
	        }
	    }
		/*for (int row = 0; row < maze.length; row++) { 
	        for (int col = 0; col < maze[row].length; col++){
	           myPanel.add(mazeArray[row][col]);
	        }
	    }*/
		for (int row = 0; row < maze.length; row++) { 
	        for (int col = 0; col < maze[row].length; col++){
	           mazeArray[row][col].addMouseListener(this);
	        }
	    }
		for (int row = 0; row < maze.length; row++) { 
	        for (int col = 0; col < maze[row].length; col++){
	        	
				mazeArray[row][col].setVisible(true);
				mazeArray[row][col].setOpaque(true);
				mazeArray[row][col].setLocation(row * mazeArray[row][col].getHeight(), col * mazeArray[row][col].getWidth());
				myPanel.add(mazeArray[row][col]);
				//adds the label array to the JPanel as well as sets the location of each label on the JPanel
			}
		}
		JLabel label = new MazeCell("Export Maze", false);
		label.setSize(50, 100);
		label.setBackground(Color.CYAN);
		label.setOpaque(true);
		label.setLocation((mazeArray[0].length * mazeArray[0][0].getHeight()) + 50, (mazeArray.length * mazeArray[0][0].getHeight()) + 50);
		label.addMouseListener(this);
		
		MazeCell solution = new MazeCell("Solution", false);
		solution.setSize(30, 70);
		solution.setBackground(Color.RED);
		solution.setOpaque(true);
		solution.setLocation((mazeArray[0].length * mazeArray[0][0].getHeight()) + 20, (mazeArray.length * mazeArray[0][0].getHeight()) + 20);
		solution.addMouseListener(this);
		
		myPanel.setLayout(null);
		myPanel.add(label);
		myPanel.add(solution);
		frame.getContentPane().add(myPanel);
		frame.setSize(1000, 1000);
		myPanel.setSize((mazeArray[0][0].getWidth() * this.maze[0].length) + 200, (mazeArray[0][0].getHeight() * this.maze.length) + 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		/*JPanel otherPanel = new JPanel();
		otherPanel.setBackground(Color.green);
		otherPanel.setLayout(null);
		otherPanel.setLocation(500, 500);
		otherPanel.add(label);
		otherPanel.setLocation(1000, 1000);
		label.setLocation(500, 500);
		frame.getContentPane().add(label);*/
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(recursiveSolve(0,0));
		MazeCell curCell = ((MazeCell) (e.getSource()));
		if (curCell.getSize().equals(mazeArray[0][0].getSize())) {
			if (curCell.getBackground() == Color.white) {
				curCell.setBackground(Color.black);
				curCell.wall = true;
				maze[curCell.x][curCell.y] = 2;
				wasHere[curCell.x][curCell.y] = !(wasHere[curCell.x][curCell.y]);
				correctPath[curCell.x][curCell.y] = !(correctPath[curCell.x][curCell.y]);
				clearArrays();
			} else {
				curCell.setBackground(Color.white);
				curCell.wall = false;
				maze[curCell.x][curCell.y] = 1;
				clearArrays();
			}
			//System.out.printlnrecursiveSolve(0,0));
			
			/*for (int[] i : maze) {
				for (int j : i) {
					System.out.print(j);
				}
				System.out.println();
			}*/
		}
		
		if (((JLabel) e.getSource()).getText().equals("Export Maze")) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure you want to save and exit?") == JOptionPane.OK_OPTION) {
				try {
					this.saveToFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} else if (curCell.getText().equals("Solution")) {
			recursiveSolve(0,0);
			//setCorrectPath();
			showSolution();
		}
	}
	
	public void clearArrays() {
		wasHere = new boolean[width][height];
		correctPath = new boolean[width][height];
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int[][] setNewMaze(int w, int h) {
		int[][] blankMaze = new int[w][h];
		for (int i = 0; i < blankMaze.length; i++) {
			for (int j = 0; j < blankMaze[i].length; j++) {
				blankMaze[i][j] = 2;
			}
		}
		return blankMaze;
	}
	
	public void saveToFile() throws IOException {
		File file = new File("P:/Mazes/" + this.tag + ".txt");
		file.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(file, false));
		out.println(this.width);
		out.println(this.height);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				out.println(maze[i][j]);
			}
		}
		out.close();
	}
	
	public void showSolution() {
		if (mazeArray == null) {
			System.out.println("The maze doesn't exist yet.");
			return;
		}
		for (int i = 0; i < mazeArray.length; i++) {
			for (int j = 0; j < mazeArray[i].length; j++) {
				if (correctPath[i][j]) {
					mazeArray[i][j].setBackground(Color.RED);
				}
			}
		}
	}
	
	/*private void setCorrectPath() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == 1 && correctPath[i][j] == false) {
					int shouldBeCorrect = 0;
					if (i == 0) {						
						if (j == 0) {
							if (correctPath[i+1][j]) shouldBeCorrect++;
							if (correctPath[i+1][j+1]) shouldBeCorrect++;
							if (correctPath[i][j+1]) shouldBeCorrect++;
						} else if (j == (maze[i].length - 1)) {
							if (correctPath[i+1][j]) shouldBeCorrect++;
							if (correctPath[i+1][j-1]) shouldBeCorrect++;
							if (correctPath[i][j-1]) shouldBeCorrect++;
						} else {
							if (correctPath[i+1][j]) shouldBeCorrect++;
							if (correctPath[i+1][j+1]) shouldBeCorrect++;
							if (correctPath[i+1][j-1]) shouldBeCorrect++;
							if (correctPath[i][j+1]) shouldBeCorrect++;
							if (correctPath[i][j-1]) shouldBeCorrect++;
						}
					} else if (i == maze.length - 1) {
						if (j == 0) {
							if (correctPath[i-1][j]) shouldBeCorrect++;
							if (correctPath[i-1][j+1]) shouldBeCorrect++;
							if (correctPath[i][j+1]) shouldBeCorrect++;
						} else if (j == maze[i].length - 1) {
							if (correctPath[i-1][j]) shouldBeCorrect++;
							if (correctPath[i-1][j-1]) shouldBeCorrect++;
							if (correctPath[i][j-1]) shouldBeCorrect++;
						} else {
							if (correctPath[i-1][j]) shouldBeCorrect++;
							if (correctPath[i-1][j+1]) shouldBeCorrect++;
							if (correctPath[i-1][j-1]) shouldBeCorrect++;
							if (correctPath[i][j+1]) shouldBeCorrect++;
							if (correctPath[i][j-1]) shouldBeCorrect++;
						}
					}
					else if (j == 0) {
						if (correctPath[i+1][j]) shouldBeCorrect++;
						if (correctPath[i-1][j]) shouldBeCorrect++;
						if (correctPath[i+1][j+1]) shouldBeCorrect++;
						if (correctPath[i-1][j+1]) shouldBeCorrect++;
						if (correctPath[i][j+1]) shouldBeCorrect++;
					} else if (j == (maze[i].length - 1)) {
						if (correctPath[i+1][j]) shouldBeCorrect++;
						if (correctPath[i-1][j]) shouldBeCorrect++;
						if (correctPath[i+1][j-1]) shouldBeCorrect++;
						if (correctPath[i-1][j-1]) shouldBeCorrect++;
						if (correctPath[i][j-1]) shouldBeCorrect++;
					} else {
						if (correctPath[i+1][j]) shouldBeCorrect++;
						if (correctPath[i-1][j]) shouldBeCorrect++;
						if (correctPath[i+1][j+1]) shouldBeCorrect++;
						if (correctPath[i+1][j-1]) shouldBeCorrect++;
						if (correctPath[i-1][j+1]) shouldBeCorrect++;
						if (correctPath[i-1][j-1]) shouldBeCorrect++;
						if (correctPath[i][j+1]) shouldBeCorrect++;
						if (correctPath[i][j-1]) shouldBeCorrect++;
					}
					
					if (shouldBeCorrect >= 2) {
						correctPath[i][j] = true;
					}
					
				}
			}
		}
	}*/

}
