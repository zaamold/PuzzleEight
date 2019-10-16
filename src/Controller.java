import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {
	
	public Model myModel;
	public View myView;
	private final int SIZE = 300;
	private final int SQUARE_NUM = 3;
	private final String PIC_NAME = "HaloReachLogo.png";
	private final String VIEW_TITLE = "Puzzle Eight";
	
	public Controller() {
		myModel = new Model(SQUARE_NUM, SIZE, PIC_NAME);
		myView = new View(this, SIZE, SIZE);
		myView.setTitle(VIEW_TITLE);
	}

	@Override
	public void mousePressed(MouseEvent e) { 
		int mouseX = e.getX();
		int mouseY = e.getY();
		boolean win = false;
		
		Piece clicked = myModel.findClickedPiece(mouseX, mouseY);
				
		if(clicked != null) {
			win = myModel.swap(clicked);
		}
		
		System.out.println("WIN: " + win);
		
		if(win) {
			myView.setTitle("You won!");
		} else {
			myView.setTitle(VIEW_TITLE);
		}
		
		myView.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	public void randomize() {
		myModel.randomize();
		myView.setTitle(VIEW_TITLE);
		myView.repaint();
	}
	
	public void reset() {
		myModel.fillPieces(SQUARE_NUM, SIZE, PIC_NAME);
		myView.setTitle(VIEW_TITLE);
		myView.repaint();
	}
	
	public Piece[][] getPieces() {
		return myModel.getPieces();
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
	}

}
