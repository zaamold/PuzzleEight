import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
/**
 * The Piece class is a helpful
 * data sac that stores the information
 * of a single "piece" of the puzzle.
 * Includes several helpful methods for 
 * quick manipulation of a Piece's values.
 * @author zacharyaamold
 *
 */
public class Piece {

	// Declaring all global variables
	private int x, y, size, num, row, col;
	private Image image;

	/**
	 * Piece constructor takes all global variables
	 * as parameters, which is populated in the Model
	 * @param x The x location
	 * @param y The y location
	 * @param size The width/height
	 * @param num The unique ID for this Piece
	 * @param row The row in the 2D Array this Piece belongs to in the Model
	 * @param col The col in the 2D Array this Piece belongs to in the Model
	 * @param image The subimage given to this Piece
	 */
	public Piece(int x, int y, int size, int num, int row, int col, Image image) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.num = num;
		this.row = row;
		this.col = col;
		this.image = image;
	}
	
	/**
	 * Given a Piece, overwrites all of this instance's
	 * relevant values with p's values
	 * @param p The Piece to be swapped with
	 */
	public void swapPieceValues(Piece p) {
		this.x = p.getX();
		this.y = p.getY();
		this.row = p.getRow();
		this.col = p.getCol();
	}
	
	/**
	 * Given a set of values, overwrites all of this instance's
	 * relevant values
	 * @param x The new x location
	 * @param y The new y location
	 * @param row The new row
	 * @param col The new col
	 */
	public void swapPieceValues(int x, int y, int row, int col) {
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
	}

	/**
	 * Helper method locally has all logic
	 * for drawing this Piece object onto our panel.
	 * @param page The page to draw onto
	 */
	public void drawOn(Graphics page) {
		if(num != 1) {
			page.drawImage(image, x, y, size, size, null);
		} else {
			// Creates a "blank piece" for the Piece
			// with a num id of 1
			page.setColor(Color.white);
			page.fillRect(x, y, size, size);
		}
	}
	
	/**
	 * Determines if this Piece was clicked, given
	 * the location of a user's click
	 * @param mouseX The x location of the user's click
	 * @param mouseY The y location of the user's click
	 * @return True if the user's click was within this piece, false otherwise
	 */
	public boolean wasClicked(int mouseX, int mouseY) {
		// If this is the blank piece, we leave immediately
		// because it can't be swapped
		if(num == 1) {
			return false;
		}
		
		// Checks to see if the user's click location is within the 
		// square location of this Piece
		if(mouseX > x && mouseX < (x + size) && mouseY > y && mouseY < (y + size)) {
			return true;
		} 
		
		return false;
	}
	
	// Getters & Setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return size;
	}

	public int getNum() {
		return num;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Overrides Object's toString for quick
	 * and clean access to Piece info from the Console
	 */
	public String toString() {
		return "Piece " + num + " - x: " + x + " y: " + y;
	}

}
