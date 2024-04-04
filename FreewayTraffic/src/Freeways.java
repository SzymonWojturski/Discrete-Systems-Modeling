import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;

/**
 * Board with Points that may be expanded (with automatic change of cell
 * number) with mouse event listener
 */

public class Freeways extends JComponent implements MouseInputListener, ComponentListener {
	private static final long serialVersionUID = 1L;
	private Car[][] cars;
	private Car[] newFreeway;
	private int size = 14;

	public Freeways(int length, int height) {
		addMouseListener(this);
		addComponentListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		setOpaque(true);
	}

	private void initialize(int length, int height) {
		cars = new Car[length][height];

		for (int x = 0; x < cars.length; ++x)
			for (int y = 0; y < cars[x].length; ++y)
				cars[x][y] = new Car();
	}

	// single iteration
	public void iteration() {
		moveDownTheGrid();
		Random random=new Random();
		if (cars[0][0].getExistance()==0 && random.nextInt(100)<10){
			cars[0][0].setExistance(1);
		}
		for(int x=0; x<cars.length;x++){
			cars[x][0].accelerate();
			cars[x][0].slowDown(x,0,cars);
			cars[x][0].randomize();
			cars[x][0].move(x,0,cars);
		}

		for(int x=0; x<cars.length;x++){
			cars[x][0].setMovedAlready(false);
		}
		this.repaint();
	}

	public void moveDownTheGrid(){
		for(int x=0;x< cars.length;x++){
			for(int y= cars[x].length-1;y>0;y--){
				cars[x][y]=cars[x][y-1].makeCopy();
			}
		}
	}

	// clearing board
	public void clear() {
		for (int x = 0; x < cars.length; ++x)
			for (int y = 0; y < cars[x].length; ++y) {
				cars[x][y].setExistance(0);
			}
		this.repaint();
	}

	//paint background and separators between cells
	protected void paintComponent(Graphics g) {
		if (isOpaque()) {
			g.setColor(getBackground());
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		g.setColor(Color.GRAY);
		drawNetting(g, size);
	}

	// draws the background netting
	private void drawNetting(Graphics g, int gridSpace) {
		Insets insets = getInsets();
		int firstX = insets.left;
		int firstY = insets.top;
		int lastX = this.getWidth() - insets.right;
		int lastY = this.getHeight() - insets.bottom;

		int x = firstX;
		while (x < lastX) {
			g.drawLine(x, firstY, x, lastY);
			x += gridSpace;
		}

		int y = firstY;
		while (y < lastY) {
			g.drawLine(firstX, y, lastX, y);
			y += gridSpace;
		}
		for (x = 0; x < cars.length; ++x) {
			for (y = 0; y < cars[x].length; ++y) {
				if (cars[x][y].getExistance() != 0) {
					switch (cars[x][y].getExistance()) {
					case 1:
						g.setColor(new Color(0x0000ff));
						break;
					case 2:
						g.setColor(new Color(0x00ff00));
						break;
					case 3:
						g.setColor(new Color(0xff0000));
						break;						
					case 4:
						g.setColor(new Color(0x000000));
						break;						
					case 5:
						g.setColor(new Color(0x444444));
						break;						
					case 6:
						g.setColor(new Color(0xffffff));
						break;						
					}
					g.fillRect((x * size) + 1, (y * size) + 1, (size - 1), (size - 1));
				}
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		if ((x < cars.length) && (x > 0) && (y < cars[x].length) && (y > 0)) {
			cars[x][y].clicked();
			this.repaint();
		}
	}

	public void componentResized(ComponentEvent e) {
		int dlugosc = (this.getWidth() / size) + 1;
		int wysokosc = (this.getHeight() / size) + 1;
		initialize(dlugosc, wysokosc);
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / size;
		int y = e.getY() / size;
		if ((x < cars.length) && (x >= 0) && (y < cars[x].length) && (y >= 0)) {
			cars[x][y].setExistance(1);
			this.repaint();
		}
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

}
