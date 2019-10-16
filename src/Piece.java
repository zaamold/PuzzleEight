import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Piece {

	int x, y, width, height, num, row, col;
	Color color;
	Image image;

	public Piece(int x, int y, int width, int height, int num, int row, int col, Image image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.num = num;
		this.row = row;
		this.col = col;
		this.image = image;
		color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
	}
	
	public void swapPieceValues(Piece p) {
		this.x = p.getX();
		this.y = p.getY();
		this.row = p.getRow();
		this.col = p.getCol();
	}
	
	public void swapPieceValues(int x, int y, int row, int col) {
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
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

	public Color getColor() {
		return color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
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

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean wasClicked(int mouseX, int mouseY) {
		if(num == 1) {
			return false;
		}
		
		if(mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < (y + height)) {
			return true;
		} else {
			return false;
		}
	}

	public void drawOn(Graphics page) {
		if(num != 1) {
			page.drawImage(image, x, y, width, height, null);
//			page.setColor(color);
//			page.fillRect(x, y, width, height);
//			page.setColor(Color.BLACK);
//			page.drawString(Integer.toString(num), x + (width/2), y + (height/2));
		} else {
			page.setColor(Color.white);
			page.fillRect(x, y, width, height);
		}
	}

	public String toString() {
		return "Piece " + num + " - x: " + x + " y: " + y;
	}

}
