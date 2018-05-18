package mazeTest;

public class RandomMaze extends TestMaze{

	public RandomMaze(int w, int h) {
		super(w,h);
		this.maze = setNewMaze(w, h);
	}
	
	public RandomMaze() {
		super(25, 25);
		this.maze = setRandomMaze(25, 25);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public int[][] setRandomMaze(int w, int h) {
		int[][] randMaze = new int[w][h];
		for (int i = 0; i < randMaze.length; i++) {
			for (int j = 0; j < randMaze[i].length; j++) {
				maze[i][j] = 2;
			}
		}
		System.out.println("*** " + recursiveSolve(0,0));
		this.maze = randMaze;
		int curX = 0, curY = 0;
		
		for (int i = 0; i < 5; i--) {
			int randX = (int)(Math.random() * randMaze.length - 1);
			int randY = (int)(Math.random() * randMaze[0].length - 1);
			System.out.println(randX + " " + randY);
			randMaze[randX][randY] = 1;
			randMaze[randMaze.length - 1][randMaze[0].length - 1] = 1;
			randMaze[0][0] = 1;
			System.out.println(recursiveSolve(0,0));
			curX = randX;
			curY = randY;
			if (recursiveSolve(0,0) == true) {
				break;
			}
		}
		randMaze[curX][curY] = 1;
		return randMaze;
	}

}
