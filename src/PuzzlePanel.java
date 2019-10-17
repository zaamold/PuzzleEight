import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * PuzzlePanel displays all of the data being
 * held by our Model, specifically the puzzle
 * Piece objects
 * @author zacharyaamold
 *
 */
public class PuzzlePanel extends JPanel {

	// Declaring global variables
	private static final long serialVersionUID = 1L;
	Piece[][] pieces;

	/**
	 * Constructor takes a Controller to get
	 * the Piece 2D Array from the Model, and sets
	 * up our panel
	 * @param c The Controller
	 * @param width The width of this panel
	 * @param height The height of this panel
	 */
	public PuzzlePanel(Controller c, int width, int height) {
		pieces = c.getPieces();
		this.addMouseListener(c);
		this.setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Overrides from JComponent to draw our
	 * Piece objects onto the panel
	 */
	public void paintComponent(Graphics page) {
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				pieces[i][k].drawOn(page);
			}
		}
	}

}
