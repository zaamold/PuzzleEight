import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Our Model stores and maintains all
 * relevant data for our PuzzleEight. It also
 * includes many useful methods for manipulation
 * of the Piece objects in our game state.
 * @author zacharyaamold
 *
 */
public class Model {

	// Declaring our member variables
	private Piece[][] pieces;
	private int pieceSize;

	/**
	 * Our Model's constructor initializes member variables
	 * and creates an initial game state of Piece objects
	 * @param pieceSize How many pieces there are per row (and col)
	 * @param length The length of the panel holding our Pieces
	 * @param picName The file path to the picture to split into Pieces
	 */
	public Model(int pieceSize, int length, String picName) {
		this.pieceSize = pieceSize;
		pieces = new Piece[pieceSize][pieceSize];

		fillPieces(pieceSize, length, picName);
	}

	public void fillPieces(int size, int length, String picName) {
		// Creating a BufferedImage using our helper method
		BufferedImage image = createImage(picName, length);

		// Determining the size of each individual piece by dividing 
		// the panel's length by how many pieces we want per row
		int pSize = length/size;
		// num variable helps us initialize each piece with a unique id
		int num = 1;
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				// Populating our 2D array with new Pieces, in correct locations properly sized.
				// Each Piece is given a subimage of the entire image
				pieces[i][k] = new Piece((k*pSize), (i*pSize), pSize, num, k, i, 
						image.getSubimage((k*pSize), (i*pSize), pSize, pSize));
				num++;
			}
		}
	}

	/**
	 * Given a path to an image and a size,
	 * creates a properly scaled, square version
	 * of the image to return
	 * @param picName The file path to the image
	 * @param length The length of the image (both width and height)
	 * @return A scaled version of the given image
	 */
	private BufferedImage createImage(String picName, int length) {
		// Declaring and safely initializing our image
		BufferedImage i = null;
		try {
			i = ImageIO.read(new File(picName));
		} catch (IOException e) {
			System.err.println("Picture File not found");
			System.exit(-1); // Exiting the program if the image can't be read
		}

		// Modified logic from StackOverflow:
		// https://stackoverflow.com/questions/4216123/how-to-scale-a-bufferedimage
		int bWidth = i.getWidth();
		int bHeight = i.getHeight();
		BufferedImage after = new BufferedImage(bWidth, bHeight, BufferedImage.TYPE_INT_ARGB);
		// The AffineTransform object assists in scaling the image  
		// to meet the size of our PuzzlePanel
		AffineTransform at = new AffineTransform();
		at.scale((double)(length)/bWidth, (double)(length)/bHeight);
		AffineTransformOp scaleOp = 
				new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(i, after);

		return after;
	}

	/**
	 * Our randomizing method is not efficient by any means,
	 * but it utilizes our existing swap() function to easily
	 * shuffle the Pieces throughout the board randomly 
	 */
	public void randomize() {

		// An arbitrarily large for loop that runs longer
		// depending on how many pieces we're dealing with overall
		for(int i = 0; i < (100 * pieceSize); i++) {

			// Randomly finds a unique identifier (num value) of a Piece
			int rando = (int)(Math.random()*(pieces.length * pieces[0].length) - 1) + 2;			

			// swapFound will become true if a swap ever occurs,
			// so we can leave our nested for loops and perform another swap
			boolean swapFound = false;
			for(int k = 0; k < pieces.length; k++) {
				for(int j = 0; j < pieces[k].length; j++) {
					// If we've found the piece matching our rando variable...
					if(pieces[k][j].getNum() == rando) {
						// ...we attempt to swap this piece and leave 
						// these two nested for loops
						swap(pieces[k][j]);
						swapFound = true;
						break;
					}
				}
				if(swapFound) {
					break;
				}
			}
		}

	}

	/**
	 * Given the location of a user's click,
	 * determines if a Piece was located and returns it
	 * @param mouseX The x location of the user's click
	 * @param mouseY The y location of the user's click
	 * @return The clicked Piece, or null otherwise
	 */
	public Piece findClickedPiece(int mouseX, int mouseY) {
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				// Calling our Piece's method for better organization
				// in an Object-oriented setting. Returns the Piece if true
				if(pieces[i][k].wasClicked(mouseX, mouseY)) {
					return pieces[i][k];
				}
			}
		}
		// Returns null if no Piece was found to be clicked
		return null;
	}

	/**
	 * This method swaps a given piece with the blankPiece,
	 * as long as the two pieces are adjacent
	 * @param piece The Piece we want to swap with the blank piece
	 * @return true if this move has won the game, false otherwise
	 */
	public boolean swap(Piece piece) {
		Piece blankPiece = null;

		// Finds which piece in our 2D array is the blank
		// piece (i.e., which piece has a num value of 1)
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				if(pieces[i][k].getNum() == 1) {
					blankPiece = pieces[i][k];
					break;
				}
			}
			if(blankPiece != null) {
				break;
			}
		}

		// If our piece is adjacent to the blank piece, we allow the swap to occur
		if((piece.getCol() == blankPiece.getCol() && 
				(piece.getRow() == blankPiece.getRow() - 1 || piece.getRow() == blankPiece.getRow() + 1)) || 
				(piece.getRow() == blankPiece.getRow() && 
				(piece.getCol() == blankPiece.getCol() - 1 || piece.getCol() == blankPiece.getCol() + 1))) {

			// Storing blank piece's values to swap
			int bX = blankPiece.getX();
			int bY = blankPiece.getY();
			int bRow = blankPiece.getRow();
			int bCol = blankPiece.getCol();

			// Swapping the pieces in our 2D array
			pieces[bCol][bRow] = piece;
			pieces[piece.getCol()][piece.getRow()] = blankPiece;

			// Swapping the values in our Pieces
			blankPiece.swapPieceValues(piece);
			piece.swapPieceValues(bX, bY, bRow, bCol);				

		}

		return didPlayerWin();
	}

	/**
	 * Determines if the player has won
	 * by seeing if the Pieces are all in order,
	 * according to their num values set upon initialization
	 * @return true if the player won, false otherwise
	 */
	public boolean didPlayerWin() {
		// Starting at 1, the num values of each piece
		// must be in consecutive order for the player to have won
		int num = 1;
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				// Checking to see if the current piece maintains the consecutive order
				if(pieces[i][k].getNum() != num) {
					return false;
				}
				num++;
			}

		}
		return true;
	}

	/**
	 * Gets the 2D Array of Pieces, used by
	 * the PuzzlePanel to display the game state
	 * @return The 2D Array of Piece objects
	 */
	public Piece[][] getPieces() {
		return pieces;
	}

}
