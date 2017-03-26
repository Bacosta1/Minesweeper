import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JPanel;

public class MSPanel extends JPanel {
	public int x = -1;
	public int y = -1;
	public int xGridMouseClick = 0;
	public int yGridMouseClick = 0;
	public int amountOfBombs = 20;
	private static final int xGrid = 50;
	private static final int yGrid = 50;
	private static final int columns = 9;
	private static final int rows = 9;
	private static final int cellSize = 29;  
	private static final long serialVersionUID = -654646154811654322L;

	public Color[][] paint = new Color[columns][rows];
	public boolean[][] locationOfMines = new boolean[columns][rows];
	public String[][] bombQuantityAround = new String[columns][rows];	


	public void minesCreation(){
		Random i = new Random();
		Random j = new Random();		
		for (int t=0; t<amountOfBombs; t++)	{			
			locationOfMines[i.nextInt(columns)][j.nextInt(rows)] = true;
		}
	}
	public int scanForNearBombs(int x, int y){
		int amountOfBombsAround =0;
		for(int i = x-1; i < x+2; i++){
			for(int j = y-1; j < y+2; j++){
				if(i >=0 && j >=0 && i <= (columns-1) && j <= (rows-1)){
					if (locationOfMines[i][j] == true){
						amountOfBombsAround++;						
					}
				}
			}
		}
		return amountOfBombsAround;
	}
	public void Numbers(){
		for (int x = 0; x < (columns-1); x++) {
			for (int y = 0; y < (rows -1); y++) {
				if(locationOfMines[x][y] == false){
					bombQuantityAround[x][y] = String.valueOf(scanForNearBombs(x,y));
				}
			}
		}
	}
	//				public MSPanel() {  
	//					JOptionPane.showMessageDialog(null, "Mines nearby: Blue = 1, Green = 2 or more");
	//					if (cellSize + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
	//						throw new RuntimeException("INNER_CELL_SIZE must be positive!");
	//					}
	//					if (columns + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
	//						throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
	//					}
	//					if (rows + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
	//						throw new RuntimeException("TOTAL_ROWS must be at least 3!");
	//					}
	//					for (int x = 0; x < columns; x++) {   //The rest of the grid
	//						for (int y = 0; y < rows; y++) {
	//							paint[x][y] = Color.WHITE;
	//						}
	//					}
	//					minesCreation();
	//					Numbers();	
	//				}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//Compute interior coordinates
		Insets inserted = getInsets();
		int x1 = inserted.left;
		int y1 = inserted.top;
		int x2 = getWidth() - inserted.right - 1;
		int y2 = getHeight() - inserted.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);

		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 10x10 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLACK);
		for (int y = 0; y <= rows; y++) {
			g.drawLine(x1 + xGrid, y1 + yGrid + (y * (cellSize + 1)), x1 + xGrid + ((cellSize + 1) * columns), y1 + yGrid + (y*(cellSize + 1)));
		}
		for (int x = 0; x <= columns; x++) {
			g.drawLine(x1 + xGrid + (x * (cellSize + 1)), y1 + yGrid, x1 + xGrid + (x * (cellSize + 1)), y1 + yGrid + ((cellSize + 1) * (rows)));
		}
		//Paint cell colors
		for (int x = 0; x < columns;x++) {
			for (int y = 0; y < rows; y++) {
				Color c = paint[x][y];
				g.setColor(c);
				g.fillRect(x1 + xGrid + (x * (cellSize + 1)) + 1, y1 + yGrid + (y * (cellSize + 1)) + 1, cellSize, cellSize);
			}
		}			
	}
	public int createXGrid(int x, int y) {
		Insets inserted = getInsets();
		int x1 = inserted.left;
		int y1 = inserted.top;
		x = x - x1 - xGrid;
		y = y - y1 - yGrid;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (cellSize + 1) == 0) || (y % (cellSize+ 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (cellSize + 1);
		y = y / (cellSize + 1);
		if (x < 0 || x > columns - 1 || y < 0 || y > rows - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}
	public int createYGrid(int x, int y) {
		Insets inserted = getInsets();
		int x1 = inserted.left;
		int y1 = inserted.top;
		x = x - x1 - xGrid;
		y = y - y1 - yGrid;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (cellSize + 1) == 0) || (y % (cellSize + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (cellSize + 1);
		y = y / (cellSize + 1);
		if (x < 0 || x > columns - 1 || y < 0 || y > rows - 1) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
}
