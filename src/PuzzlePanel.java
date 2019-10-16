import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PuzzlePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	Controller myController;
	Piece[][] pieces;
	
	public PuzzlePanel(Controller c, int width, int height) {
		myController = c;
		pieces = myController.getPieces();
		this.addMouseListener(myController);
		
		this.setPreferredSize(new Dimension(width, height));
		
	}
	
	public void paintComponent(Graphics page) {
		for(int i = 0; i < pieces.length; i++) {
			for(int k = 0; k < pieces[i].length; k++) {
				pieces[i][k].drawOn(page);
			}
		}
	}

}
