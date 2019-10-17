import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/**
 * This is the Controller of our PuzzleEight project, using MVC standards.
 * It holds the main method that begins all logic within the project.
 * This project was created in September 2018, and no logic has been modified since then.
 * @author zacharyaamold
 *
 */
public class Controller implements MouseListener {
	
	// Setting up global variables
	public Model myModel;
	public View myView;
	// Creating final values for single point of maintenance
	// (NOTE: These values can be modified and the program will still run correctly)
	private final int SIZE = 300;
	private final int SQUARE_NUM = 3;
	private final String PIC_NAME = "HaloReachLogo.png";
	private final String VIEW_TITLE = "Puzzle Eight";
	
	/**
	 * Constructor for our Controller, sets up
	 * our Model and View
	 */
	public Controller() {
		myModel = new Model(SQUARE_NUM, SIZE, PIC_NAME);
		myView = new View(this, SIZE, SIZE);
		myView.setTitle(VIEW_TITLE);
	}

	/**
	 * Inherited from our MouseListener interface,
	 * this method checks to see where a user has clicked and
	 * moves tiles accordingly, when applicable 
	 */
	@Override
	public void mousePressed(MouseEvent e) { 
		// Finding the location of the user's click
		int mouseX = e.getX();
		int mouseY = e.getY();
		// Setting up a boolean to store if the user has won momentarily
		boolean win = false;
		
		// Calls our model's method to find the piece that was clicked
		Piece clicked = myModel.findClickedPiece(mouseX, mouseY);
		
		// If the user did, in fact, click a piece...
		if(clicked != null) {
			// ...we have the model swap the piece, and see if the user has won
			win = myModel.swap(clicked);
		}
		
		// If this move has won the game, we change the title to show it
		if(win) {
			myView.setTitle("You won!");
		} else {
			// If this move has not won the game, we keep the title we set initially
			myView.setTitle(VIEW_TITLE);
		}
		
		// Repainting the view to update the frame and PuzzlePanel
		myView.repaint();
	}
	
	// Unused methods inherited from MouseListener interface below
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * This method helps randomize the
	 * placements of the tiles and repaints the view
	 */
	public void randomize() {
		myModel.randomize();
		myView.setTitle(VIEW_TITLE);
		myView.repaint();
	}
	
	/**
	 * Resets the board by resetting the pieces via the model,
	 * and repaints the view
	 */
	public void reset() {
		myModel.fillPieces(SQUARE_NUM, SIZE, PIC_NAME);
		myView.setTitle(VIEW_TITLE);
		myView.repaint();
	}
	
	/**
	 * This helper getter gets the two dimensional array
	 * of Piece objects stored in the Model, to make it
	 * easier to access it from the PuzzlePanel for repainting
	 * @return The Model's 2D array of Piece objects (the current game state)
	 */
	public Piece[][] getPieces() {
		return myModel.getPieces();
	}
	
	/**
	 * Our main method initializes an instance
	 * of our Controller
	 * @param args 
	 */
	public static void main(String[] args) {
		new Controller();
	}

}
