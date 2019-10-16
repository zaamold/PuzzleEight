import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Model {

	private Piece[][] pieces;
	int pieceSize;

	public Model(int pieceSize, int length, String picName) {
		this.pieceSize = pieceSize;
		pieces = new Piece[pieceSize][pieceSize];

		fillPieces(pieceSize, length, picName);
//		randomize();
	}

	public void fillPieces(int size, int length, String picName) {
		
		BufferedImage image = createImage(picName, length);

		int pSize = length/size;
		int num = 1;
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				pieces[i][k] = new Piece((k*pSize), (i*pSize), pSize, pSize, num, k, i, 
						image.getSubimage((k*pSize), (i*pSize), pSize, pSize));
				num++;
			}
		}
	}
	
	private BufferedImage createImage(String picName, int length) {
		BufferedImage i = null;
		try {
			i = ImageIO.read(new File(picName));
		} catch (IOException e) {
			System.err.println("Picture File not found");
		}

		// Pulled from StackOverflow
		int bWidth = i.getWidth();
		int bHeight = i.getHeight();
		BufferedImage after = new BufferedImage(bWidth, bHeight, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale((double)(length)/bWidth, (double)(length)/bHeight);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(i, after);
		
		return after;
	}
	
	public void randomize() {
		
		for(int i = 0; i < (1000 * pieceSize); i++) {
			
			int rando = (int)(Math.random()*(pieces.length * pieces[0].length) - 1) + 2;			
			
			boolean swapFound = false;
			for(int k = 0; k < pieces.length; k++) {
				for(int j = 0; j < pieces[k].length; j++) {
					if(pieces[k][j].getNum() == rando) {
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

	public Piece findClickedPiece(int mouseX, int mouseY) {
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				if(pieces[i][k].wasClicked(mouseX, mouseY)) {
					return pieces[i][k];
				}
			}
		}
		return null;
	}

	public boolean swap(Piece piece) {
		Piece blankPiece = null;
		int blankRow = -1;
		int blankCol = -1;

		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				if(pieces[i][k].getNum() == 1) {
					blankPiece = pieces[i][k];
					blankRow = blankPiece.getRow();
					blankCol = blankPiece.getCol();
					break;
				}
			}
			if(blankPiece != null) {
				break;
			}
		}
		
		if(blankPiece != null) {
		
			if((piece.getCol() == blankPiece.getCol() && 
					(piece.getRow() == blankPiece.getRow() - 1 || piece.getRow() == blankPiece.getRow() + 1)) || 
					(piece.getRow() == blankPiece.getRow() && 
					(piece.getCol() == blankPiece.getCol() - 1 || piece.getCol() == blankPiece.getCol() + 1))) {
				
				int bX = blankPiece.getX();
				int bY = blankPiece.getY();
				int bRow = blankPiece.getRow();
				int bCol = blankPiece.getCol();
				
				pieces[bCol][bRow] = piece;
				pieces[piece.getCol()][piece.getRow()] = blankPiece;
				
				blankPiece.swapPieceValues(piece);
				piece.swapPieceValues(bX, bY, bRow, bCol);				
				
			}
			
		}
		
		return didPlayerWin();
		
	}
	
	public boolean didPlayerWin() {
		boolean rtnBool = true;
		int num = 1;
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				if(pieces[i][k].getNum() != num) {
					rtnBool = false;
					break;
				}
				num++;
			}
			if(!rtnBool) {
				break;
			}
//			num++;
		}
		
		return rtnBool;
	}

	public Piece[][] getPieces() {
		return pieces;
	}

}
