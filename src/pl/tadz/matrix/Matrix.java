package pl.tadz.matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Matrix extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage buff;
	private Graphics2D ga;
	
	public Matrix() {
		setBackground(Color.white);
		setLayout(new BorderLayout());
		setSize(800, 600);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2a = (Graphics2D) g;

		if (buff == null) {
			
			setDefaults();

			Randomizer<Figure> figureRandomizer = new Randomizer<Figure>(new Figures(getGa(), new AffineTransform()).toList());
			Randomizer<Color> colorRandomizer = new Randomizer<Color>(new Colors().toList());
			Randomizer<TexturePaint> backgroundRandomizer = new Randomizer<TexturePaint>(new Backgrounds().toList());
			Randomizer<Stroke> borderRandomizer = new Randomizer<Stroke>(new Borders().toList());

			for (int q = 0; q < 4; q++) {
				Figure randomFigure = figureRandomizer.getRandomElement();
				Color randomColor = colorRandomizer.getRandomElement();
				TexturePaint randomBackground = backgroundRandomizer.getRandomElement();
				Stroke randomBorder = borderRandomizer.getRandomElement();
				
				randomFigure.setQuarter(q);
				randomFigure.setColor(randomColor);
				randomFigure.setBackground(randomBackground);
				randomFigure.setBorder(randomBorder);
				
				randomFigure.draw();
			}

			drawAxes();
			
			getGa().dispose();
		}

		g2a.drawImage(buff, null, 0, 0);
	}

	private void setDefaults() {
		getGa().setBackground(Color.WHITE);
		getGa().clearRect(0, 0, 800, 600);
		getGa().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		getGa().setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}

	private void drawAxes() {
		getGa().setPaint(Color.BLACK);
		getGa().setStroke(Borders.SOLID);
		getGa().drawLine(0, 300, 800, 300);
		getGa().drawLine(400, 0, 400, 600);
	}

	public Graphics2D getGa() {
		if (null == ga) {
			int width = getWidth();
			int height = getHeight();
			buff = (BufferedImage) (this.createImage(width, height));
			ga = buff.createGraphics();
		}
 		
		return ga;
	}
}
